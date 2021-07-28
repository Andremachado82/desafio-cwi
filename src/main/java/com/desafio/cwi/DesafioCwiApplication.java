package com.desafio.cwi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioCwiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioCwiApplication.class, args);
	}

}
