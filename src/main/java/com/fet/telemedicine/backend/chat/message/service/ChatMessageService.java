package com.fet.telemedicine.backend.chat.message.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.fet.telemedicine.backend.chat.message.repository.po.MessagePo;

public interface ChatMessageService {

    List<MessagePo> searchMessageHistory(BigInteger messageFrom, BigInteger messageTo);

    List<MessagePo> searchMessageHistory(BigInteger messageFrom);

    void saveMessageToHistory(MessagePo messagePo);
}
