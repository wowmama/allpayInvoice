package com.fruitpay.allpayInvoice;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;
import com.fruitpay.allpayInvoice.util.MD5Digest;

public class CheckValueBuilder {
	private String hashKey;
	private String hashIV;
	private Map<String, String> parameterMap;
	
	public String build() throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		StringBuilder sb = new StringBuilder();
		parameterMap.remove("InvoiceRemark");
		parameterMap.remove("ItemName");
		parameterMap.remove("ItemWord");
		parameterMap.remove("ItemRemark");

		Object[] keys = parameterMap.keySet().toArray();
		
		
		Arrays.sort(keys);
		
		sb.append("HashKey=" + hashKey + "&");
		for(Object key : keys){
			sb.append(key + "=" + AllpayURLEncoder.encode(parameterMap.get(key), "UTF-8") + "&");
		}
		sb.append("HashIV=" + hashIV);
		String str = AllpayURLEncoder.encode(sb.toString(), "UTF-8").toLowerCase();
		return MD5Digest.hexMD5String(str);
	}

	public CheckValueBuilder setHashKey(String hashKey) {
		this.hashKey = hashKey;
		return this;
	}

	public CheckValueBuilder setHashIV(String hashIV) {
		this.hashIV = hashIV;
		return this;
	}

	public CheckValueBuilder setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
		return this;
	}
	
	
}
