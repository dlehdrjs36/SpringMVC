package com.springmvc.test.web.email;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/***
 * 커스텀 뷰 - 이메일 전송
 *
 */
@Component
public class CommonEmail extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		System.out.println("11111111111133"+model.get("a") );
		SimpleEmail email = new SimpleEmail();

		// 메일서버 설정
		email.setCharset("euc-kr"); // 한글 인코딩 
		email.setHostName("smtp.gmail.com"); // 보내는 메일(SMTP) 서버
		email.setSmtpPort(465); // 포트
		email.setAuthenticator(new DefaultAuthenticator("slsldkf1234@gmail.com", "gmhrqwtrhpynimhl")); // 인증
		email.setSSLOnConnect(true); // SSL 필요
		try {
			email.addTo("slsldkf123@naver.com", "홍길동"); // 수신자 추가
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.setFrom("slsldkf1234@gmail.com", "Me"); // 보내는 사람
		} catch (EmailException e) {
			e.printStackTrace();
		}
		email.setSubject("gmail 전송 테스트 - common email를 이용한 이메일전송"); // 메일 제목
		// 메일 내용
		email.setContent("simple 메일 Test입니다", "text/plain; charset=euc-kr");
		try {
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
