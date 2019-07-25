package com.google.common.goat.eventbus.item05;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/7/25---20:33
 */
public class FruitEaterListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(FruitEaterListener.class);

    @Subscribe
    public void eat(Fruit event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Fruit eat[{}]. ", event);
        }
    }

    @Subscribe
    public void eat(Apple event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Apple eat[{}]. ", event);
        }
    }
}
