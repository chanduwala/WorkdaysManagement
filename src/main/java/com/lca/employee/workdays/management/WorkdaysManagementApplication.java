package com.lca.employee.workdays.management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WorkdaysManagementApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkdaysManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WorkdaysManagementApplication.class, args);
        LOGGER.info("Work days management application started successfully!");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("http://localhost:4200/");
            }
        };
    }

}
