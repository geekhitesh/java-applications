package com.udemy.kafka.udemykafkada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class UdemyKafkaDaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyKafkaDaApplication.class, args);
	}
}
