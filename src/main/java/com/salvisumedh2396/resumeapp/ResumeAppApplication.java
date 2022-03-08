package com.salvisumedh2396.resumeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ResumeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeAppApplication.class, args);
	}

}
