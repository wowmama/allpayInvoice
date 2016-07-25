package com.fruitpay.allpayInvoice;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;

public class ParameterMapBuilder {
	public Map<String, String> build(PostParameterMap postParameterMap) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException{
		Map<String, String> parameterMap = new TreeMap<String, String>();
		Field[] fields = postParameterMap.getClass().getDeclaredFields();
		for(Field field : fields){
			field.setAccessible(true);
			Object obj = field.get(postParameterMap);
			
			if(obj == null){
				continue;
			}else if(field.isAnnotationPresent(PostParameterName.class)
					&& obj.getClass().isEnum()){
				Method method = obj.getClass().getMethod("value");
				method.setAccessible(true);
				PostParameterName annotation = field.getDeclaredAnnotation(PostParameterName.class);
				parameterMap.put(annotation.name(), method.invoke(obj).toString());
				
			}else if(field.isAnnotationPresent(PostParameterName.class)){
				PostParameterName annotation = field.getDeclaredAnnotation(PostParameterName.class);
				String value = 
						obj instanceof Date ? String.valueOf(((Date)obj).getTime()/1000) : obj.toString() ;
				parameterMap.put(annotation.name(), value);
			}else if(obj instanceof PostParameterMap){
				parameterMap.putAll(((PostParameterMap)obj).getParameterMap());
			}
		}
		return parameterMap;
	}
}
