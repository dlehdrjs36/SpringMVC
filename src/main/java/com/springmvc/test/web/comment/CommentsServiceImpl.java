package com.springmvc.test.web.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	CommentsMybatisDAO dao;
	
	public void insertComments(CommentsVO vo) {
		dao.insertComments(vo);
	}
	public void updateComments(CommentsVO vo) {
		dao.updateComments(vo);
	}
	public void deleteComments(CommentsVO vo) {
		dao.deleteComments(vo);
	}	
	public List<CommentsVO> getCommentsList(CommentsVO vo) {
		return dao.getCommentsList(vo);
	}
	public CommentsVO getComments(CommentsVO vo) {
		return dao.getComments(vo);
	}
	@Override
	public List<BoardDTO> getBoardList(BoardPagingDTO vo) {
		return dao.getBoardList(vo);
	}
	@Override
	public int getBoardCount() {
		return dao.getBoardCount();
	}
	@Override
	public BoardDTO getBoard(BoardDTO vo) {
		return dao.getBoard(vo);
	}
	@Override
	public void updateCnt(BoardDTO vo) {
		dao.updateCnt(vo);
	}
}
