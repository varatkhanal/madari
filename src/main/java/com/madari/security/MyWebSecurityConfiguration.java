package com.vadin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.vadin.services.UserService;

@ComponentScan(basePackages = { "com.vadin.security" })
//@ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class MyWebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	/*
	 * @Autowired AuthenticationSuccessHandler myAuthenticationSuccessHandler;
	 * 
	 * @Autowired private AuthenticationFailureHandler authenticationFailureHandler;
	 */
	
	@Autowired
	private UserService userService;
	
	 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub

		auth.userDetailsService(userService).passwordEncoder(encoder());
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub		
		http.cors().and().csrf().disable().authorizeRequests()
		.antMatchers("/signup", "/login").permitAll() // Allow public access
        .antMatchers("/courses/create").hasAuthority("ADD_CHAPTER_PRIVILEGE") // Requires the "ADD_COURSE_PRIVILEGE" role
        .anyRequest().authenticated() // All other requests require authentication
    .and()
        .addFilter(getAuthenticationFilter())
        .addFilter(new AuthorizationFilter(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        	
    	http.headers().cacheControl().disable();
    	http.headers().xssProtection().disable();
    	http.headers().frameOptions().disable();
    	http.headers().contentTypeOptions().disable();
    }
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

	 private AuthenticationFilter getAuthenticationFilter() throws Exception {
	        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager(),
	                userService);
	        filter.setFilterProcessesUrl("/users/login");
	        return filter;
	    }
	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	    	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration corsConfig = new CorsConfiguration();
	        corsConfig.applyPermitDefaultValues();
	        source.registerCorsConfiguration("/**", corsConfig);
	        return source;
	      
	    }
	    
}
