package com.example.excercise.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.excercise.DTO.UserDTO;
import com.example.excercise.entity.UserEntity;
import com.example.excercise.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void save(UserDTO userDTO) {
		UserEntity userEntity = UserEntity.toUserEntity(userDTO);
		userEntity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		userRepository.save(userEntity);
	}

	public String idcheck(String username) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
		if (optionalUserEntity.isEmpty()) {
			return "ok";
		} else {
			return "no";
		}
	}

//	public UserDTO login(UserDTO userDTO) {
//		Optional<UserEntity> byLoginUsername = userRepository.findByUsername(userDTO.getUsername());
//		if(byLoginUsername.isPresent()) {
//			UserEntity userEntity = byLoginUsername.get();
//			if(userEntity.getPassword().equals(userDTO.getPassword())) {
//				UserDTO dto = UserDTO.toUserDTO(userEntity);	
//				return dto;
//			}else {
//				return null;
//			}
//		}else{
//			return null;
//		}
//	}

	public UserDTO findById(Long id) {
		Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
		if (optionalUserEntity.isPresent()) {
			return UserDTO.toUserDTO(optionalUserEntity.get());
		} else {
			return null;
		}
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Transactional
	public void changePassword(String username, String newPassword) {
		UserEntity user = userRepository.findByname(username);
		if (user != null) {
			String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
			user.setPassword(encodedPassword);
			userRepository.save(user);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	@Transactional
	public void saveEdit(String username, String newRegion, String newSubregion, String newPhone) {
	    UserEntity userEntity = userRepository.findByname(username);
	    if (userEntity != null) {
	        userEntity.setRegion(newRegion);
	        userEntity.setSubregion(newSubregion);
	        userEntity.setPhone(newPhone);
	        userRepository.save(userEntity);
	    } else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();
		
		for(FieldError error: errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		return validatorResult;
	}
}
