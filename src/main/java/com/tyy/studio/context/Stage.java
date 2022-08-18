package com.tyy.studio.context;

public interface Stage {

    StudioContext getStudioContext();

    default boolean cached() {
        return false;
    }

}
