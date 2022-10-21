package com.tyy.studio;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication()
@Configuration
public class StudioMain {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StudioMain.class).bannerMode(Mode.OFF)
                // //
                .headless(false)
                // //
                .run(args);

    }

}