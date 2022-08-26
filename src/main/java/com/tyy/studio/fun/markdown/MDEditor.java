package com.tyy.studio.fun.markdown;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.EditorKit;

public class MDEditor extends JTextPane {

    private static final long serialVersionUID = 1L;

    MDParser parser = new MDParser();

    MDEditorKit kit = new MDEditorKit();

    public MDEditor() {
        setPreferredSize(new Dimension(1000, 600));
        setBackground(Color.white);
        setBounds(0, 0, 1000, 600);
        setBorder(new EmptyBorder(30, 5, 5, 5));
        // setEditorKit(kit);
        setContentType("text/html");
        setSelectedTextColor(Color.red);
        setText("<h1>你好!</h1><h2>你好2</h2>");
        // try {
        // setPage("http://localhost/index.html");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

    }

}
