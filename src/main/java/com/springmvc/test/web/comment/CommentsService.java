package com.springmvc.test.web.comment;

import java.util.List;

public interface CommentsService {
	public void insertComments(CommentsVO vo);
	public void updateComments(CommentsVO vo);
	public void deleteComments(CommentsVO vo);
	public List<BoardDTO> getBoardList(BoardPagingDTO vo);
	public BoardDTO getBoard(BoardDTO vo);
	public int getBoardCount();
	public void updateCnt(BoardDTO vo);
	public List<CommentsVO> getCommentsList(CommentsVO vo);
	public CommentsVO getComments(CommentsVO vo);
}
