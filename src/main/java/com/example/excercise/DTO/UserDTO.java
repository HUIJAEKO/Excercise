package com.example.excercise.DTO;

import com.example.excercise.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long id;
	private String name;
    private String birthdate;
    private String phone;
    private String region;
    private String subregion; 
    private String username;
    private String password;
    private String gender;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public static UserDTO toUserDTO(UserEntity userEntity) {
	    UserDTO userDTO = new UserDTO();
	    userDTO.setId(userEntity.getId());
	    userDTO.setName(userEntity.getName());
	    userDTO.setBirthdate(userEntity.getBirthdate());
	    userDTO.setPhone(userEntity.getPhone());
	    userDTO.setRegion(userEntity.getRegion());
	    userDTO.setSubregion(userEntity.getSubregion());
	    userDTO.setUsername(userEntity.getUsername());
	    userDTO.setPassword(userEntity.getPassword());
	    userDTO.setGender(userEntity.getGender());
	    return userDTO;
	}
}
