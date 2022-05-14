package com.fet.telemedicine.backend.chat.message.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fet.telemedicine.backend.chat.message.repository.po.MessagePo;

@Repository
public interface GroupMessageRepository extends MongoRepository<MessagePo, BigInteger> {

}
