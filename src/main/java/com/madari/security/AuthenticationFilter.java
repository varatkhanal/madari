package com.madari.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madari.dto.UserDTO;
import com.madari.model.request.LoginRequestModel;
import com.madari.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;
    //private final String TOKEN_SECRET="h4of9eh48vmg02nfu30v27yen295hfj65";

    public AuthenticationFilter(AuthenticationManager authenticationManager,
            UserService usersService) {
        this.userService = usersService;
        super.setAuthenticationManager(authenticationManager);
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
  
            LoginRequestModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginRequestModel.class);
            

            // Perform basic validation of the credentials
            if (creds == null || StringUtils.isEmpty(creds.getUserName()) || StringUtils.isEmpty(creds.getPassword())) {
                throw new BadCredentialsException("Invalid username or password");
            }
            
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUserName(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        
        // Get User Details from Database 
        String userName = ((User) auth.getPrincipal()).getUsername();  
            
        UserDTO userDto = userService.getUser(userName);
       // UserDetailResponseModel
        
        final String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        
       HashMap<String,Object> claims = new HashMap<>();
        
       //claims.put("User name", userName);
       //claims.put("first name", userDto.getFirstName());
       //claims.put("last name", userDto.getLastName());
      // claims.put("email", userDto.getEmail());
       claims.put("AUTHORITIES_KEY",authorities);
       claims.put("UserID", userDto.getUserId());
       
        // Generate GWT
        String token = Jwts.builder()
                .setSubject(userDto.getUserId())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong("360000000")))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET )
                .compact();      
          
        //res.addHeader("Token", token);
        res.addHeader("UserID", userDto.getUserId());
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+token);
        
     // Write the error message to the response body as JSON
        res.setContentType("application/json");
        new ObjectMapper().writeValue(res.getWriter(), claims);

    }  
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        // Set the response status to 401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Create a map to hold the error message
        Map<String, Object> error = new HashMap<String, Object>();
        error.put("message", "Invalid username or password");

        // Write the error message to the response body as JSON
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), error);
    }

} 	 