package com.google.common.goat.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/7/25---19:37
 */
public class App {


    @Test
    public void testReceiveEvent() {
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();
        eventBus.register(listener);
        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));
//        System.out.println("LastMessage:"+listener.getLastMessage());
    }
}
