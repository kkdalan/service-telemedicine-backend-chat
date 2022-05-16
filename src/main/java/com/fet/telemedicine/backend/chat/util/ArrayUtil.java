package com.fet.telemedicine.backend.chat.util;

import java.lang.reflect.Array;
import java.util.List;

public class ArrayUtil {

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
