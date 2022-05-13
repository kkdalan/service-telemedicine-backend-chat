package com.fet.telemedicine.backend.chat.im;

import javax.websocket.Session;

import com.fet.telemedicine.backend.chat.model.WebsocketMessage;

public interface InstantMessenger {

    void startSession(Session session, String username, String password);

    void sendMessage(WebsocketMessage message, Session session);

    void disconnect(Session session);

}