package com.elotech.TesteTecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class TesteTecnicoApplication implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings( CorsRegistry registry ) {
		registry.addMapping("/**").allowedOrigins("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TesteTecnicoApplication.class, args);
	}

}
