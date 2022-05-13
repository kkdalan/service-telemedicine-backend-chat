package com.fet.telemedicine.backend.chat.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ObjectUtils {

    public static String reflectToString(Object object) {
	return ToStringBuilder.reflectionToString(object);
    }

}
