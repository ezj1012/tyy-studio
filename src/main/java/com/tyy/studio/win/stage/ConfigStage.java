package com.tyy.studio.win.stage;

import java.awt.Color;

import com.tyy.studio.context.StudioContext;
import com.tyy.studio.win.AbsStage;
import com.tyy.studio.win.comp.IconImg;

public class ConfigStage extends AbsStage {

    private static final long serialVersionUID = 1L;

    public ConfigStage(StudioContext context) {
        super(context);
        this.setBackground(Color.BLACK);
    }

    @Override
    public IconImg getIcon() {
        return null;
    }

}
