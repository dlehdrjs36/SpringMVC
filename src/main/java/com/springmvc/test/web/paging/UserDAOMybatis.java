package com.springmvc.test.web.paging;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springmvc.test.web.paging.UserDTO;
import com.springmvc.test.web.paging.UserSearchDTO;

@Repository("userDAOMybatis2")
public class UserDAOMybatis {
	//dml구문 구분하기위해서 사용한것.(insert,update... insert수행에  update 사용해도 정상동작. id값은 정확하게 주어야함.
	
	@Autowired
	SqlSessionTemplate mybatis;
	//전체조회
	public List<UserDTO> getUsers(UserSearchDTO searchDto) {
		System.out.println("==============user mybatis 목록 조회==========="); //selectOne : 단건조회, selectList :리스트조회
		return mybatis.selectList("user.getUsers", searchDto); // userMappe.xml의 namespace이름.id 
	}
	//건수 조회
	public int getCnt(UserSearchDTO searchDto) {
		System.out.println("==============mybatis 전체 건수 조회==============");
		return mybatis.selectOne("user.getCnt", searchDto);
	}
	//단건조회
	public UserDTO getUser(UserDTO dto) {
		System.out.println("==============mybatis 사용자 단건조회==============");
		return mybatis.selectOne("user.getUser", dto);
	}
	//등록
	public int insertUser(UserDTO dto) {
		System.out.println("==============mybatis 사용자 추가==============");
		return mybatis.insert("user.insertUser", dto); // 네임스페이스명.userMappe의id
	}
	//수정
	public int updateUser(UserDTO dto) {
		System.out.println("==============mybatis 사용자 수정==============");
		return mybatis.update("user.updateUser", dto);
	}
	//삭제
	public int deleteUser(UserDTO dto) {
		System.out.println("==============mybatis 사용자 삭제==============");
		return mybatis.delete("user.deleteUser", dto);
	}

}
