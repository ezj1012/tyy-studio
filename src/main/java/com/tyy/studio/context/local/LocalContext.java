package com.tyy.studio.context.local;

import java.io.File;
import java.io.IOException;

import org.springframework.core.env.ConfigurableEnvironment;

import com.tyy.studio.context.StageCtrl;
import com.tyy.studio.context.StudioContext;
import com.tyy.studio.utils.SystemUtils;
import com.tyy.studio.win.StudioWindow;
import com.tyy.studio.win.stage.ConfigStage;

public class LocalContext implements StudioContext {

    public static LocalContext context;

    ConfigurableEnvironment env;

    private LocalContext() {

    }

    public static LocalContext getCtx() {
        if (context == null) {
            synchronized (LocalContext.class) {
                if (context == null) {
                    context = new LocalContext();
                }
            }
        }
        return context;
    }

    File rootFile;

    @Override
    public void initContext(StudioWindow studioWindow, ConfigurableEnvironment environment) {
        this.env = environment;
        stageCtrl = new StageCtrl(studioWindow, this);
        studioWindow.getToolbar().setContext(this);
        try {
            String path = env.getProperty("root.path");
            rootFile = new File(path == null || "".equals(path.trim()) ? "./" : path).getCanonicalFile();
        } catch (IOException e) {
            publishException(e);
            getStageCtrl().toStage(ConfigStage.class);
            return;
        }
        LocalProperties localProps = getLocalProps();
        if (localProps == null) {
            getStageCtrl().toStage(ConfigStage.class);
            return;
        }
    }

    public File getRootFile() {
        return rootFile;
    }

    public LocalProperties getLocalProps() {
        LocalProperties props = SystemUtils.readFile(new File(rootFile, "tyy.cfg"), LocalProperties.class);
        return props;
    }

    private StageCtrl stageCtrl = null;

    public void setStageCtrl(StageCtrl stageCtrl) {
        this.stageCtrl = stageCtrl;
    }

    @Override
    public StageCtrl getStageCtrl() {
        return stageCtrl;
    }

}
