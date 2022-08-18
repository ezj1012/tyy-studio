package com.tyy.studio.fun.uml.dclass;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.tyy.studio.fun.uml.bean.UMLClass;

/**
 * UML 类图
 * 
 * @author XiongJian
 *
 */
public class ClassComp extends JPanel {

    private static final long serialVersionUID = 1L;

    private UMLClass umlClass;

    public void setUmlClass(UMLClass umlClass) {
        this.umlClass = umlClass;
        this.setBounds(0, 0, 200, 300);
        this.setBackground(Color.WHITE);
    }

    public UMLClass getUmlClass() {
        return umlClass;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

}
