package com.springmvc.test.web.comment;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsMybatisDAO {

	@Autowired
	private SqlSessionTemplate sqlSession; 
	
	 public void insertComments(CommentsVO vo){
		 sqlSession.insert("comments.insertComments", vo);
	 }
	 
	 public void updateComments(CommentsVO vo){
		 sqlSession.update("comments.updateComments", vo);
	 }
	 
	 public void deleteComments(CommentsVO vo){
		 sqlSession.delete("comments.deleteComments", vo);
	 }	 	
	 //다건조회
	 public List<CommentsVO> getCommentsList(CommentsVO vo){
		 return sqlSession.selectList("comments.getCommentsList", vo);
	 }
	 //단건조회
	 public CommentsVO getComments(CommentsVO vo){
		 return sqlSession.selectOne("comments.getComments", vo);
	 }
	 //게시글 상세페이지 조회
	 public BoardDTO getBoard(BoardDTO vo){
		 return sqlSession.selectOne("comments.getBoard", vo);
	 }
	 //게시글 목록 조회
	 public List<BoardDTO> getBoardList(BoardPagingDTO vo){
		 return sqlSession.selectList("comments.getBoardList", vo);
	 }
	 //게시글 totalcount 조회
	 public int getBoardCount(){
		 return sqlSession.selectOne("comments.getBoardCount");
	 }
	 //게시글 조회수 업데이트
	 public int updateCnt(BoardDTO vo){
		 return sqlSession.update("comments.updateCnt", vo);
	 }

}
