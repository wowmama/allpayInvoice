package com.fruitpay.allpayInvoice.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParameterParser {
	public static Map<String, String> parse(String response) {
		Map<String, String> parameterMap = new LinkedHashMap<String, String>();
		String[] parameters = response.split("&");
		for (String para : parameters) {
			int index = para.indexOf("=");
			String key = index > 0 ? para.substring(0, index) : para;
			String value = index > 0 && para.length() > index + 1 ? para.substring(index + 1) : null;
			parameterMap.put(key, value);
		}
		return parameterMap;
	}
}
