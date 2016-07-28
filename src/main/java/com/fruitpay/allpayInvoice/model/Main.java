package com.fruitpay.allpayInvoice.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import com.fruitpay.allpayInvoice.machine.InvoiceMachine;
import com.fruitpay.allpayInvoice.machine.MachineFactory;
import com.fruitpay.allpayInvoice.machine.MachineType;

public class Main {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchAlgorithmException, IOException{
		
		String merchantId = "2000132";
	    String hashKey = "ejCk326UnaZWKisg";
	    String hashIV = "q9jcZX8Ib9LM8wYk";
		
	    InvoiceMachine invoiceMachine = MachineFactory.getInvoiceMachine(MachineType.QUERY)
	    	.setHashKey(hashKey)
	    	.setHashIV(hashIV)
			.setMerchantId(merchantId);
		
		Invoice invoice = new Invoice()
				.setRelateNumber("MYINVOICE123465")
				.setInvType(Invoice.InvTypeEnum.NORMAL)
				.setSalesAmount(2200)
				.setReason("測試")
				.setInvoiceNumber("JK00002424")
				.setTimeStamp(new Date());
		URLDecoder.decode("");
		Customer customer = invoice.getCustomer()
				.setCustomerName("王小明")
				.setCustomerAddr("台北市")
				.setCustomerEmail("john@yahoo.com.tw")
				.setCustomerId("john19110101")
				.setCustomerPhone("0912345678");
		
		
		Item item = invoice.createItem()
				.setItemName("移動ktv")
				.setItemCount(2)
				.setItemPrice(100)
				.setItemAmount(200)
				.setItemWord("只");
		
		invoice.createItem()
			.setItemName("移動音響")
			.setItemCount(1)
			.setItemPrice(2000)
			.setItemAmount(2000)
			.setItemWord("個")
			.setItemRemark("二手");
		
		Map<String, String> response = invoiceMachine.post(invoice);
		
		response.forEach((k, v)->System.out.println("Key : " + k + ", Value : " + URLDecoder.decode(v, "UTF-8")));
    }
	
}
