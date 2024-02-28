package com.org.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.org.product")
@EnableAutoConfiguration
public class PartApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartApplication.class, args);
	}
}
