package com.gkfcsolution.onetomanyrealtion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OnetomanyrealtionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnetomanyrealtionApplication.class, args);
	}

}
