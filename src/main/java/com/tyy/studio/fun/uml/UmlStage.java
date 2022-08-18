package com.tyy.studio.fun.uml;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.tyy.studio.context.StudioContext;
import com.tyy.studio.fun.uml.dclass.ClassComp;
import com.tyy.studio.win.AbsStage;
import com.tyy.studio.win.Res;
import com.tyy.studio.win.comp.IconImg;

public class UmlStage extends AbsStage {

    private static final long serialVersionUID = 1L;

    private Image img;

    public UmlStage(StudioContext context) {
        super(context);
        img = Res.get().getImage("back.png").getImage();
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        ClassComp comp = new ClassComp();
        comp.setBounds(0, 0, 200, 300);
        this.add(comp);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }

    @Override
    public IconImg getIcon() {
        return null;
    }

}
