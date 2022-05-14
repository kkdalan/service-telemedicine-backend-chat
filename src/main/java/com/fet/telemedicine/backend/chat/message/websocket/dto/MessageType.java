package com.fet.telemedicine.backend.chat.message.websocket.dto;

public enum MessageType {
    OLD_MESSAGE,
    NEW_MESSAGE,
    JOIN_SUCCESS, 
    LEAVE, 
    ERROR, 
    FORBIDDEN, 
    ADD_CONTACT, 
    GET_CONTACTS,
    NEW_INSTANCE_ROOM,
    NEW_RESERVED_ROOM,
}
