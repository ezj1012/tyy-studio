package com.tyy.studio.win;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tyy.studio.context.StageLifecycle;
import com.tyy.studio.context.StudioContext;
import com.tyy.studio.win.comp.IconImg;

public abstract class AbsStage extends JPanel implements StageLifecycle {

    private static final long serialVersionUID = 1L;

    protected StudioContext context;

    public AbsStage(StudioContext context) {
        this.context = context;
        this.setBounds(0, 0, 1000, 600);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public StudioContext getContext() {
        return context;
    }

    public boolean needCache() {
        return false;
    }

    public abstract IconImg getIcon();

}
