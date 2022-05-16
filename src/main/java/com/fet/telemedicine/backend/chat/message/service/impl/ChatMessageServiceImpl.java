package com.fet.telemedicine.backend.chat.message.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.telemedicine.backend.chat.message.repository.MessageRepository;
import com.fet.telemedicine.backend.chat.message.repository.po.MessagePo;
import com.fet.telemedicine.backend.chat.message.service.ChatMessageService;
import com.fet.telemedicine.backend.chat.message.service.MongoSequenceService;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MongoSequenceService mongoSequenceService;

    @Override
    public List<MessagePo> findMessageHistory(BigInteger messageFrom, BigInteger messageTo) {
	return messageRepository.findByMessageFromAndMessageToOrderByMessageIdAsc(messageFrom, messageTo);
    }

    @Override
    public void saveMessageToHistory(MessagePo messagePo) {
	try {
	    BigInteger newMessageId = mongoSequenceService.getNextSequenceValue("message_id");
	    messagePo.setMessageId(newMessageId);
	    messageRepository.save(messagePo);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
