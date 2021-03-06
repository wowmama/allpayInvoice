package com.fruitpay.allpayInvoice.builder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;
import com.fruitpay.allpayInvoice.util.MD5Digest;

/**
 * 檢查碼Builder，每次跟Allpay溝通都需檢查碼機制。<br>
 * @author Churu
 *
 */
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
		parameterMap.remove("Reason");
		
		Object[] keys = parameterMap.keySet().toArray();
		
		Arrays.sort(keys);
		
		sb.append("HashKey=" + hashKey + "&");
		for(Object key : keys){
			sb.append(key + "=" + parameterMap.get(key) + "&");
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
