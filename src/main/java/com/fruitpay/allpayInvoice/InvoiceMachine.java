package com.fruitpay.allpayInvoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;
import com.fruitpay.allpayInvoice.model.Carruer;
import com.fruitpay.allpayInvoice.model.Customer;
import com.fruitpay.allpayInvoice.model.Invoice;
import com.fruitpay.allpayInvoice.model.Item;
import com.fruitpay.allpayInvoice.model.Items;
import com.fruitpay.allpayInvoice.model.Invoice.DonationEnum;
import com.fruitpay.allpayInvoice.model.Invoice.InvTypeEnum;
import com.fruitpay.allpayInvoice.model.Invoice.PrintEnum;
import com.fruitpay.allpayInvoice.model.Invoice.TaxTypeEnum;
import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;

public class InvoiceMachine implements PostParameterMap{
	private static final Logger log = Logger.getLogger(InvoiceMachine.class);
	
	private String postUrl;
	private String merchantId;
	private String hashKey;
	private String hashIV;
	private Invoice invoice;
	
	public Invoice createInvoice(){

		//預設為不列印、不捐贈、應稅、一般稅額
		Invoice invoice = new Invoice();
		invoice.setPrint(PrintEnum.NO);
		invoice.setDonation(DonationEnum.NO);
		invoice.setTaxType(TaxTypeEnum.TAXABLE);
		invoice.setInvType(InvTypeEnum.NORMAL);
		invoice.setItemList(new Items());
		
		setInvoice(invoice);
		return invoice;
	}
	
	public void postInvoice() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, NoSuchAlgorithmException, MalformedURLException, IOException{
		
		HttpURLConnection connection = (HttpURLConnection)new URL(postUrl).openConnection();
		String urlParameters = getUrlParameters();
		
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
		in.close();
		
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
		
		Map<String, String> parameterMap = getParameterMap();
		parameterMap.put("CheckMacValue", checkMacValue);
		
		StringBuilder sb = new StringBuilder();
		Object[] keys =  parameterMap.keySet().toArray();
		
		Arrays.sort(keys);
		
		for(Object key : keys){
			sb.append(key + "=" + AllpayURLEncoder.encode(parameterMap.get(key), "UTF-8") + "&");
		}
		
		return sb.substring(0, sb.length() - 1);
	}
	
	
	public InvoiceMachine setInvoice(Invoice invoice){
		this.invoice = invoice;
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
	@Override
	public Map<String, String> getParameterMap()
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, InvocationTargetException {
		Map<String, String> parameterMap = invoice.getParameterMap();
		parameterMap.put("MerchantID", merchantId);
		return parameterMap;
	}
	
	
}
