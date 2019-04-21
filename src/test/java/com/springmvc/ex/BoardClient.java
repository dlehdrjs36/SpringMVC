package com.springmvc.ex;

import java.sql.SQLException;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springmvc.test.web.rest.RestDTO;
import com.springmvc.test.web.rest.RestService;


@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(locations = {	"classpath:spring/common-context.xml", 
									"classpath:spring/datasource-context.xml", 
									"classpath:spring/mybatis-context.xml", 
									"classpath:spring/transaction-context.xml"})*/
//@ContextConfiguration(locations = "classpath:spring/*-context.xml")
//@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/*-context.xml")
@ContextConfiguration(locations = "file:src/test/resources/spring/*-context.xml")
public class BoardClient {
	@Autowired
	ApplicationContext context;
	
	/*@Autowired
	SqlSession session;*/

	@Autowired
	RestService restService;
	
/*	@Test @Ignore
	public void insertSEQ() {
		int cnt = session.update("userNS.insertPerson", new PersonVO("9407231634744", "�Ѹ�", "���", "1234"));
		System.out.println(cnt);
	}
	
	@Test @Ignore
	public void updateUserTest() {
		service.updateUser(new UserVO("dooly", "����3", "��3", "���3"));
		System.out.println(service.getUser("dooly"));
	}

	@Test
	public void deleteUserTest() {
		service.deleteUser("dooly");

		for (UserVO user : service.getUserList()) {
			System.out.println(user);
		}
	}
	*/

/*	@Test
	@Ignore
	public void insertUserTest() {
		service.insertUser(new UserVO("polar", "����", "��", "���"));

		for (UserVO user : service.getUserList()) {
			System.out.println(user);
		}
	}
*/
	@Test
	//@Ignore
	public void getUserTest() {
		RestDTO param = new RestDTO();
		param.setId("user19");
/*		BoardVO boardVO = session.selectOne("board.getBoard",param);
		System.out.println(boardVO);*/
		RestDTO userVO = restService.getUser(param);
		System.out.println("getUser 결과 : " + userVO);
		
		//테스트 결과가 정확하면 정상수행(초록) 표시, 틀리면 실패(빨강) 표시가 출력된다.
		assertEquals("dsad", userVO.getName());
		
	}

	@Test
//	@Ignore
	public void dataSourceTest() {
		DataSource ds = (DataSource) context.getBean("dataSource");
		try {
			System.out.println(ds.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
