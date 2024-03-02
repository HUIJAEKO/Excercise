package com.example.excercise.DTO;

import java.sql.Timestamp;
import java.util.List;

import com.example.excercise.entity.PostEntity;
import com.example.excercise.entity.ReplyEntity;
import com.example.excercise.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private Long id;
	private String postTitle;
	private String postContent;
	private UserEntity userEntity;
	private String region;
	private String subregion; 
	private List<ReplyEntity> replyEntity;
	private Timestamp postCreatedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSubregion() {
		return subregion;
	}

	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}

	public List<ReplyEntity> getReplyEntity() {
		return replyEntity;
	}

	public void setReplyEntity(List<ReplyEntity> replyEntity) {
		this.replyEntity = replyEntity;
	}

	public Timestamp getPostCreatedTime() {
		return postCreatedTime;
	}

	public void setPostCreatedTime(Timestamp postCreatedTime) {
		this.postCreatedTime = postCreatedTime;
	}
	
	//PostDTO를 PostEntity로 전환
	public static PostEntity toPostEntity(PostDTO postDTO) {
		PostEntity postEntity = new PostEntity();
		postEntity.setPostTitle(postDTO.getPostTitle());
		postEntity.setPostContent(postDTO.getPostContent());
		postEntity.setRegion(postDTO.getRegion());
		postEntity.setSubregion(postDTO.getSubregion());
		postEntity.setUserEntity(postDTO.getUserEntity());
		postEntity.setReplyEntity(postDTO.getReplyEntity());
		postEntity.setPostCreatedTime(postDTO.getPostCreatedTime());


		return postEntity;
	}
}
