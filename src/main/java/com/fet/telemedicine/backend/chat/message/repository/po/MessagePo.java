package com.fet.telemedicine.backend.chat.message.repository.po;

import java.math.BigInteger;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class MessagePo {
    @Id
    private ObjectId id;
    private BigInteger messageId;
    private BigInteger messageFrom;
    private BigInteger messageTo;
    private String content;
    private Date createAt;

    public MessagePo() {
    }

    public ObjectId getId() {
	return id;
    }

    public void setId(ObjectId id) {
	this.id = id;
    }

    public BigInteger getMessageId() {
	return messageId;
    }

    public void setMessageId(BigInteger messageId) {
	this.messageId = messageId;
    }

    public BigInteger getMessageFrom() {
	return messageFrom;
    }

    public void setMessageFrom(BigInteger messageFrom) {
	this.messageFrom = messageFrom;
    }

    public BigInteger getMessageTo() {
	return messageTo;
    }

    public void setMessageTo(BigInteger messageTo) {
	this.messageTo = messageTo;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public Date getCreateAt() {
	return createAt;
    }

    public void setCreateAt(Date createAt) {
	this.createAt = createAt;
    }

}
