package com.fet.telemedicine.backend.chat.message.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fet.telemedicine.backend.chat.message.repository.po.SequencePo;
import java.lang.String;
import java.util.List;

@Repository
public interface SequenceRepository extends MongoRepository<SequencePo, String> {

    List<SequencePo> findBySequenceName(String sequenceName);
}
