package com.fet.telemedicine.backend.chat.util;

import java.lang.reflect.Array;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class ArrayUtil {

    public static boolean isEmpty(Object[] array) {
	return ArrayUtils.isEmpty(array);
    }

    public static boolean isNotEmpty(Object[] array) {
	return !isEmpty(array);
    }

    public static <T> T[] toArray(List<T> list, Class<T> clazz) {
	if (list == null) {
	    return null;
	}
	@SuppressWarnings("unchecked")
	T[] array = (T[]) Array.newInstance(clazz, list.size());
	list.toArray(array);
	return array;
    }

}
