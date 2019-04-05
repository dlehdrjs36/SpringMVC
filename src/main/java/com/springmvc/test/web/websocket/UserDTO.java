package com.springmvc.test.web.websocket;

public class UserDTO  {
	private String id;
	private String passWord;
	private String name;
	private String role;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return passWord;
	}
	public void setPassword(String passWord) {
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
