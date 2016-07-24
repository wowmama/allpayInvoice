package com.fruitpay.allpayInvoice.interfaces;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;

public abstract class PostParameterMap {
	
	public Map<String, String> getParameterMap() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		Map<String, String> parameterMap = new TreeMap<String, String>();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field : fields){
			field.setAccessible(true);
			Object obj = field.get(this);
			
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
	
	public Map<String, String> getUrlEncodeParameterMap() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException {
		Map<String, String> parameterMap = new TreeMap<String, String>();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field : fields){
			field.setAccessible(true);
			Object obj = field.get(this);
			
			if(obj == null){
				continue;
			}else if(field.isAnnotationPresent(PostParameterName.class)
					&& obj.getClass().isEnum()){
				Method method = obj.getClass().getMethod("value");
				method.setAccessible(true);
				PostParameterName annotation = field.getDeclaredAnnotation(PostParameterName.class);
				
				String value = method.invoke(obj).toString();
				parameterMap.put(annotation.name(), annotation.urlEncode() ? AllpayURLEncoder.encode(value,"UTF-8") : value);
				
			}else if(field.isAnnotationPresent(PostParameterName.class)){
				PostParameterName annotation = field.getDeclaredAnnotation(PostParameterName.class);
				String value = 
						obj instanceof Date ? String.valueOf(((Date)obj).getTime()/1000) : obj.toString() ;
				parameterMap.put(annotation.name(), annotation.urlEncode() ? AllpayURLEncoder.encode(value,"UTF-8") : value);
			
			}else if(obj instanceof PostParameterMap){
				parameterMap.putAll(((PostParameterMap)obj).getUrlEncodeParameterMap());
			}
		}
		return parameterMap;
	}
}
