package com.springmvc.test.web.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//유저 서비스 실행
@Service
public class BoardServiceImplJPA implements BoardServiceJPA {
	@Autowired BoardDAOJPA dao; // Hibernate로 만든 DAO

	@Override
	public boolean insertBoard(BoardVO vo) {	
		return dao.insertBoard(vo);
	}
	@Override
	public BoardVO getBoard(BoardVO vo) {
		return dao.getBoard(vo);
	}
	//전체조회
	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {	
		System.out.println("목록 조회");
		return dao.getBoardList(vo);
	}
	//건수조회
	@Override
	public int getCnt(BoardVO vo) {
		return dao.getCount(vo);
	}

	//수정
	@Override
	public boolean updateBoard(BoardVO vo) {
		return dao.updateBoard(vo);
	}
	//삭제
	@Override
	public boolean deleteBoard(BoardVO vo) {
		return dao.deleteBoard(vo);
	}
}
