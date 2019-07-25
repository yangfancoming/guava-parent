package com.google.common.goat.eventbus.item04;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/7/25---20:30
 */
public class MultipleEventListeners {

    private final static Logger LOGGER = LoggerFactory.getLogger(MultipleEventListeners.class);

    @Subscribe
    public void task1(final String event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Received event [{}] and will take a action by ==task1==", event);
        }
    }

    @Subscribe
    public void task2(final String event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Received event [{}] and will take a action by ==task2==", event);
        }
    }

    @Subscribe
    public void intTask(final Integer event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Received event [{}] and will take a action by ==intTask==", event);
        }
    }
}


