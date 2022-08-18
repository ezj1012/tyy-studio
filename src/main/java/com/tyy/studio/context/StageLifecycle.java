package com.tyy.studio.context;

public interface StageLifecycle {

    default void create() {
    }

    default void active() {
    }

    default void showed() {
    }

    default void hided() {
    }

    default void removed() {
    }

}
