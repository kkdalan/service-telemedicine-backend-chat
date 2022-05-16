package com.fet.telemedicine.backend.chat.message.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fet.telemedicine.backend.chat.message.repository.SequenceRepository;
import com.fet.telemedicine.backend.chat.message.repository.po.SequencePo;
import com.fet.telemedicine.backend.chat.message.service.MongoSequenceService;

@Service
public class MongoSequenceServiceImpl implements MongoSequenceService {

    @Autowired
    private SequenceRepository sequenceRepository;

    @Override
    public BigInteger getNextSequenceValue(String sequenceName) {
	SequencePo currSequence = null;
	List<SequencePo> targetList = sequenceRepository.findBySequenceName(sequenceName);
	if (CollectionUtils.isEmpty(targetList)) {
	    currSequence = new SequencePo(sequenceName, BigInteger.ZERO);
	} else {
	    if (targetList.size() == 1) {
		currSequence = targetList.get(0);
	    } else {
		throw new RuntimeException("Duplicate sequence names found: " + sequenceName);
	    }
	}
	BigInteger currSequenceValue = currSequence.getSequenceValue();
	if (currSequenceValue == null) {
	    throw new RuntimeException("Sequence value is null at sequence: " + sequenceName);
	}
	currSequence.setSequenceValue(currSequenceValue.add(BigInteger.ONE));
	sequenceRepository.save(currSequence);
	return currSequence.getSequenceValue();
    }

}