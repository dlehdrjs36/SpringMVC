package com.springmvc.test.web.jackson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.test.web.jackson.UserDTO;
import com.springmvc.test.web.jackson.UserSearchDTO;

//유저 서비스 실행
@Service("userServiceImpl3") // UserServiceImpl을 Bean으로 등록하는 어노테이션.
public class UserServiceImpl implements UserService {
	@Autowired UserDAOMybatis dao; // Mybatis로 만든 UserDAO

	@Override
	public int insertUser(UserDTO dto) {	
		return dao.insertUser(dto);
	}
	@Override
	public UserDTO getUser(UserDTO dto) {
		return dao.getUser(dto);
	}
	//전체조회
	//JavaObj -> Xml 변환
	@Override
	public List<UserDTO> getUsers(UserSearchDTO searchDto) {	
		System.out.println("사용자 목록 조회"); // 실제이 부분은 로그로 대체 되어야함. 하루동안 로그가 쌓이면 몇백줄씩 쌓임. 로그는 로그라이브러리 이용함.
		return dao.getUsers(searchDto); // 핵심관심
	}
	//건수조회
	@Override
	public int getCnt(UserSearchDTO searchDto) {
		return dao.getCnt(searchDto);
	}

	//수정
	@Override
	public int updateUser(UserDTO dto) {
		return dao.updateUser(dto);//dao.updateUser();
	}
	//삭제
	@Override
	public int deleteUser(UserDTO dto) {
		return dao.deleteUser(dto);//dao.deleteUser();
	}
}
