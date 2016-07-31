package com.fruitpay.allpayInvoice.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ParameterParser {
	public static Map<String, String> parse(String response) {
		Map<String, String> parameterMap = new LinkedHashMap<String, String>();
		String[] parameters = response.split("&");
		for (String para : parameters) {
			int index = para.indexOf("=");
			String key = index > 0 ? para.substring(0, index) : para;
			String value = index > 0 && para.length() > index + 1 ? para.substring(index + 1) : "";
			try {
				parameterMap.put(key, URLDecoder.decode(value,"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return parameterMap;
//		return Arrays.asList(parameters)
//				.stream()
//				.map(ParameterParser::splitQueryParameter)
//				.collect(Collectors.to)
//				.collect(Collectors.toMap(SimpleImmutableEntry::getKey,SimpleImmutableEntry::getValue));
	}
	
//	public static SimpleImmutableEntry<String, String> splitQueryParameter(String it) {
//	    
//		int idx = it.indexOf("=");
//		String key = null;
//		String value = null;
//		try {
//				key = idx > 0 ? it.substring(0, idx) : it;
//				value = idx > 0 && it.length() > idx + 1 ? URLDecoder.decode(it.substring(idx + 1),"UTF-8") : "";
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			return new SimpleImmutableEntry<>(key, value);
//		}
//		return new SimpleImmutableEntry<>(key, value);
//	}
}
