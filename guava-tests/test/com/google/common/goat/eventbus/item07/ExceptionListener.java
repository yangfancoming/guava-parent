package com.google.common.goat.eventbus.item07;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/7/25---20:44
 */
public class ExceptionListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionListener.class);

    @Subscribe
    public void m1(final String event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Received event [{}] and will take m1", event);
        }
    }
    @Subscribe
    public void m2(final String event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Received event [{}] and will take m2", event);
        }
    }
    @Subscribe
    public void m3(final String event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Received event [{}] and will take m3", event);
        }
        throw new RuntimeException();
    }
}

