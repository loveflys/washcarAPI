package com.qgil.washcar.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class CayApplication {
	public static void main(String[] args) {
		SpringApplication.run(CayApplication.class, args);
	}
}