package com.tyy.studio.win.stage;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tyy.studio.context.StudioConfig;
import com.tyy.studio.win.AbsStage;
import com.tyy.studio.win.comp.IconImg;

public class StartingLoading extends AbsStage {

    private static final long serialVersionUID = 1L;

    private ImageIcon favicon;

    public StartingLoading() {
        super(null);
        this.setBackground(StudioConfig.getConfig().toolbarBackgroundColor);
        favicon = StudioConfig.getConfig().getFavicon();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(favicon.getImage(), (this.getWidth() - favicon.getIconWidth()) / 2 - 12, (this.getHeight() - favicon.getIconHeight()) / 2 - 20, this);
    }

    @Override
    public IconImg getIcon() {
        return null;
    }

}
