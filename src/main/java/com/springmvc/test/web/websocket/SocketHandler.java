package com.springmvc.test.web.websocket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.springmvc.test.web.websocket.UserDTO;
import com.springmvc.test.web.websocket.UserSearchDTO;
import com.springmvc.test.web.websocket.UserService;
import com.fasterxml.jackson.databind.ObjectMapper; // 스프링에서 json객체 변환시켜주는것은 jackson의  objectmapper를 이용

//spring4 websocket 사용. 기본원리는 javax websocket과 같음
public class SocketHandler extends TextWebSocketHandler implements InitializingBean {
	private final Logger logger = LogManager.getLogger(getClass());
	private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();

	@Resource
	private UserService userService;

	public SocketHandler() {
		super();
		this.logger.info("create SocketHandler instance!");
	}

	@Override
	// onClose
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		sessionSet.remove(session);
		this.logger.info("remove session!");
	}

	@Override
	// onOpen
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessionSet.add(session);
		this.logger.info("add session!");
	}

	@Override
	// onMessage , 클라이언트에서 받아온 메시지값을 처리하는 메서드.
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);
		this.logger.info("receive message:" + message.toString()); // json string을 vo로 변환
		// json string(클라이언트에서 json타입으로 받은 값 ) -> java object(dto) 로 변환, 반대로도 가능함.
		ObjectMapper mapper = new ObjectMapper();
		MsgVO msgvo = mapper.readValue((String) message.getPayload(), MsgVO.class); // String값을 읽어서 MsgVO에 담으라는 뜻 .
		UserDTO result = new UserDTO();															// 아까받아온 메시지의타입(JSON)을 JAVA OBJECT로 변환하는 작업.
	
		//서버에서 클라이언트로 데이터보낼띠 map형태로 보내면 좋음. 전송결과
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cmd",msgvo.getCmd());
		// cmd에 따라서 명령어 구분가능.
		if (msgvo.getCmd().equals("get")) {
			// 사용자조회
			UserDTO userDTO = new UserDTO();
			// 검색조건 없이 단건조회 하는 경우 , 검색이 안되게하게하기위해 0을 전달
			if(msgvo.getMsg() == null || msgvo.getMsg() =="") {			
				result.setName("결과없음");
				result.setRole("결과없음");
			}
			else {
				userDTO.setId(msgvo.getMsg());
				result = userService.getUser(userDTO);	
				if ( result == null || result.getName() == "") {
					result = new UserDTO();
					result.setName("결과없음");
					result.setRole("결과없음");
				}
			}
			// 요청을 보낸사람의 id를 알기 위해서 전달
			result.setId(msgvo.getId()); 
			map.put("msg",result); // 단건 조회결과를 msg에 담앗음
			// java object(DTO) -> json 구조의 string으로 변환
			//sendMessage(mapper.writeValueAsString(result));
		} else if (msgvo.getCmd().equals("all")) {
			// 전체조회 
			UserSearchDTO searchDTO = new UserSearchDTO();
			searchDTO.setStart(1);
			searchDTO.setEnd(16);
			//List<UserDTO> result = userService.getUsers(userDTO);
			
			map.put("msg", userService.getUsers(searchDTO)); // 전체조회결과를 msg에 담앗음
			// java object(DTO) -> json구조의 string으로 변환 
			//sendMessage(mapper.writeValueAsString(result));
		}
		sendMessage(mapper.writeValueAsString(map));
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		this.logger.error("web socket error!", exception);
	}

	@Override
	public boolean supportsPartialMessages() {
		this.logger.info("call method!");
		return super.supportsPartialMessages();
	}

	public void sendMessage(String message) {
		for (WebSocketSession session : this.sessionSet) {
			if (session.isOpen()) {
				try {
					session.sendMessage(new TextMessage(message));
				} catch (Exception ignored) {
					this.logger.error("fail to send message!", ignored);
				}
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread thread = new Thread() {
			int i = 0;
			@Override
			public void run() {
				while (true) {
					try {
						// sendMessage("send message index " + i++);
						Thread.sleep(1000); // 스레드가 1초간격으로 메시지 전송
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}
			}
		};
		//
		thread.start(); //메시지 전달.
	}
}
