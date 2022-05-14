package com.fet.telemedicine.backend.chat.message.websocket.support;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fet.telemedicine.backend.chat.message.websocket.dto.WebSocketMessage;

@Component
public class WebSocketMessageHelper {

    public static final Logger log = LoggerFactory.getLogger(WebSocketMessageHelper.class);

    public void send(Session session, WebSocketMessage websocketMessage) {
	try {
	    session.getBasicRemote().sendObject(websocketMessage);
	} catch (IOException | EncodeException e) {
	    log.error("WebSocket error, message {} was not sent.", websocketMessage.toString(), e);
	}
    }
}
