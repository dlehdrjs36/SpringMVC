package com.springmvc.test.web.rest;

public class RestDTO {
	private String id;
	private String passWord;
	private String name;
	private String role;
	private String gender;
	private String address;
	private String phone;
	private String email;
	private String location;
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	// 이것이없으면 필드값을 볼 수없음. private로 정의되어있기 때문
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", passWord=" + passWord + ", name=" + name + ", role=" + role + "]";
	}
}
