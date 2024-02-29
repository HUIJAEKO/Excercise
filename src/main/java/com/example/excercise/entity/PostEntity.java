package com.example.excercise.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//https://velog.io/@tjddnths0223/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8JPAqueryDsl-%EB%8C%80%EB%8C%93%EA%B8%80%EA%B3%84%EC%B8%B5%ED%98%95-%EA%B5%AC%ED%98%84

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_table")
public class PostEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String postTitle;
	
	@Column(nullable=false)
	private String postContent;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private UserEntity userEntity;
	
	@Column(nullable=false)
    private String region;
	
	@Column(nullable=false)
    private String subregion; 
	
	@OneToMany(mappedBy = "postEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<ReplyEntity> replyEntity;
	
	@CreationTimestamp
	private Timestamp postCreatedTime;
	

}
