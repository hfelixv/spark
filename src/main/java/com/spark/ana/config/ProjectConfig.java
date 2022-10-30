package com.spark.ana.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(
        basePackages = "com.spark.ana.rest.client")
public class ProjectConfig {
}