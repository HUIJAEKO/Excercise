package com.example.excercise.config;


import org.springframework.context.annotation.Bean;



import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.example.excercise.exception.CustomAuthenticationFailureHandler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
						.authorizeHttpRequests((auth)->auth
										.requestMatchers("/","/user/login","/user/agree","/user/signup","/user/usernameCheck","/user/main").permitAll()
										.requestMatchers("/css/**", "/js/**").permitAll()
										.anyRequest().authenticated()
						);
		
		http
						.formLogin((auth)->auth.loginPage("/user/login")
							    	.loginProcessingUrl("/user/login")
							    	.defaultSuccessUrl("/user/main", true)
							    	.failureHandler(new CustomAuthenticationFailureHandler())
							    	.permitAll()
						)
						
						.logout((logout) -> logout
				                .logoutUrl("/user/logout") 
				                .logoutSuccessUrl("/user/login") 
				                .addLogoutHandler((LogoutHandler) new LogoutHandler() {
									@Override
									public void logout(HttpServletRequest request, HttpServletResponse response,
											Authentication authentication) {
													String cookieName = "JSESSIONID";
													Cookie cookie = new Cookie(cookieName, null);
													cookie.setPath("/");
													cookie.setHttpOnly(true);
													cookie.setMaxAge(0); 
													response.addCookie(cookie);
				                    }

									
				                })
				                .invalidateHttpSession(true)
				                .clearAuthentication(true)
				                .permitAll()
				            );
		
		http
						.csrf((auth)->auth.disable());
		
		return http.build();
	}
}
