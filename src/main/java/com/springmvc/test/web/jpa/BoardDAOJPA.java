package com.springmvc.test.web.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.springmvc.test.web.jpa.BoardVO;

@Repository
public class BoardDAOJPA {
	@PersistenceContext
	private EntityManager em;

	public boolean insertBoard(BoardVO vo) {
		System.out.println("===> JPA로 insertBoard() 기능 처리");
		int ck = vo.getSeq();
		System.out.println("1 ==== " + ck); // 순수한 엔티티의 Seq 값. 0
		em.persist(vo); // 영속성 엔티티, 영속성 컨테이너가 관리한다.
		System.out.println("2 ==== " + vo.getSeq()); // 영속성 엔티티의 Seq 값. 1 ~ n
		// 등록한 엔티티의 Seq 값이 초기 엔티티와 다르면 등록이 성공한 것이다. 초기 엔티티와 Seq 값이 같다면 제대로 등록되지 않은 것

		if (ck != vo.getSeq())
			return true;
		else
			return false;
	}

	public boolean updateBoard(BoardVO vo) {
		System.out.println("===> JPA로 updateBoard() 기능 처리");
		int ck = vo.getSeq();
		System.out.println("1 ==== " + ck); // 순수한 엔티티의 Seq 값. 0
		em.merge(vo);
		System.out.println("2 ==== " + vo.getSeq()); // 추가되는 Seq 값. 맨 마지막 Seq 값이다.

		if (ck != vo.getSeq())
			return true;
		else
			return false;
	}

	public boolean deleteBoard(BoardVO vo) {
		System.out.println("===> JPA로 deleteBoard() 기능 처리");
		int ck = vo.getSeq();

		em.remove(em.find(BoardVO.class, vo.getSeq()));

		if (ck != vo.getSeq())
			return true;
		else
			return false;
	}

	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JPA로 getBoard() 기능 처리");
		return (BoardVO) em.find(BoardVO.class, vo.getSeq());
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JPA로 getBoardList() 검색 기능 처리");
		TypedQuery<BoardVO> query;
		/*
		 * if ("title".equals(vo.getSearchCondition())) query =
		 * em.createQuery("from BoardVO b  where b.title = :title order by b.seq desc",
		 * BoardVO.class); else if ("content".equals(vo.getSearchCondition())) query =
		 * em.createQuery("from BoardVO b  where b.content = :title order by b.seq desc"
		 * , BoardVO.class); else query =
		 * em.createQuery("from BoardVO b  order by b.seq desc", BoardVO.class);
		 * if(vo.getSearchCondition() != null && !vo.getSearchCondition().equals(""))
		 * query.setParameter("title", vo.getSearchKeyword());
		 * query.setFirstResult(vo.getFirst()-1); query.setMaxResults(vo.getLast());
		 * return query.getResultList();
		 */
		return em.createQuery("from BoardVO b order by b.seq desc").getResultList();
	}

	public int getCount(BoardVO vo) {
		System.out.println("===> JPA로 getCount() 기능 처리");
		return ((Long) em.createQuery("select count(b) from BoardVO b").getSingleResult()).intValue();
	}

	public void deleteBoardList(ArrayList<String> seq) {

	}
}