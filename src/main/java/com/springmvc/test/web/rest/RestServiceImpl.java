package com.springmvc.test.web.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//유저 서비스 실행
@Service
public class RestServiceImpl implements RestService {
	@Autowired RestDAOMybatis dao; // Mybatis로 만든 UserDAO

	@Override
	public int insertUser(RestDTO dto) {	
		return dao.insertUser(dto);
	}
	@Override
	public RestDTO getUser(RestDTO dto) {
		return dao.getUser(dto);
	}
	//전체조회
	//JavaObj -> Xml 변환
	@Override
	public List<RestDTO> getUsers(RestDTO searchDto) {	
		System.out.println("사용자 목록 조회"); // 실제이 부분은 로그로 대체 되어야함. 하루동안 로그가 쌓이면 몇백줄씩 쌓임. 로그는 로그라이브러리 이용함.
		return dao.getUsers(searchDto); // 핵심관심
	}
	//건수조회
	@Override
	public int getCnt(RestDTO searchDto) {
		return dao.getCnt(searchDto);
	}

	//수정
	@Override
	public int updateUser(RestDTO dto) {
		return dao.updateUser(dto);//dao.updateUser();
	}
	//삭제
	@Override
	public int deleteUser(RestDTO dto) {
		return dao.deleteUser(dto);//dao.deleteUser();
	}
}
