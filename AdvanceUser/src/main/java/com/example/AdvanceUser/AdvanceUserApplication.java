package com.example.AdvanceUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AdvanceUserApplication { //model -> Repository -> Service -> Controller -> client

	public static void main(String[] args) {

		SpringApplication.run(AdvanceUserApplication.class, args);
	}

}
