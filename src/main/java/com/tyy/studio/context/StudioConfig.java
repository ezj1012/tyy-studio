package com.tyy.studio.context;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.springframework.core.io.ClassPathResource;

public class StudioConfig {

    public static StudioConfig config;

    public StudioConfig() {
        StudioConfig.config = this;
    }

    public static StudioConfig getConfig() {
        if (config == null) {
            synchronized (StudioConfig.class) {
                if (config == null) {
                    new StudioConfig();
                }
            }
        }
        return config;
    }

    public Color toolbarBackgroundColor = new Color(51, 51, 51);

    public ImageIcon getFavicon() {
        try {
            URL location = new ClassPathResource("favicon.png").getURL();
            return new ImageIcon(location);
        } catch (IOException e) {
            return null;
        }
    }

}
