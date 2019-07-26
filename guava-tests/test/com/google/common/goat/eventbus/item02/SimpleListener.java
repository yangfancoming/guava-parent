package com.google.common.goat.eventbus.item02;

import com.google.common.eventbus.Subscribe;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/7/25---19:54
 */
public class SimpleListener {


    /**
     * 一个简单的Listener方法  可以通过@Subscribe注解将任意的类的方法变为一个Listener
     * @param event Guava规定此处只能有一个参数
     */
    @Subscribe
    public void foo(final String event){
        System.out.println("foo 监听者 接收到消息：" + event);
    }

    @Subscribe
    public void bar(final String event){
        System.out.println("bar 监听者 接收到消息：" + event);
    }
}
