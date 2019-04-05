package com.springmvc.test.web.jpa;

import java.util.List;

public interface BoardServiceJPA { // 추후에 서비스가 교체될수도있기때문에 표준을 설정함.

	//등록
	public boolean insertBoard(BoardVO vo);
	//수정
	public boolean updateBoard(BoardVO vo);
	//삭제(회원탈퇴)
	public boolean deleteBoard(BoardVO vo);
	//단건조회
	public BoardVO getBoard(BoardVO vo);
	//전체조회
	public List<BoardVO> getBoardList(BoardVO vo);
	//건수조회
	public int getCnt(BoardVO vo);
}
