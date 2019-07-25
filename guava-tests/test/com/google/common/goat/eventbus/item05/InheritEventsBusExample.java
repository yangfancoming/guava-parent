package com.google.common.goat.eventbus.item05;

import com.google.common.eventbus.EventBus;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: 结论：当作为参数的event之间有继承关系时，使用eventBus发送消息，eventt的父类listener也会对此消息进行处理。
 * @ author  山羊来了
 * @ date 2019/7/25---20:33
 */
public class InheritEventsBusExample {

    public static void main(String[] args) {

        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());

        System.out.println("-------发送子类   子类和父类都会处理  所以打印两条记录--------------");
        eventBus.post(new Apple("apple"));

        System.out.println("--------发送父类  只有父类处理 所以打印一条记录-------------");
        eventBus.post(new Fruit("Fruit"));
    }
}
