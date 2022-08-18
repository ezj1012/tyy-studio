package com.tyy.studio.context;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.tyy.studio.win.AbsStage;
import com.tyy.studio.win.StudioWindow;

public class StageCtrl {

    StudioWindow window;

    StudioContext context;

    Map<Class<? extends AbsStage>, AbsStage> stageMap = new HashMap<>();

    private AbsStage curStage;

    public StageCtrl(StudioWindow window, StudioContext context) {
        super();
        this.window = window;
        this.context = context;
    }

    public void setWindow(StudioWindow window) {
        this.window = window;
    }

    public void setContext(StudioContext context) {
        this.context = context;
    }

    public synchronized void toStage(Class<? extends AbsStage> stage) {
        AbsStage preStage = curStage;

        AbsStage newStage = stageMap.get(stage);
        if (newStage == null) {
            try {
                newStage = stage.getConstructor(StudioContext.class).newInstance(context);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
            newStage.create();
            if (newStage.needCache()) {
                stageMap.put(stage, newStage);
            }
        }
        newStage.active();
        this.window.setStage(newStage);
        newStage.showed();

        preStage.hided();

        if (!preStage.needCache()) {
            preStage.removed();
        }
    }

}
