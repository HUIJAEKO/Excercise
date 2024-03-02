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

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reply_table")
public class ReplyEntity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(nullable = false)
		private String replyContent;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id")
		private UserEntity userEntity;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "post_id")
		private PostEntity postEntity;
		
		 @ManyToOne(fetch = FetchType.LAZY)
		 @JoinColumn(name = "reply_id")
		 private ReplyEntity replyEntity;

		 @OneToMany(mappedBy = "replyEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
		 private List<ReplyEntity> childReply;
		
		@CreationTimestamp
		private Timestamp replyCreatedTime;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getReplyContent() {
			return replyContent;
		}

		public void setReplyContent(String replyContent) {
			this.replyContent = replyContent;
		}

		public UserEntity getUserEntity() {
			return userEntity;
		}

		public void setUserEntity(UserEntity userEntity) {
			this.userEntity = userEntity;
		}

		public PostEntity getPostEntity() {
			return postEntity;
		}

		public void setPostEntity(PostEntity postEntity) {
			this.postEntity = postEntity;
		}

		public ReplyEntity getReplyEntity() {
			return replyEntity;
		}

		public void setReplyEntity(ReplyEntity replyEntity) {
			this.replyEntity = replyEntity;
		}

		public List<ReplyEntity> getChildReply() {
			return childReply;
		}

		public void setChildReplies(List<ReplyEntity> childReply) {
			this.childReply = childReply;
		}

		public Timestamp getReplyCreatedTime() {
			return replyCreatedTime;
		}

		public void setReplyCreatedTime(Timestamp replyCreatedTime) {
			this.replyCreatedTime = replyCreatedTime;
		}
		
		
}
