package com.fet.telemedicine.backend.chat.exception;

public class MessageException extends RuntimeException {

    private static final String MESSAGE = "Something went wrong when connecting to the chat server with username '%s'.";
    
    public MessageException(String username, Throwable e) {
        super(String.format(MESSAGE, username), e);
    }
}
