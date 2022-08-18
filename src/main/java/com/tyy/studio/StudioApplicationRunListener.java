package com.tyy.studio;

import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import com.tyy.studio.context.local.LocalContext;
import com.tyy.studio.win.StudioWindow;

public class StudioApplicationRunListener implements SpringApplicationRunListener, Ordered {

    private final SpringApplication application;

    private final String[] args;

    private StudioWindow studioWindow;

    public StudioApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        System.out.println("starting");
        studioWindow = new StudioWindow();
        SwingUtilities.invokeLater(() -> {
            studioWindow.setVisible(true);
        });
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("environmentPrepared");
        LocalContext.getCtx().initContext(studioWindow, environment);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("contextPrepared");
        // context.getAutowireCapableBeanFactory();

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("failed");
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
