package com.fet.telemedicine.backend.chat.model;


public class WebsocketMessage {

  String from;
  String to;
  String content;
  MessageType messageType;

  private WebsocketMessage() {}

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
    return new WebsocketMessage.Builder();
  }

  public static class Builder {
    String from;
    String to;
    String content;
    MessageType messageType;

    public WebsocketMessage build() {
      WebsocketMessage websocketMessage = new WebsocketMessage();
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
