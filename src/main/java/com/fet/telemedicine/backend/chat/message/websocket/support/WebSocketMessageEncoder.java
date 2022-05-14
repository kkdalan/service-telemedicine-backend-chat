package com.fet.telemedicine.backend.chat.message.websocket.support;

import com.fet.telemedicine.backend.chat.message.websocket.dto.WebSocketMessage;
import com.google.gson.Gson;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WebSocketMessageEncoder implements Encoder.Text<WebSocketMessage> {
    @Override
    public String encode(WebSocketMessage message) {
        Gson gson = new Gson();
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
