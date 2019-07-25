

package com.google.common.eventbus;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Registry of subscribers to a single event bus.
 */
final class SubscriberRegistry {

    /**
     * 他的键是Class类，也就是Event的类型，值是CopyOnWriteArraySet，也就是订阅者。
     * 这个ConcurrentMap是Guava定义的并发Map，这个后续咱们有机会再分析。
     * All registered subscribers, indexed by event type.
     * <p>The {@link CopyOnWriteArraySet} values make it easy and relatively lightweight to get an
     * immutable snapshot of all current subscribers to an event without any locking.
     */
    private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<Subscriber>> subscribers =  Maps.newConcurrentMap();

    /** The event bus this registry belongs to. */
    @Weak private final EventBus bus;

    SubscriberRegistry(EventBus bus) {
        this.bus = checkNotNull(bus);
    }

    /** Registers all subscriber methods on the given listener object. */
    /** 主要的逻辑是：
     获取这个类中所有用@Subscribe注解的方法，存储到Multimap中。
     遍历Multimap，键为eventType，然后根据这个键，从缓存中获取这个事件对应的订阅者集合。
     获取到之后，判断集合是否为空，如果为空，新建一个集合来存储。
    */
    void register(Object listener) {
        //根据订阅者找到对应的<EventType,订阅者集合>
        Multimap<Class<?>, Subscriber> listenerMethods = findAllSubscribers(listener);
        //新的订阅者添加到集合中
        for (Entry<Class<?>, Collection<Subscriber>> entry : listenerMethods.asMap().entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<Subscriber> eventMethodsInListener = entry.getValue();
            CopyOnWriteArraySet<Subscriber> eventSubscribers = subscribers.get(eventType);
            if (eventSubscribers == null) {
                CopyOnWriteArraySet<Subscriber> newSet = new CopyOnWriteArraySet<>();
                eventSubscribers = MoreObjects.firstNonNull(subscribers.putIfAbsent(eventType, newSet), newSet);
            }
            eventSubscribers.addAll(eventMethodsInListener);
        }
    }

    /** Unregisters all subscribers on the given listener object. */
    void unregister(Object listener) {
        // 获取监听器中所有的监听方法。
        Multimap<Class<?>, Subscriber> listenerMethods = findAllSubscribers(listener);

        for (Entry<Class<?>, Collection<Subscriber>> entry : listenerMethods.asMap().entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<Subscriber> listenerMethodsForType = entry.getValue();

            CopyOnWriteArraySet<Subscriber> currentSubscribers = subscribers.get(eventType);
            if (currentSubscribers == null || !currentSubscribers.removeAll(listenerMethodsForType)) {
                // if removeAll returns true, all we really know is that at least one subscriber was
                // removed... however, barring something very strange we can assume that if at least one
                // subscriber was removed, all subscribers on listener for that event type were... after
                // all, the definition of subscribers on a particular class is totally static
                throw new IllegalArgumentException(
                        "missing event subscriber for an annotated method. Is " + listener + " registered?");
            }

            // don't try to remove the set if it's empty; that can't be done safely without a lock
            // anyway, if the set is empty it'll just be wrapping an array of length 0
        }
    }

    @VisibleForTesting
    Set<Subscriber> getSubscribersForTesting(Class<?> eventType) {
        return MoreObjects.firstNonNull(subscribers.get(eventType), ImmutableSet.<Subscriber>of());
    }

    /**
     * Gets an iterator representing an immutable snapshot of all subscribers to the given event at
     * the time this method is called.
     */
    Iterator<Subscriber> getSubscribers(Object event) {
        ImmutableSet<Class<?>> eventTypes = flattenHierarchy(event.getClass());
        List<Iterator<Subscriber>> subscriberIterators =  Lists.newArrayListWithCapacity(eventTypes.size());
        for (Class<?> eventType : eventTypes) {
            CopyOnWriteArraySet<Subscriber> eventSubscribers = subscribers.get(eventType);
            if (eventSubscribers != null) {
                // eager no-copy snapshot
                subscriberIterators.add(eventSubscribers.iterator());
            }
        }
        return Iterators.concat(subscriberIterators.iterator());
    }

    /**
     * A thread-safe cache that contains the mapping from each class to all methods in that class and
     * all super-classes, that are annotated with {@code @Subscribe}. The cache is shared across all
     * instances of this class; this greatly improves performance if multiple EventBus instances are
     * created and objects of the same class are registered on all of them.
     */
    private static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodsCache =
            CacheBuilder.newBuilder().weakKeys()
                    .build(
                            new CacheLoader<Class<?>, ImmutableList<Method>>() {
                                @Override
                                public ImmutableList<Method> load(Class<?> concreteClass) throws Exception {
                                    return getAnnotatedMethodsNotCached(concreteClass);
                                }
                            });

    /**
     * Returns all subscribers for the given listener grouped by the type of event they subscribe to.
     * findAllSubscribers用于查找事件类型以及事件处理器的对应关系。
     * 查找注解需要涉及到反射，通过反射来获取标注在方法上的注解。
     * 因为Guava针对EventBus的注册采取的是“隐式契约”而非接口这种“显式契约”。
     * 而类与接口是存在继承关系的，所有很有可能某个订阅者其父类（或者父类实现的某个接口）也订阅了某个事件。
     * 因此这里的查找需要顺着继承链向上查找父类的方法是否也被注解标注。
     */
    private Multimap<Class<?>, Subscriber> findAllSubscribers(Object listener) {
        Multimap<Class<?>, Subscriber> methodsInListener = HashMultimap.create();
        Class<?> clazz = listener.getClass();
        ImmutableList<Method> annotatedMethods = getAnnotatedMethods(clazz);
        for (Method method : annotatedMethods) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];
            methodsInListener.put(eventType, Subscriber.create(bus, listener, method));
        }
        return methodsInListener;
    }

    private static ImmutableList<Method> getAnnotatedMethods(Class<?> clazz) {
        return subscriberMethodsCache.getUnchecked(clazz);
    }

    //这个执行结果会放入到subscriberMethodsCache本地缓存中，下次同样的Class不用再反射解析，直接从缓存中拿
    private static ImmutableList<Method> getAnnotatedMethodsNotCached(Class<?> clazz) {
        Set<? extends Class<?>> supertypes = TypeToken.of(clazz).getTypes().rawTypes();
        Map<MethodIdentifier, Method> identifiers = Maps.newHashMap();
        for (Class<?> supertype : supertypes) {
            for (Method method : supertype.getDeclaredMethods()) {
                //遍历订阅者的每个方法，检查方法上是否有@SubScribe注解
                if (method.isAnnotationPresent(Subscribe.class) && !method.isSynthetic()) {
                    // TODO(cgdecker): Should check for a generic parameter type and error out
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    //注解方法只能有一个参数
                    checkArgument(
                            parameterTypes.length == 1,"Method %s has @Subscribe annotation but has %s parameters. Subscriber methods must have exactly 1 parameter.",
                            method,parameterTypes.length);
                    MethodIdentifier ident = new MethodIdentifier(method);
                    if (!identifiers.containsKey(ident)) {
                        identifiers.put(ident, method);
                    }
                }
            }
        }
        return ImmutableList.copyOf(identifiers.values());
    }

    /** Global cache of classes to their flattened hierarchy of supertypes. */
    private static final LoadingCache<Class<?>, ImmutableSet<Class<?>>> flattenHierarchyCache =
            CacheBuilder.newBuilder()
                    .weakKeys()
                    .build(
                            new CacheLoader<Class<?>, ImmutableSet<Class<?>>>() {
                                // <Class<?>> is actually needed to compile
                                @SuppressWarnings("RedundantTypeArguments")
                                @Override
                                public ImmutableSet<Class<?>> load(Class<?> concreteClass) {
                                    return ImmutableSet.<Class<?>>copyOf(TypeToken.of(concreteClass).getTypes().rawTypes());
                                }
                            });

    /**
     * Flattens a class's type hierarchy into a set of {@code Class} objects including all
     * superclasses (transitively) and all interfaces implemented by these superclasses.
     */
    @VisibleForTesting
    static ImmutableSet<Class<?>> flattenHierarchy(Class<?> concreteClass) {
        try {
            return flattenHierarchyCache.getUnchecked(concreteClass);
        } catch (UncheckedExecutionException e) {
            throw Throwables.propagate(e.getCause());
        }
    }

    private static final class MethodIdentifier {

        private final String name;
        private final List<Class<?>> parameterTypes;

        MethodIdentifier(Method method) {
            this.name = method.getName();
            this.parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name, parameterTypes);
        }

        @Override
        public boolean equals(@Nullable Object o) {
            if (o instanceof MethodIdentifier) {
                MethodIdentifier ident = (MethodIdentifier) o;
                return name.equals(ident.name) && parameterTypes.equals(ident.parameterTypes);
            }
            return false;
        }
    }
}
