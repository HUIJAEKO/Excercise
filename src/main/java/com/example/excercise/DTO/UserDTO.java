package com.example.excercise.DTO;

import com.example.excercise.entity.UserEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//사용자 DTO
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long id;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;
	
	@NotBlank(message = "생년월일은 필수 입력 값입니다.")
    private String birthdate;
    
	 @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    private String phone;
    
    @NotBlank(message = "지역은 필수 입력 값입니다.")
    private String region;
    
    @NotBlank(message = "지역은 필수 입력 값입니다.")
    private String subregion; 
    
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String username;
    
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
	
	@NotBlank(message = "성별은 필수 입력 값입니다.")
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
	
	//userEntity를  userDTO로 변경
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
