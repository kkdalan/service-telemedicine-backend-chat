package com.fet.telemedicine.backend.chat.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

    public static <T> T mapToBean(Map<Object, Object> map, Class<T> clazz) {
	T obj = null;
	try {
	    BeanInfo beanInfo = Introspector.getBeanInfo(clazz.getClass());
	    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

	    for (PropertyDescriptor property : propertyDescriptors) {
		String key = property.getName();

		if (map.containsKey(key)) {
		    Object value = map.get(key);
		    Method setter = property.getWriteMethod();
		    setter.invoke(obj, value);
		}
	    }
	} catch (Exception e) {
	    System.out.println("transMap2Bean Error " + e);
	}
	return (T) obj;
    }

    public static Map<String, Object> beanToMap(Object obj) {

	if (obj == null) {
	    return null;
	}

	Map<String, Object> map = new HashMap<String, Object>();
	try {
	    BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
	    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	    for (PropertyDescriptor property : propertyDescriptors) {
		String key = property.getName();
		if (!key.equals("class")) {
		    Method getter = property.getReadMethod();
		    Object value = getter.invoke(obj);
		    map.put(key, value);
		}
	    }
	} catch (Exception e) {
	    System.out.println("transBean2Map Error " + e);
	}

	return map;
    }

}
