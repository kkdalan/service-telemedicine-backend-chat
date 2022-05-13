package com.fet.telemedicine.backend.chat.websocket.support;

import com.fet.telemedicine.backend.chat.message.model.InstantMessage;
import com.google.gson.Gson;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class InstantMessageEncoder implements Encoder.Text<InstantMessage> {
    @Override
    public String encode(InstantMessage message) {
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
