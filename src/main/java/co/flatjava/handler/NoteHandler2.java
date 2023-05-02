package co.flatjava.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j;

@Log4j
@EnableWebSocket
@Component
public class NoteHandler2 extends TextWebSocketHandler{
	
}
