package com.fet.telemedicine.backend.chat.facade;

import javax.websocket.Session;

import com.fet.telemedicine.backend.chat.model.WebSocketMessage;

public interface InstantMessenger {
    
    public static final String XMPP_INSTANT_MESSENGER = "XMPPInstantMessenger";

    void startSession(Session session, String username, String password);

    void sendMessage(WebSocketMessage message, Session session);

    void disconnect(Session session);

}