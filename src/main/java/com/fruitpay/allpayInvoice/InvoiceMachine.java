package com.fruitpay.allpayInvoice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.fruitpay.allpayInvoice.model.Carruer;
import com.fruitpay.allpayInvoice.model.Customer;
import com.fruitpay.allpayInvoice.model.Invoice;
import com.fruitpay.allpayInvoice.model.Item;
import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;

public class InvoiceMachine {
//	private static final Logger log = Logger.getLogger(InvoiceMachine.class);
	
	private String postUrl;
	private String merchantId;
	private String hashKey;
	private String hashIV;
	private Invoice invoice;
	
	public Customer createCustomer(){
		setCustomer(new Customer());
		return invoice.getCustomer();
	}
	public Invoice createInvoice(){
		setInvoice(new Invoice());
		return invoice;
	}
	public Carruer createCarruer(){
		setCarruer(new Carruer());
		return invoice.getCarruer();
	}
	public Item createItem(){
		Item item = new Item();
		invoice.getItemList().addItem(item);
		return item; 
	}
	
	public void postInvoice() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, NoSuchAlgorithmException, MalformedURLException, IOException{
		
		HttpURLConnection connection = (HttpURLConnection)new URL(postUrl).openConnection();
		String urlParameters = getUrlParameters();
		System.out.println("urlParameters : " + urlParameters);
		connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Language", "UTF-8");  

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParameters);

		wr.flush();
		wr.close();
		
		
		int responseCode = connection.getResponseCode();
//		log.info("\nSending 'POST' request to URL : " + postUrl);
//		log.info("Post parameters : " + urlParameters);
//		log.info("Response Code : " + responseCode);
		

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
	}
	
	public String getCheckMacValue() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException, NoSuchAlgorithmException{
		Map<String, String> parameterMap = invoice.getParameterMap();
		parameterMap.put("MerchantID", merchantId);
		
		String checkMacValue = new CheckValueBuilder()
			.setHashIV(hashIV)
			.setHashKey(hashKey)
			.setParameterMap(parameterMap)
			.build();
		return checkMacValue;
	}
	
	public String getUrlParameters() throws IllegalAccessException,
			NoSuchMethodException, InvocationTargetException,
			UnsupportedEncodingException, NoSuchAlgorithmException {
		
		String checkMacValue = getCheckMacValue();
		
		Map<String, String> parameterMap = invoice.getParameterMap();
		parameterMap.put("CheckMacValue", checkMacValue);
		parameterMap.put("MerchantID", merchantId);
		
		StringBuilder sb = new StringBuilder();
		StringBuilder testSb = new StringBuilder();
		Object[] keys =  parameterMap.keySet().toArray();
		Arrays.sort(keys);
		
		for(Object key : keys){
			sb.append(key + "=" + AllpayURLEncoder.encode(parameterMap.get(key), "UTF-8") + "&");
		}
		
		return sb.substring(0, sb.length() - 1);
	}
	
	
	public InvoiceMachine setTimeStamp(Date timeStamp){
		invoice.setTimeStamp(timeStamp);
		return this;
	}
	public InvoiceMachine setCustomer(Customer customer){
		this.invoice.setCustomer(customer);
		return this;
	}
	public InvoiceMachine setInvoice(Invoice invoice){
		this.invoice = invoice;
		return this;
	}
	public InvoiceMachine setCarruer(Carruer carruer) {
		this.invoice.setCarruer(carruer);
		return this;
	}
	public InvoiceMachine setPostUrl(String postUrl) {
		this.postUrl = postUrl;
		return this;
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
	
	
}
