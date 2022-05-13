package com.fet.telemedicine.backend.chat.websocket.support;

import com.fet.telemedicine.backend.chat.model.WebSocketMessage;
import com.google.gson.Gson;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class WebSocketMessageDecoder implements Decoder.Text<WebSocketMessage> {

    @Override
    public WebSocketMessage decode(String message) {
        Gson gson = new Gson();
        return gson.fromJson(message, WebSocketMessage.class);
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
