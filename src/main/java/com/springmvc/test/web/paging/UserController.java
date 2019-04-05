package com.springmvc.test.web.paging;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.test.web.paging.UserSearchDTO;
import com.springmvc.test.web.paging.UserService;
import com.springmvc.test.web.paging.UserDTO;
import com.springmvc.test.web.paging.Paging;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	// 목록
	@RequestMapping(value = "/getMemberList.do")
	public ModelAndView getBoardList(ModelAndView mv, 
							UserSearchDTO vo,
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
		mv.addObject("paging", paging);
		mv.addObject("datas", list);
		mv.setViewName("memberList");
		return mv;
	}
}
