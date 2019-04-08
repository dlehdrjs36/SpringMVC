package com.springmvc.test.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestfulUserController {
	@Autowired
	RestService userService;
	
	//전체조회
	@RequestMapping(value="/users"
					,consumes= {"application/json, application/xml"}    //요청헤더
					,method=RequestMethod.GET)
	public Map<String,Object> getUserList(Model model, RestDTO vo) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("data", userService.getUsers(vo) );
		return result;
	}
	//단건조회
	@RequestMapping(value="/users/{id}",  method=RequestMethod.GET)
	public Map<String,Object> getUser(@PathVariable String id, RestDTO vo, Model model) {
		Map<String,Object> result = new HashMap<String, Object>();
		vo.setId(id);
		result.put("data", userService.getUser(vo) );
		return result;
	}
	//삭제
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public Map<String,Object>  getUserList( @PathVariable String id, RestDTO vo, Model model) {
		vo.setId(id);
		userService.deleteUser(vo);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		return result;
	}
	//등록
	@RequestMapping(value="/users"
			,method=RequestMethod.POST
	//		,produces="application/json"     
	// 		,consumes="application/json"
            ,headers = {"Content-type=application/json" }
	)
	public Map<String,Object> insertUser(@RequestBody RestDTO vo, Model model) {
		userService.insertUser(vo);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		return result;
	}
	//수정
	@RequestMapping(value="/users"
			,method=RequestMethod.PUT
	//		,produces="application/json"      //응답헤더
	 		,consumes="application/json"      //요청헤더
    //      ,headers = {"Content-type=application/json" }
	)
	public RestDTO updateUser(@RequestBody RestDTO vo, Model model) {
		userService.updateUser(vo);
		return  vo;
	}	
	@RequestMapping(value="/respAPI")
	public Map respAPI() {
		RestTemplate rest = new RestTemplate();
		return rest.getForObject("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101", Map.class);
	}
	

}
