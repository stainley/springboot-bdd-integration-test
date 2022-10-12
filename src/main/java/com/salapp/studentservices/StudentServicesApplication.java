package com.salapp.studentservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StudentServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServicesApplication.class, args);
    }

}
