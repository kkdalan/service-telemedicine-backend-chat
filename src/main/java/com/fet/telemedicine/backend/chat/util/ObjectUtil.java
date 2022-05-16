package com.fet.telemedicine.backend.chat.util;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ObjectUtil {

    public static String reflectToString(Object object) {
	return ToStringBuilder.reflectionToString(object);
    }

}
