package com.molly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Start {
	public static void main(String[] args) {
		SpringApplication.run(Start.class,args);
	}
}
