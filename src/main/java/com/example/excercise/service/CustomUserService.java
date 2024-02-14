package com.example.excercise.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.excercise.DTO.CustomUserDetails;
import com.example.excercise.entity.UserEntity;
import com.example.excercise.repository.UserRepository;

@Service
public class CustomUserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByname(username);
		if(user != null) {			
				return new CustomUserDetails(user);
			}else {
				return null;
			}
		}
}
