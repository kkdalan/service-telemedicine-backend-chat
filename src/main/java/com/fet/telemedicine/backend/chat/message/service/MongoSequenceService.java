package com.fet.telemedicine.backend.chat.message.service;

import java.math.BigInteger;

public interface MongoSequenceService {

    BigInteger getNextSequenceValue(String sequenceName);
}
