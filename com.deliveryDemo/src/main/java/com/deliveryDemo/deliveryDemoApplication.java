package com.deliveryDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class deliveryDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(deliveryDemoApplication.class,args);
        log.info("App Start......");
    }
}
