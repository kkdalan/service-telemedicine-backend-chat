package com.fet.telemedicine.backend.chat.websocket.support;

import com.fet.telemedicine.backend.chat.message.model.InstantMessage;
import com.google.gson.Gson;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class InstantMessageDecoder implements Decoder.Text<InstantMessage> {

    @Override
    public InstantMessage decode(String message) {
        Gson gson = new Gson();
        return gson.fromJson(message, InstantMessage.class);
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
