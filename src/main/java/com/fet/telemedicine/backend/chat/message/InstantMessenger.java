package com.fet.telemedicine.backend.chat.message;

import javax.websocket.Session;

import com.fet.telemedicine.backend.chat.message.model.InstantMessage;

public interface InstantMessenger {
    
    public static final String XMPP_INSTANT_MESSENGER = "XMPPInstantMessenger";

    void startSession(Session session, String username, String password);

    void sendMessage(InstantMessage message, Session session);

    void disconnect(Session session);

}