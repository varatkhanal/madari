package com.vadin.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@ComponentScan(basePackages = {"com.vadin.security"})
@EnableWebSecurity
public class MyWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    // FIX: Read allowed origins from environment variable.
    // Set CORS_ALLOWED_ORIGINS in Render to your frontend URL, e.g.:
    //   https://your-frontend.onrender.com
    // For local dev, set it in application-dev.properties or leave blank to allow localhost.
    @Value("${cors.allowed-origins:http://localhost:3000}")
    private String corsAllowedOrigins;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/signup", "/login", "/users/login").permitAll()
                .antMatchers("/courses/create").hasAuthority("ADD_CHAPTER_PRIVILEGE")
                .anyRequest().authenticated()
            .and()
                .addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // FIX: Re-enable sensible security headers.
        // Only frame options disabled (common for SPAs), others left at Spring defaults.
        http.headers().frameOptions().disable();
        http.headers().cacheControl().disable(); // SPAs handle their own caching
        // xssProtection and contentTypeOptions intentionally left enabled (Spring default)
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager(), userService);
        filter.setFilterProcessesUrl("/users/login");
        return filter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfig = new CorsConfiguration();

        // FIX: Use specific allowed origins from env var instead of wildcard (*)
        corsConfig.setAllowedOrigins(Arrays.asList(corsAllowedOrigins.split(",")));
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "UserID"));
        corsConfig.setExposedHeaders(Arrays.asList("Authorization", "UserID"));
        corsConfig.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
}
