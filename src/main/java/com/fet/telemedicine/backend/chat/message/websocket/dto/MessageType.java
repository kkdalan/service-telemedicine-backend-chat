package com.fet.telemedicine.backend.chat.message.websocket.dto;

public enum MessageType {
    NEW_MESSAGE,
    JOIN_SUCCESS, 
    LEAVE, 
    ERROR, 
    FORBIDDEN, 
    ADD_CONTACT, 
    GET_CONTACTS
}
