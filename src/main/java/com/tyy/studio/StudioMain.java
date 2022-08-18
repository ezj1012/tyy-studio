package com.tyy.studio;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication()
@Configuration
public class StudioMain {

    @Bean
    public AA a() {
        System.out.println("init aa");
        return new AA();
    }

    public static void main(String[] args) {
        // LocalDateTime date = LocalDateTime.now();
        // Duration between = Duration.between(LocalDateTime.of(1992, 10, 12, 7, 0), date);
        // System.out.println(between.toDays());
        // System.out.println(between.toDays()/365);
        new SpringApplicationBuilder(StudioMain.class).bannerMode(Mode.OFF)
                //
                .web(WebApplicationType.NONE).headless(false)
                //
                .run(args);
    }

}