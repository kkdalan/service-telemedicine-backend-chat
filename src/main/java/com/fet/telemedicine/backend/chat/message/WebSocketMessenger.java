package com.fet.telemedicine.backend.chat.message;

import javax.websocket.Session;

import com.fet.telemedicine.backend.chat.message.websocket.dto.WebSocketMessage;

public interface WebSocketMessenger {
    
    public static final String XMPP_WEB_SOCKET_MESSENGER = "XMPPWebSocketMessenger";

    void startSession(Session session, String username, String password);

    void sendMessage(WebSocketMessage message, Session session);

    void disconnect(Session session);

}