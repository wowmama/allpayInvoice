package com.fruitpay.allpayInvoice.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

public class CheckValueBuilder {
	private String hashKey;
	private String hashIV;
	private Map<String, String> parameterMap;
	
	public String build() throws UnsupportedEncodingException{
		
		StringBuilder sb = new StringBuilder();
		String[] keys = (String[]) parameterMap.keySet().toArray();
		Arrays.sort(keys);
		
		sb.append("HashKey=" + hashKey + "&");
		for(String key : keys){
			sb.append(key + "=" + parameterMap.get(key) + "&");
		}
		sb.append("&HashIV=" + hashIV);
		
		sb = new StringBuilder(AllpayURLEncoder.encode(sb.toString(), "UTF-8").toLowerCase());
		
		
		return null;
	}
}
