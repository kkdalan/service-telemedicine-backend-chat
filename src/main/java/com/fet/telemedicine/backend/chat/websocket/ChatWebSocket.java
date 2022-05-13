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
import com.fet.telemedicine.backend.chat.facade.XMPPFacade;
import com.fet.telemedicine.backend.chat.model.WebsocketMessage;
import com.fet.telemedicine.backend.chat.websocket.support.WebsocketMessageDecoder;
import com.fet.telemedicine.backend.chat.websocket.support.WebsocketMessageEncoder;

@ServerEndpoint(value = "/chat/{username}/{password}", decoders = WebsocketMessageDecoder.class, encoders = WebsocketMessageEncoder.class)
public class ChatWebSocket {

    public static final Logger log = LoggerFactory.getLogger(ChatWebSocket.class);
  
    private final XMPPFacade xmppFacade;

    public ChatWebSocket() {
        this.xmppFacade = (XMPPFacade) SpringContext.getApplicationContext().getBean("XMPPFacade");
    }

    @OnOpen
    public void open(Session session, @PathParam("username") String username, @PathParam("password") String password) {
        xmppFacade.startSession(session, username, password);
    }

    @OnMessage
    public void handleMessage(WebsocketMessage message, Session session) {
        xmppFacade.sendMessage(message, session);
    }

    @OnClose
    public void close(Session session) {
        xmppFacade.disconnect(session);
    }

    @OnError
    public void onError(Throwable e, Session session) {
        log.debug(e.getMessage());
        xmppFacade.disconnect(session);
    }
}
