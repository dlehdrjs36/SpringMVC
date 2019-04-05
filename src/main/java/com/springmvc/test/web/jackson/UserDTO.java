package com.springmvc.test.web.jackson;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/***
 * 
 * @XmlAccessorType(XmlAccessType.FIELD) 
 * - UserDTO를 XML Type으로 변환할수 있다는 의미의 어노테이션
 * - XmlAccessType.FIELD 옵션은 객체(UserDTO)가 가진 필드, 즉 변수들을 자동으로 자식 엘리먼트로 표현된다.
 * 
 * @XmlAttribute 
 * - 설정은 id를 속성으로 표현하라는 의미
 * @XmlTransient 
 * - 설정은 XML 변환에서 제외하라는 의미이다
 * 
 * @author User
 */
@XmlAccessorType(XmlAccessType.FIELD) 
public class UserDTO  {
	@XmlAttribute
	private String id;
	@XmlTransient
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
