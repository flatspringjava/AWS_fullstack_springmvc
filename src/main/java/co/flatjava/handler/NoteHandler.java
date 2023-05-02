package co.flatjava.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import co.flatjava.domain.MemberVO;
import co.flatjava.domain.NoteVO;
import co.flatjava.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@EnableWebSocket
public class NoteHandler extends TextWebSocketHandler {
//	// 접속자 관리 객체
//	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
//	@Autowired
//	private NoteService noteService;
//
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		log.warn("입장한 사람 : " + getIdBySession(session));
//		sessions.add(session);
//		log.info("현재 접속자 수 " + sessions.size());
//		log.info("현재 접속자 정보 ");
//		log.warn(sessions.stream().map(s->getIdBySession(s)).collect(Collectors.toList()));
//	}
//
//	@Override
//	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {		
//		sessions.remove(session);
//	}
//
//	@Override
//	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		String reviever = message.getPayload(); // js, ws.send() 수신자의 정보가 필요. 
//		
//		String sender = getIdBySession(session);
//		List<NoteVO> list0 = noteService.getSendList(sender);
//		List<NoteVO> list1 = noteService.getReceiveList(reviever);
//		List<NoteVO> list2 = noteService.getReceiveUncheckedList(reviever);
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("sendList", list0);
//		map.put("receiveList", list1);
//		map.put("receiveUncheckedList", list2);
//		map.put("sender", sender);
//		
//		Gson gson = new Gson();				
//		for(WebSocketSession s : sessions) {
//			if(reviever.equals(getIdBySession(s)) || session == s) {
//				s.sendMessage(new TextMessage(gson.toJson(map)));
//			}
//		}
//		// id1이 id2에게 전송 하려면 id2는 접속중 이어야한다.  
//	}
//	private String getIdBySession(WebSocketSession session) {
//		Object obj = session.getAttributes().get("member");
//		String id = null;
//		if(obj != null && obj instanceof MemberVO) {
//			id = ((MemberVO)obj).getId();
//		}
//		return id;
//	}
}
