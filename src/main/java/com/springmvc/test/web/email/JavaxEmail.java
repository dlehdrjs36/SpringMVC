package com.springmvc.test.web.email;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;


/***
 * 커스텀 뷰 - 이메일 전송
 *
 */
@Component
public class JavaxEmail extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		res.setCharacterEncoding("euc-kr");
		
		// Recipient's email ID needs to be mentioned.
		String to = "slsldkf123@naver.com";// change accordingly
		// Sender's email ID needs to be mentioned
		String from = "slsldkf1234@gmail.com"; // change accordingly
		final String username = "slsldkf1234@gmail.com"; // change accordingly
		final String password = "gmhrqwtrhpynimhl"; // change accordingly
		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// Set Subject: header field
			message.setSubject("gmail 전송 테스트 - javax email을 이용한 이메일전송");
			// Now set the actual message
			message.setText("Hello, this is sample for to check send email using JavaMailAPI ");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		PrintWriter out = res.getWriter();
		out.print("<html>"
				+ "<head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=euc-kr\">"
				+ "<body><p>javax를 이용한 이메일 전송완료</p><br>"
				 );
		out.print("<a href='./'>메인</a></body></html>");
		out.flush();
		out.close();
	}
}
