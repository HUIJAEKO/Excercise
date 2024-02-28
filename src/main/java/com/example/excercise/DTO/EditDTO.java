package com.example.excercise.DTO;

//회원정보 수정 DTO
public class EditDTO {
		private String region;
		private String subregion;
		private String phone;
		private String username;
		
		public EditDTO() {
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
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		
}
