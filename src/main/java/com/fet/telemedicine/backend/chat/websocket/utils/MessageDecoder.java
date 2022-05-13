package com.fet.telemedicine.backend.chat.websocket.utils;

import com.fet.telemedicine.backend.chat.model.WebsocketMessage;
import com.google.gson.Gson;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<WebsocketMessage> {

    @Override
    public WebsocketMessage decode(String message) {
        Gson gson = new Gson();
        return gson.fromJson(message, WebsocketMessage.class);
    }

    @Override
    public boolean willDecode(String message) {
        return (message != null);
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
