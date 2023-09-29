package com.example.kokoc;

import controller.IndexController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {IndexController.class})
public class KokocApplication {

    public static void main(String[] args) {
        SpringApplication.run(KokocApplication.class, args);
    }

}
