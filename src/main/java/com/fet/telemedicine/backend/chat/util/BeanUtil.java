package com.fet.telemedicine.backend.chat.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

    public static <T> T mapToBean(Map<Object, Object> map, Class<T> beanClass) {
	T beanInstance = null;
	try {
	    beanInstance = beanClass.newInstance();
	    Field[] beanFields = beanClass.getDeclaredFields();
	    if (ArrayUtil.isNotEmpty(beanFields)) {
		for (Field beanField : beanFields) {
		    Object beanFieldInstance = map.get(beanField.getName());
		    if (beanFieldInstance != null) {
			ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
		    }
		}
	    }
	} catch (Exception e) {
	    System.out.println("mapToBean error " + e);
	}
	return (T) beanInstance;
    }

    public static Map<String, Object> beanToMap(Object beanInstance) {

	if (beanInstance == null) {
	    return null;
	}

	Map<String, Object> map = new HashMap<String, Object>();
	try {
	    BeanInfo beanInfo = Introspector.getBeanInfo(beanInstance.getClass());
	    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	    for (PropertyDescriptor property : propertyDescriptors) {
		String key = property.getName();
		if (!key.equals("class")) {
		    Method getter = property.getReadMethod();
		    Object value = getter.invoke(beanInstance);
		    map.put(key, value);
		}
	    }
	} catch (Exception e) {
	    System.out.println("beanToMap error " + e);
	}

	return map;
    }

}
