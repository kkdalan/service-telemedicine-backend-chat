package com.fet.telemedicine.backend.chat.im.exception;

public class InstantMessengerException extends RuntimeException {

    private static final String MESSAGE = "Something went wrong when connecting to the XMPP server with username '%s'.";
    public InstantMessengerException(String username, Throwable e) {
        super(String.format(MESSAGE, username), e);
    }
}
