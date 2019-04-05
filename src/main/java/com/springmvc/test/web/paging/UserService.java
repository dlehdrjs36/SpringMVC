package com.springmvc.test.web.paging;

import java.util.List;

import com.springmvc.test.web.paging.UserDTO;
import com.springmvc.test.web.paging.UserSearchDTO;

public interface UserService { // 추후에 서비스가 교체될수도있기때문에 표준을 설정함.

	//등록
	public int insertUser(UserDTO dto);
	//수정
	public int updateUser(UserDTO dto);
	//삭제(회원탈퇴) , 외래키걸면 고려해야함. 다같이삭제할지 안할지. (외래키걸려있을때 키값만남겨놓고 나머지부분 null값으로 만드는것도 탈퇴임.)
	public int deleteUser(UserDTO dto);
	//단건조회
	public UserDTO getUser(UserDTO dto);
	//전체조회
	public List<UserDTO> getUsers(UserSearchDTO searchDto);
	//건수조회
	public int getCnt(UserSearchDTO searchDto);

}
