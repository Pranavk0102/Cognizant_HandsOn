package com.library.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.library")
@EnableAspectJAutoProxy
public class AppConfig {

    public AppConfig() {
        System.out.println("AppConfig initialized - Component scanning enabled for com.library package");
    }
}