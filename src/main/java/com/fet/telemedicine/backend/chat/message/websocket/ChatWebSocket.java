package com.fet.telemedicine.backend.chat.message.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fet.telemedicine.backend.chat.config.SpringContext;
import com.fet.telemedicine.backend.chat.message.WebSocketMessenger;
import com.fet.telemedicine.backend.chat.message.websocket.dto.WebSocketMessage;
import com.fet.telemedicine.backend.chat.message.websocket.support.WebSockerMessageDecoder;
import com.fet.telemedicine.backend.chat.message.websocket.support.WebSocketMessageEncoder;

@ServerEndpoint(value = "/chat/{username}/{password}", decoders = WebSockerMessageDecoder.class, encoders = WebSocketMessageEncoder.class)
public class ChatWebSocket {

    public static final Logger log = LoggerFactory.getLogger(ChatWebSocket.class);

    private final WebSocketMessenger instantMessenger;

    public ChatWebSocket() {
	this.instantMessenger = (WebSocketMessenger) SpringContext.getApplicationContext()
		.getBean(WebSocketMessenger.XMPP_WEB_SOCKET_MESSENGER);
    }

    @OnOpen
    public void open(Session session, @PathParam("username") String username, @PathParam("password") String password) {
	instantMessenger.startSession(session, username, password);
    }

    @OnMessage
    public void handleMessage(WebSocketMessage message, Session session) {
	instantMessenger.sendMessage(message, session);
    }

    @OnClose
    public void close(Session session) {
	instantMessenger.disconnect(session);
    }

    @OnError
    public void onError(Throwable e, Session session) {
	log.debug(e.getMessage());
	instantMessenger.disconnect(session);
    }
}
