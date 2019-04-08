package com.springmvc.test.web.jackson;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.test.web.jackson.UserSearchDTO;
import com.springmvc.test.web.jackson.UserService;
import com.springmvc.test.web.jackson.UserDTO;
import com.springmvc.test.web.paging.Paging;

@Controller
public class JacksonController {

	@Autowired
	UserService userService;
	
	// JavaObject -> Json으로 형변환되어 값을 반환한다. 데이터는 http 응답프로토콜 몸체에 담긴다.
	@RequestMapping(value = "/getJsonMemberList.do"
					,produces="application/json")
	@ResponseBody
	public List<UserDTO> getJsonMemberList(UserSearchDTO vo,
							Paging paging) {
		// 페이지번호 파라미터
		if( paging.getPage() == null) {
			paging.setPage(1); 
		}
		// 시작/마지막 레코드 번호
		vo.setStart(paging.getFirst());
		vo.setEnd(paging.getLast());
		
		// 전체 건수
		int total = userService.getCnt(vo);//
		paging.setTotalRecord(total);
		
		List<UserDTO> list = userService.getUsers(vo);
		return list;
	}
	
	// JavaObject -> Xml으로 형변환하여 값을 반환한다. 데이터는 http 응답프로토콜 몸체에 담긴다.
	@RequestMapping(value = "/getJsonMemberList2.do"
					,produces="application/xml")
	@ResponseBody
	public UserListDTO getJsonMemberList2(UserSearchDTO vo, Paging paging) {
		// 페이지번호 파라미터
		if( paging.getPage() == null) {
			paging.setPage(1); 
		}
		// 시작/마지막 레코드 번호
		vo.setStart(paging.getFirst());
		vo.setEnd(paging.getLast());	
		// 전체 건수
		int total = userService.getCnt(vo);
		paging.setTotalRecord(total);
		
		List<UserDTO> list = userService.getUsers(vo);
		UserListDTO userList = new UserListDTO();
		userList.setUserList(list);	
		return userList;
	}
}
