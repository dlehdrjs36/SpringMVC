package com.springmvc.test.web.jackson;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***
 * @XmlRootElement(name ="userList")
 * - Xml 문서는 반드시 단 하나의 루트 엘리먼트를 가져야 한다. 
 * - 우리가 원하는것은 여러 개의 UserDTO 정보를 담는 것이다.
 * - 여러개의 UserDTO 정보를 가지게 하기위해서 이 정보들을 여러개 가질 수 있는 루트 엘리먼트를 추가해야한다.
 * - 이 설정은 루트 엘리먼트를 설정한다는 의미이다.
*/

@XmlRootElement(name ="userList")
@XmlAccessorType(XmlAccessType.FIELD) 
public class UserListDTO {
	@XmlElement(name = "user")
	List<UserDTO> userList;

	public List<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}
}
