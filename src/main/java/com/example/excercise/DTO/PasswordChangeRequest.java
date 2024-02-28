package com.example.excercise.DTO;

//비밀번호 변경 DTO
public class PasswordChangeRequest {
	private String currentPw;
	private String newPw;
	private String newPwConfirm;
	
	
	public String getCurrentPw() {
		return currentPw;
	}
	public void setCurrentPw(String currentPw) {
		this.currentPw = currentPw;
	}
	public String getNewPw() {
		return newPw;
	}
	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}
	public String getNewPwConfirm() {
		return newPwConfirm;
	}
	public void setNewPwConfirm(String newPwConfirm) {
		this.newPwConfirm = newPwConfirm;
	}
	
	
}
