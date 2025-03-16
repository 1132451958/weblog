package com.chai.weblog.web;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({"com.chai.weblog.*"})
@SpringBootApplication
@EnableScheduling
public class WeblogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeblogWebApplication.class, args);
    }

}
