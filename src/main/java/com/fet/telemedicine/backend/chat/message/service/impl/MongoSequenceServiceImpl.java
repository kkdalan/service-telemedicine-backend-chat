package com.fet.telemedicine.backend.chat.message.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.data.mongodb.core.script.NamedMongoScript;
import org.springframework.stereotype.Service;

import com.fet.telemedicine.backend.chat.message.service.MongoSequenceService;

@Service
public class MongoSequenceServiceImpl implements MongoSequenceService {

    private static final String SCRIPT_NAME = "getNextSequenceValue";
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BigInteger getNextSequenceValue(String sequenceName) {

	ScriptOperations scriptOps = mongoTemplate.scriptOps();

	if (!scriptOps.exists(SCRIPT_NAME)) {
	    ExecutableMongoScript script = new ExecutableMongoScript(javascriptCode());
	    scriptOps.execute(script, "directly execute script");
	    scriptOps.register(new NamedMongoScript(SCRIPT_NAME, script));
	}
	BigInteger sequenceValue = (BigInteger) scriptOps.call(SCRIPT_NAME, sequenceName);
	
	return sequenceValue;
    }
    
    /*
    function getNextSequenceValue(sequenceName){
	   var sequenceDocument = db.sequence.findAndModify({
	      query:{_id: sequenceName },
	      update: {$inc:{sequence_value:1}},
	      new:true
	   });
	   return sequenceDocument.sequence_value;
	}
     */
    private String javascriptCode() {
	StringBuilder sb = new StringBuilder();
	sb.append("function getNextSequenceValue(sequenceName){")
	  .append("  var sequenceDocument = db.sequence.findAndModify({")
	  .append("    query:{_id: sequenceName },")
	  .append("    update: {$inc:{sequence_value:1}},")
	  .append("    new:true")
	  .append("  });")
	  .append("  return sequenceDocument.sequence_value;")
	  .append("}");
	return sb.toString();
}
}
