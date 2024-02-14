package com.example.excercise.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.excercise.exception.CustomAuthenticationFailureHandler;

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
										.requestMatchers("/","/login","/agree","/signup","/user/usernameCheck","/main").permitAll()
										.requestMatchers("/css/**", "/js/**").permitAll()
										.anyRequest().authenticated()
						);
		
		http
						.formLogin((auth)->auth.loginPage("/login")
										.loginProcessingUrl("/login")
										.defaultSuccessUrl("/main")
										.failureHandler(new CustomAuthenticationFailureHandler())
										.permitAll()
						)
						
						.logout((logout) -> logout
				                .logoutUrl("/logout") // 로그아웃 처리 URL 설정
				                .logoutSuccessUrl("/login") // 로그아웃 성공 시 리다이렉션될 URL
				                .invalidateHttpSession(true) // 세션 무효화
				                .clearAuthentication(true) // 인증 정보 클리어
				                .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
				                .permitAll()
				            );
		
		http
						.csrf((auth)->auth.disable());
		
		return http.build();
	}
}
