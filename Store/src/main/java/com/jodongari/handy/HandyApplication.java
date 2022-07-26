package com.jodongari.handy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HandyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandyApplication.class, args);
	}

}
