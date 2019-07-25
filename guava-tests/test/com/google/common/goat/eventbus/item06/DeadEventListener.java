package com.google.common.goat.eventbus.item06;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/7/25---20:40
 */

public class DeadEventListener {

    @Subscribe
    public void handle(DeadEvent event){
        //获取事件源
        System.out.println(event.getSource());//DEAD-EVENT-BUS
        //获取事件
        System.out.println(event.getEvent());//DeadEventListener event
    }
}


