package com.fet.telemedicine.backend.chat.message.service;

import java.math.BigInteger;
import java.util.List;

import com.fet.telemedicine.backend.chat.message.repository.po.MessagePo;

public interface ChatMessageService {

    List<MessagePo> findMessageHistory(BigInteger messageFrom, BigInteger messageTo);

    void saveMessageToHistory(MessagePo messagePo);
}
