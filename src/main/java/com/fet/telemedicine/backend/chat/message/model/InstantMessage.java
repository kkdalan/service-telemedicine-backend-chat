package com.fet.telemedicine.backend.chat.message.model;

import com.fet.telemedicine.backend.chat.utils.ObjectUtils;

public class InstantMessage {

    String from;
    String to;
    String content;
    MessageType messageType;

    private InstantMessage() {
    }

    @Override
    public String toString() {
	return ObjectUtils.reflectToString(this);
    }

    public String getFrom() {
	return from;
    }

    public void setFrom(String from) {
	this.from = from;
    }

    public String getTo() {
	return to;
    }

    public void setTo(String to) {
	this.to = to;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public MessageType getMessageType() {
	return messageType;
    }

    public void setMessageType(MessageType messageType) {
	this.messageType = messageType;
    }

    public static Builder builder() {
	return new InstantMessage.Builder();
    }

    public static class Builder {
	String from;
	String to;
	String content;
	MessageType messageType;

	public InstantMessage build() {
	    InstantMessage websocketMessage = new InstantMessage();
	    websocketMessage.setFrom(from);
	    websocketMessage.setTo(to);
	    websocketMessage.setContent(content);
	    websocketMessage.setMessageType(messageType);
	    return websocketMessage;
	}

	public Builder from(String from) {
	    this.from = from;
	    return this;
	}

	public Builder to(String to) {
	    this.to = to;
	    return this;
	}

	public Builder content(String content) {
	    this.content = content;
	    return this;
	}

	public Builder messageType(MessageType messageType) {
	    this.messageType = messageType;
	    return this;
	}

    }
}
