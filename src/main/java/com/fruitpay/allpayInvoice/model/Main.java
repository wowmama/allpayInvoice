package com.fruitpay.allpayInvoice.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import com.fruitpay.allpayInvoice.InvoiceMachine;
import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;
import com.fruitpay.allpayInvoice.util.MD5Digest;

public class Main {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchAlgorithmException, IOException{
		System.setProperty("http.proxyHost", "127.0.0.1");
	    System.setProperty("https.proxyHost", "127.0.0.1");
	    System.setProperty("http.proxyPort", "8888");
	    System.setProperty("https.proxyPort", "8888");
		
		
		String merchantId = "2000132";
	    String hashKey = "ejCk326UnaZWKisg";
	    String hashIV = "q9jcZX8Ib9LM8wYk";
	    String postUrl = "http://einvoice-stage.allpay.com.tw/Invoice/Issue";
		
	    InvoiceMachine invoiceMachine = new InvoiceMachine()
	    	.setHashKey(hashKey)
	    	.setHashIV(hashIV)
			.setMerchantId(merchantId)
			.setPostUrl(postUrl);
		
		Invoice invoice = invoiceMachine.createInvoice()
				.setRelateNumber("MYINVOICE123457")
				.setInvType(Invoice.InvTypeEnum.NORMAL)
				.setSalesAmount(100)
				.setTimeStamp(new Date());

		Customer customer = invoiceMachine.createCustomer()
				.setCustomerName("customerName")
				.setCustomerAddr("customerAddr")
				.setCustomerEmail("test@yahoo.com.tw")
				.setCustomerId("")
				.setCustomerPhone("0912345678");
		
		
		Carruer carruer = invoiceMachine.createCarruer();
		
		Item item = invoiceMachine.createItem()
				.setItemName("test_ktv")
				.setItemCount(1)
				.setItemPrice(100)
				.setItemAmount(100)
				.setItemWord("piece")
				.setItemRemark("test_ktv");
		
		invoiceMachine.postInvoice();

    }
	
}
