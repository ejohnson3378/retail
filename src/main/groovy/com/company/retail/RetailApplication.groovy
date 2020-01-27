package com.company.retail

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.company.retail")
class RetailApplication {

	static void main(String[] args) {
		SpringApplication.run(RetailApplication, args)
	}

}
