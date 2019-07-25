package com.google.common.goat.eventbus.item01;

import com.google.common.eventbus.Subscribe;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/7/25---19:36
 */
public class EventListener {

    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("事件监听者:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}