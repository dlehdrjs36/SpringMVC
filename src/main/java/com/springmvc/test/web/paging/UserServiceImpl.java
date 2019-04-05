package com.springmvc.test.web.paging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.test.web.paging.UserDTO;
import com.springmvc.test.web.paging.UserSearchDTO;

//유저 서비스 실행
@Service("userServiceImpl2") // UserServiceImpl을 Bean으로 등록하는 어노테이션.
public class UserServiceImpl implements UserService {
	//LogAdvice logAdvice = new LogAdvice(); // LogAdvice를 이용해서 System.out ... 찍어보기
	//Log4jAdvice logAdvice = new Log4jAdvice(); // Log4jAdvice를 이용해서 System.out ... 찍어보기
	
	@Autowired UserDAOMybatis dao; // Mybatis로 만든 UserDAO
	@Override
	public int insertUser(UserDTO dto) {
		
		return dao.insertUser(dto);
		// 실행후 commmit O
	}

	@Override
	public UserDTO getUser(UserDTO dto) {
		//logAdvice.pringlog(); // 횡단관심
		return dao.getUser(dto);
	}
	//전체조회
	@Override
	public List<UserDTO> getUsers(UserSearchDTO searchDto) {
		// 서비스메서드 부분
		
		System.out.println("사용자 목록 조회"); // 실제이 부분은 로그로 대체 되어야함. 하루동안 로그가 쌓이면 몇백줄씩 쌓임. 로그는 로그라이브러리 이용함.
		return dao.getUsers(searchDto); // 핵심관심
	}
	//건수조회
	@Override
	public int getCnt(UserSearchDTO searchDto) {
		// TODO Auto-generated method stub
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
