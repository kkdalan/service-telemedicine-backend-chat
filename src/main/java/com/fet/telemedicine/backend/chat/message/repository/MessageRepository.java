package com.fet.telemedicine.backend.chat.message.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fet.telemedicine.backend.chat.message.repository.po.MessagePo;

@Repository
public interface MessageRepository extends MongoRepository<MessagePo, BigInteger> {

    List<MessagePo> findByMessageFromAndMessageToOrderByMessageIdAsc(BigInteger messageFrom, BigInteger messageTo);

}
