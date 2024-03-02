package com.example.excercise.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.excercise.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
	List<PostEntity> findAllByOrderByPostCreatedTimeDesc(Pageable pageable);
}
