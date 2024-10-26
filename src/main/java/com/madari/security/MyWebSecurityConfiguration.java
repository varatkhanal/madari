package com.madari.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
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

import com.madari.services.UserService;

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
		// Posts endpoints
        .antMatchers(HttpMethod.POST,"/posts/**").hasAuthority("ADD_POST_PRIVILEGE")
        .antMatchers(HttpMethod.GET,"/posts/**").hasAuthority("GET_ALL_POST_PRIVILEGE")
        .antMatchers(HttpMethod.PUT,"/posts/**").hasAuthority("UPDATE_POST_PRIVILEGE")
        .antMatchers(HttpMethod.DELETE,"/posts/**").hasAuthority("DELETE_POST_PRIVILEGE")
		// Courses endpoints
		.antMatchers(HttpMethod.POST, "/courses/**").hasRole("MODERATOR")
		.antMatchers(HttpMethod.GET, "/courses/**").hasAuthority("GET_ALL_COURSE_PRIVILEGE")
		.antMatchers(HttpMethod.PUT, "/courses/**").hasAuthority("UPDATE_COURSE_PRIVILEGE")
		.antMatchers(HttpMethod.DELETE, "/courses/**").hasAuthority("DELETE_COURSE_PRIVILEGE")
        // Chapters endpoints
        .antMatchers(HttpMethod.POST,"/courses/*/chapters/**").hasAuthority("ADD_CHAPTER_PRIVILEGE")
        .antMatchers(HttpMethod.GET,"/courses/*/chapters/**").hasAuthority("GET_ALL_CHAPTER_PRIVILEGE")
        .antMatchers(HttpMethod.PUT,"/courses/*/chapters/**").hasAuthority("UPDATE_CHAPTER_PRIVILEGE")
        .antMatchers(HttpMethod.DELETE,"/courses/*/chapters/**").hasAuthority("DELETE_CHAPTER_PRIVILEGE")
        // Topics endpoints
        .antMatchers(HttpMethod.POST,"/courses/*/chapters/*/topics/**").hasAuthority("ADD_TOPIC_PRIVILEGE")
        .antMatchers(HttpMethod.GET,"/courses/*/chapters/*/topics/**").hasAuthority("GET_ALL_TOPIC_PRIVILEGE")
        .antMatchers(HttpMethod.PUT,"/courses/*/chapters/*/topics/**").hasAuthority("UPDATE_TOPIC_PRIVILEGE")
        .antMatchers(HttpMethod.DELETE,"/courses/*/chapters/*/topics/**").hasAuthority("DELETE_TOPIC_PRIVILEGE")
        // Topic Details endpoints
        .antMatchers(HttpMethod.POST,"/courses/*/chapters/*/topics/*/topicDetails").hasAuthority("ADD_TOPICDETAIL_PRIVILEGE")
        .antMatchers(HttpMethod.GET,"/courses/*/chapters/*/topics/*/topicDetails").hasAuthority("GET_ALL_TOPICDETAIL_PRIVILEGE")
        .antMatchers(HttpMethod.PUT,"/courses/*/chapters/*/topics/*/topicDetails").hasAuthority("UPDATE_TOPICDETAIL_PRIVILEGE")
        .antMatchers(HttpMethod.DELETE,"/courses/*/chapters/*/topics/*/topicDetails").hasAuthority("DELETE_TOPICDETAIL_PRIVILEGE")
        // Public endpoints
        .antMatchers("/signup", "/login").permitAll()
        .anyRequest().authenticated()
    .and()
        .addFilter(getAuthenticationFilter())//custom filters for authentication and authorization.
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
