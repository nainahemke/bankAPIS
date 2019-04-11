package com.demobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.demobank.entity")
@EnableJpaRepositories(basePackages = {"com.demobank.dao"})
public class DemoBankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBankApiApplication.class, args);
	}

}
