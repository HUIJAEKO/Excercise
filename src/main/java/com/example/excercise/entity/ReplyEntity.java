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

@Entity
@Table(name = "reply_table")
public class ReplyEntity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(nullable = false)
		private String replyContent;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "userId")
		private UserEntity userEntity;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "postId")
		private PostEntity postEntity;
		
		 @ManyToOne(fetch = FetchType.LAZY)
		 @JoinColumn(name = "parentReplyId")
		 private ReplyEntity ReplyEntity;

		 @OneToMany(mappedBy = "ReplyEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
		 private List<ReplyEntity> childReplies;
		
		@CreationTimestamp
		private Timestamp replyCreatedTime;
}
