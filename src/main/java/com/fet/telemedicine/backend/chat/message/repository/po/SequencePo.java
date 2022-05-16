package com.fet.telemedicine.backend.chat.message.repository.po;

import java.math.BigInteger;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
public class SequencePo {
    @Id
    private ObjectId id;
    private String sequenceName;
    private BigInteger sequenceValue = BigInteger.ZERO;

    public SequencePo() {
    }
    
    public SequencePo(String sequenceName, BigInteger sequenceValue) {
	this.sequenceName = sequenceName;
	this.sequenceValue = sequenceValue;
    }

    public ObjectId getId() {
	return id;
    }

    public void setId(ObjectId id) {
	this.id = id;
    }

    public String getSequenceName() {
	return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
	this.sequenceName = sequenceName;
    }

    public BigInteger getSequenceValue() {
	return sequenceValue;
    }

    public void setSequenceValue(BigInteger sequenceValue) {
	this.sequenceValue = sequenceValue;
    }

}
