package com.google.common.goat.eventbus.item03;

import com.google.common.eventbus.EventBus;

/**
 * Created by 64274 on 2019/7/25.
 * @ Description: 结论：注册了一个Listener，使用eventBus发送消息它的父类的Subscribe也会对此消息进行处理。
 * @ author  山羊来了
 * @ date 2019/7/25---20:29
 */
public class InheritListenersEventBusExample {

    public static void main(String[] args) {

        final EventBus eventBus = new EventBus();
        eventBus.register(new ConcreteListener());
        System.out.println("post the string event.");
        eventBus.post("I am String event");
        System.out.println("post the int event.");
        eventBus.post(1000);

    }
}
