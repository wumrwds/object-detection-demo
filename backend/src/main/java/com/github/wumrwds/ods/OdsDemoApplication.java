package com.github.wumrwds.ods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OdsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdsDemoApplication.class, args);
	}

}
