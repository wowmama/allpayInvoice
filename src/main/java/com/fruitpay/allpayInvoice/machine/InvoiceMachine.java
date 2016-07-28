package com.fruitpay.allpayInvoice.machine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.fruitpay.allpayInvoice.builder.CheckValueBuilder;
import com.fruitpay.allpayInvoice.model.Invoice;
import com.fruitpay.allpayInvoice.util.ParameterParser;

public class InvoiceMachine {
	private static final Logger log = Logger.getLogger(InvoiceMachine.class);
	private String postUrl;
	private MachineType machineType;
	private String merchantId;
	private String hashKey;
	private String hashIV;
	
	
	
	public InvoiceMachine(String postUrl, MachineType machineType) {
		super();
		this.postUrl = postUrl;
		this.machineType = machineType;
	}

	public Map<String,String> post(Invoice invoice) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, NoSuchAlgorithmException, MalformedURLException, IOException{
		HttpsURLConnection connection = (HttpsURLConnection)new URL(postUrl).openConnection();
		String urlParameters = getUrlParameters(invoice);
		System.out.println("urlParameters" + urlParameters);
		connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream()) ;
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
        writer.write(urlParameters);

        writer.flush();
        writer.close();
		
		
		int responseCode = connection.getResponseCode();
		log.info("\nSending 'POST' request to URL : " + postUrl);
		log.info("Post parameters : " + urlParameters);
		log.info("Response Code : " + responseCode);
		

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		System.out.println(response);
		
		in.close();
		
		return ParameterParser.parse(response.toString());
		
	}
	
	public String getCheckMacValue(Invoice invoice) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException, NoSuchAlgorithmException{
		Map<String, String> parameterMap = invoice.getParameterMap(machineType);
		parameterMap.put("MerchantID", merchantId);
		
		String checkMacValue = new CheckValueBuilder()
			.setHashIV(hashIV)
			.setHashKey(hashKey)
			.setParameterMap(parameterMap)
			.build();
		return checkMacValue;
	}
	
	public String getUrlParameters(Invoice invoice) throws IllegalAccessException,
			NoSuchMethodException, InvocationTargetException,
			UnsupportedEncodingException, NoSuchAlgorithmException {
		
		String checkMacValue = getCheckMacValue(invoice);
		
		Map<String, String> parameterMap = getParameterMap(invoice);
		parameterMap.put("CheckMacValue", checkMacValue);
		
		StringBuilder sb = new StringBuilder();
		Object[] keys =  parameterMap.keySet().toArray();
		
		Arrays.sort(keys);
		
		for(Object key : keys){
			sb.append(key + "=" + parameterMap.get(key) + "&");
//			sb.append(key + "=" + AllpayURLEncoder.encode(parameterMap.get(key), "UTF-8") + "&");
		}
		
		return sb.substring(0, sb.length() - 1);
	}
	
	
	public InvoiceMachine setMerchantId(String merchantId) {
		this.merchantId = merchantId;
		return this;
	}
	public InvoiceMachine setHashKey(String hashKey) {
		this.hashKey = hashKey;
		return this;
	}
	public InvoiceMachine setHashIV(String hashIV) {
		this.hashIV = hashIV;
		return this;
	}
	
	public Map<String, String> getParameterMap(Invoice invoice)
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException {
		Map<String, String> parameterMap = invoice.getParameterMap(machineType);
		parameterMap.put("MerchantID", merchantId);
		return parameterMap;
	}

}