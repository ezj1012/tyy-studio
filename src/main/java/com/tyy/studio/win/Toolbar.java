package com.tyy.studio.win;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tyy.studio.context.StudioConfig;
import com.tyy.studio.win.comp.IconButton;
import com.tyy.studio.win.comp.IconImg;
import com.tyy.studio.win.stage.ConfigStage;

public class Toolbar extends JPanel {

    private static final long serialVersionUID = 1L;

    private IconButton config;

    private Color c1 = new Color(15, 15, 15, 128);

    public Toolbar() {
        this.setBounds(0, 0, 24, 600);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setLayout(null);
        this.setBackground(StudioConfig.getConfig().toolbarBackgroundColor);
        config = new IconButton(20, 20, new IconImg("setting.png", 20, 20));
        config.setBounds(2, getHeight() - (22 + 5), 20, 20);
        // config.setHoverBackground(c1);
        this.add(config);
        config.addActionListener(new StageChangeListener(ConfigStage.class));
    }

    private class StageChangeListener implements ActionListener {

        private Class<? extends AbsStage> clazz;

        public StageChangeListener(Class<? extends AbsStage> clazz) {
            this.clazz = clazz;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(this.clazz);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(c1);
        g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());

    }

}
