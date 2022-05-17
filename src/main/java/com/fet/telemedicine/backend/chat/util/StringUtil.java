package com.fet.telemedicine.backend.chat.util;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil {

    public static final String SEPARATOR = String.valueOf((char) 29);

    public static boolean isEmpty(String str) {
	if (str != null) {
	    str = str.trim();
	}
	return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
	return !isEmpty(str);
    }

    public static String[] splitString(String str, String separatorChar) {
	return StringUtils.split(str, separatorChar);
    }

}
