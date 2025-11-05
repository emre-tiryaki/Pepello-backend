package org.pepello.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"org.pepello"})
@ComponentScan(basePackages = {"org.pepello"})
@EnableJpaRepositories(basePackages = {"org.pepello"})
@SpringBootApplication
public class PepelloApplication {

    static void main(String[] args) {
        SpringApplication.run(PepelloApplication.class, args);
    }

}
