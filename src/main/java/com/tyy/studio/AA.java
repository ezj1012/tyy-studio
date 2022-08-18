package com.tyy.studio;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class AA {

    @Value("${aa}")
    private String aa;

    @PostConstruct
    public void init() {
        System.out.println("postConstruct");
    }

    public AA() {
    }

}
