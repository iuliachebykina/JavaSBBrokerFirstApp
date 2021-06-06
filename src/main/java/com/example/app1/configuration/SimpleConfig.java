package com.example.app1.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
public class SimpleConfig {

    @Bean
    public CountDownLatch countDownLatch(){
        return new CountDownLatch(1);
    }
}
