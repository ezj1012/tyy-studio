package com.tyy.studio.win;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.core.io.ClassPathResource;

import com.tyy.studio.win.stage.StartingLoading;

public class StudioWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final int width = 1024;

    public static final int height = 600;

    public static StudioWindow window;

    private final StartingLoading starting;

    private final Toolbar toolbar;

    private final JPanel stagePanel;

    public StudioWindow() {
        window = this;
        int w = width;
        int h = height;

        stagePanel = new JPanel();
        stagePanel.setLayout(null);
        stagePanel.setBounds(24, 0, width - 24, height);
        this.setStage(starting = new StartingLoading());

        toolbar = new Toolbar();
        this.getContentPane().setLayout(null);
        this.getContentPane().add(stagePanel);
        this.getContentPane().add(toolbar);

        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((ss.width - w) / 2, (ss.height - h) / 2, w, h);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(getFavicon().getImage());
        this.setResizable(false);
    }

    @Override
    public void setResizable(boolean resizable) {
        if (!resizable) {
            this.setBounds(this.getX(), this.getY(), width + 6, height + 29);
        }
        super.setResizable(resizable);
    }

    public ImageIcon getFavicon() {
        try {
            URL location = new ClassPathResource("favicon.png").getURL();
            return new ImageIcon(location);
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        StudioWindow studioWindow = new StudioWindow();
        studioWindow.setVisible(true);
        System.out.println(studioWindow.getContentPane().getWidth() + " " + studioWindow.getContentPane().getHeight());
    }

    public void setStage(AbsStage stage) {
        stagePanel.add(stage);
    }

}
