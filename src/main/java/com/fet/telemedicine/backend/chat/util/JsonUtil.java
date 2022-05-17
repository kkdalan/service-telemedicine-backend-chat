package com.fet.telemedicine.backend.chat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String toJson(T obj) {
	String json = null;
	try {
	    json = OBJECT_MAPPER.writeValueAsString(obj);
	} catch (Exception e) {
	    LOGGER.error("convert POJO to JSON failure", e);
	    throw new RuntimeException(e);
	}
	return json;
    }

    public static <T> T toJson(String json, Class<T> type) {
	T pojo = null;
	try {
	    pojo = OBJECT_MAPPER.readValue(json, type);
	} catch (Exception e) {
	    LOGGER.error("convert JSON to POJO failure", e);
	    throw new RuntimeException(e);
	}
	return pojo;
    }

}
