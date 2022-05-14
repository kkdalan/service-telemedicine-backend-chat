package com.fet.telemedicine.backend.chat.message.repository.po;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
public class SequencePo {
    @Id
    private BigInteger sequenceName;
    private BigInteger sequenceValue;

    public SequencePo() {
    }

    public BigInteger getSequenceName() {
	return sequenceName;
    }

    public void setSequenceName(BigInteger sequenceName) {
	this.sequenceName = sequenceName;
    }

    public BigInteger getSequenceValue() {
	return sequenceValue;
    }

    public void setSequenceValue(BigInteger sequenceValue) {
	this.sequenceValue = sequenceValue;
    }

}
