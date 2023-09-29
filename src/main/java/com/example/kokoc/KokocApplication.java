package com.example.kokoc;

import controller.IndexController;
import model.SportKind;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repo.SportKindRepository;


@ComponentScan(basePackageClasses = {IndexController.class})
@SpringBootApplication(scanBasePackageClasses = {SportKindRepository.class})
@EntityScan(basePackageClasses = {SportKind.class})
@EnableJpaRepositories(basePackageClasses = {SportKindRepository.class})
public class KokocApplication {

    public static void main(String[] args) {
        SpringApplication.run(KokocApplication.class, args);
    }

}
