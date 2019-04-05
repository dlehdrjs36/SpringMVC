package com.springmvc.test.web.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session; // tomcat에서 쓰는 session과 websocket의 session은 다름.
import javax.websocket.server.ServerEndpoint;
/***
 * javax websocket 사용
 * @author User
 *
 */
@ServerEndpoint("/broadcasting")
public class Broadsocket {
	//클라이언트가 접속요청을하면 서버에서는 그 소켓과연결할 소켓을 생성. Session은 소켓과 같은 의미로 생각. 중복소켓이있으면 안되니까 Set사용. 
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>()); //Set<Session> == 채팅방

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		System.out.println(message);
		synchronized (clients) {
			// Iterate over the connected sessions
			// and broadcast the received message
			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(message); // 전송받은 메시지를 다른 모든 클라이언트(소켓)에게 보내줌.
				}
			}
		}
	}

	@OnOpen //클라이언트로부터 요청이 들어오면
	public void onOpen(Session session) { // socket == session
		// Add session to the connected sessions set
		System.out.println(session);
		clients.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		// Remove session from the connected sessions set
		clients.remove(session);
	}
}
