# 目录
    item01
    item02 EventBus 基本用法
    item03 Listener之间的继承关系
    item04 不同类型参数的Subscribe
    item05 继承关系的event
    item06 DeadEvent
    item07 EventBus之异常处理
    
    
# 简介
    EventBus是Guava的事件处理机制，是设计模式中的观察者模式（生产/消费者编程模型）的优雅实现，在应用中可以处理一些异步任务。
    对于事件监听和发布订阅模式，EventBus是一个非常优雅和简单解决方案，我们不用创建复杂的类和接口层次结构。
    
# 主要类概念分析
    
    EventBus：这个类的作用有两个，一个是作为一个总线通道，另一个作用是消息的广播。
    AsyncEventBus：异步的EventBus，功能与EventBus类似，只不过实现方式有所差异。
    Subscriber：可以按照字面理解是订阅者，也可以说是监听器。
    SubscriberRegistry：订阅注册表。主要存储的是Subcriber和Event之间的关系，用于消息分发时可以迅速根据Event的类型找到Subscriber。
    Dispatcher：事件分发器，定义了一些分发的策略，里面包含三种分发器。
    两个重要的注解@Subscribe和@AllowConcurrentEvents。第一个是标识监听器的方法，第二个与第一个配合使用，标识允许多线程执行。
    DeadEvent：死信对象，标识没有订阅者关注的事件。
    SubscribeExceptionHandler：订阅者抛出异常的处理器。SubscribeExceptionContext：订阅者抛出异常的上下文对象。