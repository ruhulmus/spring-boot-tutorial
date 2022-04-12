package com.ruhulmus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootBucket4j {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBucket4j.class, args);
    }
}