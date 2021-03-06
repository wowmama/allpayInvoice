package com.fruitpay.allpayInvoice.builder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;
import com.fruitpay.allpayInvoice.machine.MachineType;
import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;

/**
 * Post參數Builder<br>
 * @author Churu
 *
 */
public class ParameterMapBuilder {
	private MachineType machineType;
	
	/**
	 * 依照傳入的MachineType決定為哪種Builder(開立、作廢、查詢...等)<br>
	 * @param machineType 開立類型
	 */
	public ParameterMapBuilder(MachineType machineType) {
		super();
		this.machineType = machineType;
	}
	
	/**
	 * 將傳入的PostParameterMap轉換為Post參數Map<br>
	 * @param postParameterMap
	 * @return {@code Map<String, String>} Post參數Map
	 */
	public Map<String, String> build(PostParameterMap postParameterMap) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException{
		Map<String, String> parameterMap = new TreeMap<String, String>();
		Field[] fields = postParameterMap.getClass().getDeclaredFields();
		for(Field field : fields){
			field.setAccessible(true);
			Object obj = field.get(postParameterMap);
			PostParameterName annotation = field.getDeclaredAnnotation(PostParameterName.class);
			
			if(obj == null){
				continue;
			}else if(field.isAnnotationPresent(PostParameterName.class)
					&& Arrays.asList(annotation.method()).contains(machineType)
					&& obj.getClass().isEnum()){
				Method method = obj.getClass().getMethod("value");
				method.setAccessible(true);
				String value = AllpayURLEncoder.encode(method.invoke(obj).toString(),"UTF-8");
				parameterMap.put(annotation.name(), value);
				
			}else if(field.isAnnotationPresent(PostParameterName.class)
					&& Arrays.asList(annotation.method()).contains(machineType)
					&& obj instanceof PostParameterMap){
				parameterMap.putAll(((PostParameterMap)obj).getParameterMap(machineType));

			}else if(field.isAnnotationPresent(PostParameterName.class)
					&& Arrays.asList(annotation.method()).contains(machineType)){
				String value = 
						obj instanceof Date ? String.valueOf(((Date)obj).getTime()/1000) : obj.toString() ;
				//在url encode之前，替換參數中的+為空白。根據allpay techsurppor給的參考範例修改。
				value = value.replace("+", " ");
				value = annotation.isEncode() ? AllpayURLEncoder.encode(value,"UTF-8") : value;
				parameterMap.put(annotation.name(), value);
			}
		}
		return parameterMap;
	}
}
