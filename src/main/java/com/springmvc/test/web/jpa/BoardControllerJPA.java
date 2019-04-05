package com.springmvc.test.web.jpa;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardControllerJPA {
	@Autowired
	BoardServiceJPA boardService;
	private boolean ck;
	
	//전체조회
	@RequestMapping(value="/boards", method=RequestMethod.GET)
	public Map<String,Object> getBoardList(Model model, BoardVO vo) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("data", boardService.getBoardList(vo) );
		return result;
	}
	//단건조회
	@RequestMapping(value="/boards/{id}",  method=RequestMethod.GET)
	public Map<String,Object> getBoard(@PathVariable String id, BoardVO vo, Model model) {
		Map<String,Object> result = new HashMap<String, Object>();
		vo.setSeq(Integer.parseInt(id));
		result.put("data", boardService.getBoard(vo) );
		return result;
	}
	//삭제
	@RequestMapping(value="/boards/{id}", method=RequestMethod.DELETE)
	public Map<String,Object> deleteBoard( @PathVariable String id, BoardVO vo, Model model) {
		vo.setSeq(Integer.parseInt(id));
		ck = boardService.deleteBoard(vo);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("result", ck);
		return result;
	}
	//등록
	@RequestMapping(value="/boards"
			,method=RequestMethod.POST
	//		,produces="application/json"     
	// 		,consumes="application/json"
            ,headers = {"Content-type=application/json" }
	)
	public Map<String,Object> insertBoard(@RequestBody BoardVO vo, Model model) {
		ck = boardService.insertBoard(vo);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("result", ck );
		return result;
	}
	//수정
	@RequestMapping(value="/boards"
			,method=RequestMethod.PUT
	//		,produces="application/json"      //응답헤더
	 		,consumes="application/json"     //요청헤더
    //      ,headers = {"Content-type=application/json" }
	)
	public Map<String,Object> updateBoard(@RequestBody BoardVO vo, Model model) {
		ck = boardService.updateBoard(vo);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("result", ck);
		return result;
	}	
}
