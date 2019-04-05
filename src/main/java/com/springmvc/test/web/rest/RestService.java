package com.springmvc.test.web.rest;

import java.util.List;

public interface RestService { // 추후에 서비스가 교체될수도있기때문에 표준을 설정함.

	//등록
	public int insertUser(RestDTO dto);
	//수정
	public int updateUser(RestDTO dto);
	//삭제(회원탈퇴) , 외래키걸면 고려해야함. 다같이삭제할지 안할지. (외래키걸려있을때 키값만남겨놓고 나머지부분 null값으로 만드는것도 탈퇴임.)
	public int deleteUser(RestDTO dto);
	//단건조회
	public RestDTO getUser(RestDTO dto);
	//전체조회
	//Jackson을 이용해서 JavaObject -> Xml 데이터 변환하여 전달, 전체조회
	public List<RestDTO> getUsers(RestDTO searchDto);
	//건수조회
	public int getCnt(RestDTO searchDto);
}
