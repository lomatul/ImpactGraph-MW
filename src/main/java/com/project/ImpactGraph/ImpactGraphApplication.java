package com.project.ImpactGraph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ImpactGraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImpactGraphApplication.class, args);
	}

}
