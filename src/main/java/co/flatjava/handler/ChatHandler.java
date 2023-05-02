package co.flatjava.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import co.flatjava.domain.MemberVO;
import co.flatjava.domain.NoteVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class ChatHandler extends TextWebSocketHandler{

	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		log.info("현재 접속자 수 " + sessions.size());
		log.info("현재 접속자 정보 " );
		sessions.forEach(log::warn);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.warn(session.getId() + "로그아웃");
		sessions.remove(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {		
		String msg = message.getPayload();
		// String의 msg는 ID와 내용을 문자열로 담아져 있는 형태이다.
		// 이때 문자열을 분리하는 작업을 해야하는데 Gson을 활용한다.
		// Gson 인스턴스 객체
		Gson gson = new Gson();
		NoteVO vo = gson.fromJson(msg, NoteVO.class);
		MemberVO memberVO = (MemberVO)session.getAttributes().get("member");
		// map 객채에 gson.fromJson으로 msg안에 Map.class 로 나눔.
		Map<?, ?> map = gson.fromJson(msg, Map.class);
		log.warn(map.get("msg"));
		log.warn(map.get("id"));
		log.warn(msg);
		log.warn(session.getAttributes().get("member"));
		for(WebSocketSession s : sessions) {
			s.sendMessage(new TextMessage(session.getId() + " :: " + msg));
		}
	}
}
