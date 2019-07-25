package com.google.common.goat.eventbus.item06;

import com.google.common.eventbus.EventBus;

/**
 * Created by 64274 on 2019/7/25.
 *
 当EventBus发布了一个事件，但是注册的订阅者中没有找到处理该事件的方法，
 那么EventBus就会把该事件包装成一个DeadEvent事件来重新发布；我们在应用中可以提供如下的事件处理方法来处理DeadEvent。
 * @ author  山羊来了
 * @ date 2019/7/25---20:40
 */
public class DeadEventBusExample {

    public static void main(String[] args) {
        //重写EventBus的toString方法，使eventBus的名称为DEAD-EVENT-BUS
        final EventBus eventBus = new EventBus(){
            @Override public String toString() {
                return "DEAD-EVENT-BUS";
            }
        };
        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);


        eventBus.post("DeadEventListener event1");
        eventBus.post("DeadEventListener event2");
        eventBus.post("DeadEventListener event3");

    }
}

