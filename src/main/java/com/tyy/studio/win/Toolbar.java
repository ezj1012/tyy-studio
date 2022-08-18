package com.tyy.studio.win;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tyy.studio.context.StudioConfig;
import com.tyy.studio.context.StudioContext;
import com.tyy.studio.fun.uml.UmlStage;
import com.tyy.studio.win.comp.IconButton;
import com.tyy.studio.win.comp.IconImg;

public class Toolbar extends JPanel {

    private static final long serialVersionUID = 1L;

    private IconButton config;

    private Color c1 = new Color(15, 15, 15, 128);

    private StudioContext context;

    public Toolbar() {
        this.setBounds(0, 0, 24, 600);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setLayout(null);
        this.setBackground(StudioConfig.getConfig().toolbarBackgroundColor);
        config = new IconButton(20, 20, new IconImg("setting.png", 20, 20));
        config.setBounds(2, getHeight() - (22 + 5), 20, 20);
        // config.setHoverBackground(c1);
        this.add(config);
        // config.addActionListener(new StageChangeListener(ConfigStage.class));
        config.addActionListener(new StageChangeListener(UmlStage.class));
    }

    private class StageChangeListener implements ActionListener {

        private Class<? extends AbsStage> clazz;

        public StageChangeListener(Class<? extends AbsStage> clazz) {
            this.clazz = clazz;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            context.getStageCtrl().toStage(clazz);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(c1);
        g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());

    }

    public void setContext(StudioContext context) {
        this.context = context;
    }

}
