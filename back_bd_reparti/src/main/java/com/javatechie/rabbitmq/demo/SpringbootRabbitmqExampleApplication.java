package com.javatechie.rabbitmq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class },scanBasePackages = "com.javatechie.rabbitmq.demo")
public class SpringbootRabbitmqExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqExampleApplication.class, args);
	}

}
