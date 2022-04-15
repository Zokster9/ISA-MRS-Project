package com.example.projectmrsisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ProjectMrsIsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectMrsIsaApplication.class, args);
    }

}
