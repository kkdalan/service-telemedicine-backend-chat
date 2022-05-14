package com.fet.telemedicine.backend.chat.message.repository.po;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "group_message")
public class GroupMessagePo {
    @Id
    private GroupMessageKey groupMessageKey;
    private BigInteger userId;
    private String content;
    private Date createAt;

    public GroupMessagePo() {
    }

    static class GroupMessageKey implements Serializable {

	private BigInteger groupId;
	private BigInteger messageId;

	public BigInteger getGroupId() {
	    return groupId;
	}

	public void setGroupId(BigInteger groupId) {
	    this.groupId = groupId;
	}

	public BigInteger getMessageId() {
	    return messageId;
	}

	public void setMessageId(BigInteger messageId) {
	    this.messageId = messageId;
	}

    }

    public GroupMessageKey getGroupMessageKey() {
	return groupMessageKey;
    }

    public void setGroupMessageKey(GroupMessageKey groupMessageKey) {
	this.groupMessageKey = groupMessageKey;
    }

    public BigInteger getUserId() {
	return userId;
    }

    public void setUserId(BigInteger userId) {
	this.userId = userId;
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
