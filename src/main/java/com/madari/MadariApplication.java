package com.madari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MadariApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadariApplication.class, args);
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		
		return new SpringApplicationContext();
	}
	
	 @Bean
	 BCryptPasswordEncoder bCryptPasswordEncoder() { return new
	  BCryptPasswordEncoder(); 
	 }

}
