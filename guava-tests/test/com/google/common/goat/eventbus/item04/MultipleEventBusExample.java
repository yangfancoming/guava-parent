package com.google.common.goat.eventbus.item04;

import com.google.common.eventbus.EventBus;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: 结论：eventBus会根据Listener的参数类型的不同，分别向不同的Subscribe发送不同的消息。
 * @ author  山羊来了
 * @ date 2019/7/25---20:31
 */
public class MultipleEventBusExample {

    public static void main(String[] args) {

        final EventBus eventBus = new EventBus();
        eventBus.register(new MultipleEventListeners());
        System.out.println("post the string event.");
        eventBus.post("I am String event");
        System.out.println("post the int event.");
        eventBus.post(1000);
    }
}

