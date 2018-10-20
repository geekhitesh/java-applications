package com.daarks.microservices.springmysqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class SpringMysqlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMysqlDemoApplication.class, args);
		
		//System.out.println("Created");
	}
}
