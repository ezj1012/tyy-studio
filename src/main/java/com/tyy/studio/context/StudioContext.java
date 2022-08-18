package com.tyy.studio.context;

import org.springframework.core.env.ConfigurableEnvironment;

import com.tyy.studio.win.StudioWindow;

/**
 * 
 * @author XiongJian
 *
 */
public interface StudioContext {

    void initContext(StudioWindow studioWindow, ConfigurableEnvironment environment);

    StageCtrl getStageCtrl();

    default void publishException(Exception e) {
    }

}
