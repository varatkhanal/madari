package com.madari.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.madari.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;;
public class AuthorizationFilter extends BasicAuthenticationFilter{
	
	//Authentication auth;

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, 
			HttpServletResponse res, 
			FilterChain chain)throws IOException, ServletException{
		String header = req.getHeader(SecurityConstants.HEADER_STRING);
		
		if(header==null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
			
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		String token = req.getHeader(SecurityConstants.HEADER_STRING);
				
		if(token!=null) {
			token=token.replace(SecurityConstants.TOKEN_PREFIX,"");
			
			Claims claims = Jwts.parser()
	                .setSigningKey(SecurityConstants.TOKEN_SECRET)
	                .parseClaimsJws(token)
	                .getBody();

	        String userId = (String) claims.get("UserID");
	        
	        String authoritiesString = (String) claims.get("AUTHORITIES_KEY");
	        List<String> authorities = Arrays.asList(authoritiesString.split(","));
			
		
			if(userId!=null) {
				 List<GrantedAuthority> grantedAuthorities = authorities.stream()
		                    .map(SimpleGrantedAuthority::new)
		                    .collect(Collectors.toList());
				 System.out.println(grantedAuthorities);
				return new UsernamePasswordAuthenticationToken(userId,null,grantedAuthorities);//user, null, grantedAuthorities);
			}
			return null;
		}	
		return null;
	}
}
