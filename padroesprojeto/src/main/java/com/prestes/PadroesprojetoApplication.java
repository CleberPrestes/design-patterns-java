package com.prestes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class PadroesprojetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadroesprojetoApplication.class, args);
	}

}
