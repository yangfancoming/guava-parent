package com.google.common.goat.eventbus.item03;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/7/25---20:28
 */
public class BaseListener extends AbstractListener{

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseListener.class);

    @Subscribe
    public void baseTask(final String event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Received event [{}] will be handle by {}.{}", new Object[]{event,this.getClass().getSimpleName(),"baseTask"});
        }
    }
}
