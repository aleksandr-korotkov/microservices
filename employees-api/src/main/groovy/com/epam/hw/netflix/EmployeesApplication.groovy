package com.epam.hw.netflix

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
class EmployeesApplication {
    static void main(String[] args) {
        SpringApplication.run(EmployeesApplication, args)
    }
}
