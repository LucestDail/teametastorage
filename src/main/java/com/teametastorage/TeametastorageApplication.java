package com.teametastorage;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TeametastorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeametastorageApplication.class, args);
	}

}
