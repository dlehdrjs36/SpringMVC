package com.springmvc.test.web.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//유저 서비스 실행
@Service // UserServiceImpl을 Bean으로 등록하는 어노테이션.
public class UserServiceImpl implements UserService {
	//LogAdvice logAdvice = new LogAdvice(); // LogAdvice를 이용해서 System.out ... 찍어보기
	//Log4jAdvice logAdvice = new Log4jAdvice(); // Log4jAdvice를 이용해서 System.out ... 찍어보기
	
	
	//메서드 호출할때마다 로그 찍게 할 수 있음.
	//핵심관심과 횡단관심을 분리하는 방법
	
	//DI 기능사용. 의미 : UserDAO를 dao로 부르겠다. 
	//@Autowired UserDAO dao;  기존 사용하던 UserDAO
	//@Autowired UserDAOSpring dao; //스프링jdbc로 만든 UserDAO
	@Autowired UserDAOMybatis dao; // Mybatis로 만든 UserDAO
	@Override
	public int insertUser(UserDTO dto) {
		// 서비스단위로 트랜잭션 처리 등록시 작업이완료되지않는것이 하나라도있으면 모두 취소됨.  setAutocommit(false);
		/* 트랜잭션매니저 설정되어있으면 서비스단위로 알아서 트랜잭션 처리해줌. 
		 * 동시에 두가지 작업을 할때 트랜잭션매니저가 설정되어있을경우 모두 완료되지않으면 모두 취소.
		 * 트랙잭션매니저가 설정되지않았을 경우 모두완료되지않아도 자동 Commit = 2개중 1개작업만 완료되도 완료된 1개의작업은 commit 됨.
		dao.insertUser(dto);
		return dao.insertUser(dto);*/
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
		// int a = 5/0; 임의로 에러발생시키는 코드. ( after-throwing 확인할 수 있음)
		//logAdvice.pringlog();// 횡단관심
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
