package com.example.excercise.DTO;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.excercise.entity.UserEntity;

public class CustomUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private UserEntity userEntity;
	
	 public Long getId() {
			return userEntity.getId();
		}
    
    public String getName() {
		return userEntity.getName();
	}

	public String getBirthdate() {
		return userEntity.getBirthdate();
	}

	public String getPhone() {
		return userEntity.getPhone();
	}

	public String getRegion() {
		return userEntity.getRegion();
	}

	public String getSubregion() {
		return userEntity.getSubregion();
	}

	public String getGender() {
		return userEntity.getGender();
	}
	
	public CustomUserDetails(UserEntity userEntity) {
		this.setUserEntity(userEntity);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
