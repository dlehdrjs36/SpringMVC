package com.springmvc.test.web.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentsController {

	@Autowired
	CommentsService commentService;
	
	//댓글 등록 예제화면
	@RequestMapping("getBoardAndComment.do")
	public String getBoardAndComment() {
		return "comments/getBoardAndComment";
	}
	
	//게시글 목록 조회
	@RequestMapping("getBoardList.do")
	public ModelAndView getBoardList(BoardPagingDTO vo, ModelAndView mv){

		vo.setTotal(commentService.getBoardCount());
		
		mv.addObject("paging", vo);
		mv.addObject("list", commentService.getBoardList(vo));
		mv.setViewName("comments/getBoards");
		return mv;
	}
	//게시글 상세 조회
	@RequestMapping("getBoard.do")
	public ModelAndView getBoard(BoardDTO vo, ModelAndView mv){

		//조회수 증가
		commentService.updateCnt(vo);
		//게시글 상세화면 
		mv.addObject("board", commentService.getBoard(vo) );
		//View
		mv.setViewName("comments/getBoard");
		return mv;
	}
	
	//댓글 등록
	@RequestMapping("insertComments")
	@ResponseBody // Ajax로 요청할때 사용됨, 자바객체를 넘겨줌
	public CommentsVO insertComments(CommentsVO vo){
		commentService.insertComments(vo); // 1개등록
		return commentService.getComments(vo); // 등록한것을 조회해서 view페이지에 넘겨줌(등록일자를 알기위해서(등록후 등록일자가 삽입되기떄문)
	
	}
	//댓글 수정
	@RequestMapping("updateComments")
	@ResponseBody
	public CommentsVO updateComments(CommentsVO vo){
		commentService.updateComments(vo);
		return vo;
	}
	//댓글 삭제
	@RequestMapping("deleteComments")
	@ResponseBody
	public CommentsVO deleteComments(CommentsVO vo){
		commentService.deleteComments(vo);
		return vo;
	}
	//댓글 목록
	@RequestMapping("getCommentsList")
	@ResponseBody
	public List<CommentsVO> getCommentsList(CommentsVO vo){
		vo.setPageUnit(10);
		return commentService.getCommentsList(vo);
	}
}
