package com.tyy.studio.fun.markdown;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import com.tyy.studio.context.StudioContext;
import com.tyy.studio.fun.chart.Radar;
import com.tyy.studio.win.AbsStage;
import com.tyy.studio.win.comp.IconImg;

public class MDStage extends AbsStage {

    private static final long serialVersionUID = 1L;

    MDEditor pane = new MDEditor();

    public MDStage(StudioContext context) {
        super(context);
        this.setBackground(Color.white);
        this.setLayout(null);
        // this.add(pane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Radar a = new Radar(100, 100, 30);
        a.add("你好", 100, 100, "你好");
        a.add("你好", 100, 100, "你好");
        a.add("你好", 100, 100, "你好");
        a.add("你好", 100, 100, "你好");
        a.add("你好", 100, 100, "你好");
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 600);
        g.setColor(Color.BLACK);
        g.setColor(Color.red);
        g.fillPolygon(a.getPolygon());
    }

    @Override
    public IconImg getIcon() {
        return null;
    }

}
