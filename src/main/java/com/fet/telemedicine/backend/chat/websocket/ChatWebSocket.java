package com.fet.telemedicine.backend.chat.websocket;

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
import com.fet.telemedicine.backend.chat.facade.InstantMessenger;
import com.fet.telemedicine.backend.chat.model.WebSocketMessage;
import com.fet.telemedicine.backend.chat.websocket.support.WebSocketMessageDecoder;
import com.fet.telemedicine.backend.chat.websocket.support.WebSocketMessageEncoder;

@ServerEndpoint(value = "/chat/{username}/{password}", decoders = WebSocketMessageDecoder.class, encoders = WebSocketMessageEncoder.class)
public class ChatWebSocket {

    public static final Logger log = LoggerFactory.getLogger(ChatWebSocket.class);

    private final InstantMessenger instantMessenger;

    public ChatWebSocket() {
	this.instantMessenger = (InstantMessenger) SpringContext.getApplicationContext()
		.getBean(InstantMessenger.XMPP_INSTANT_MESSENGER);
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
