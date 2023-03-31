package com.project.questapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.project.questapp.business.concretes.UserDetailsManager;
import com.project.questapp.security.JwtAuthenticationEntryPoint;
import com.project.questapp.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private UserDetailsManager userDetailsManager;
	
	private JwtAuthenticationEntryPoint handler;

	public SecurityConfig(UserDetailsManager userDetailsManager, JwtAuthenticationEntryPoint handler) {
		this.userDetailsManager = userDetailsManager;
		this.handler = handler;
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	
}
