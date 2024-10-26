package com.vadin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class VadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(VadinApplication.class, args);
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
