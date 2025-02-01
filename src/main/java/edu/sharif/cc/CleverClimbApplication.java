package edu.sharif.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.sharif.cc")
public class CleverClimbApplication {

	public static void main(String[] args) {

		SpringApplication.run(CleverClimbApplication.class, args);
	}

}
