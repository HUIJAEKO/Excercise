package com.example.excercise.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.excercise.entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByUsername(String username);
	UserEntity findByname(String username);

}
