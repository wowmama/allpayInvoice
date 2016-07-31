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
		
	    InvoiceMachine invoiceMachine = MachineFactory.getInvoiceMachine(MachineType.CREATE)
	    	.setHashKey(hashKey)
	    	.setHashIV(hashIV)
			.setMerchantId(merchantId);
		
		AllpayInvoice invoice = new AllpayInvoice()
				.setRelateNumber("MYINVOICE123473")
				.setInvType(AllpayInvoice.InvTypeEnum.NORMAL)
				.setSalesAmount(998)
				.setTimeStamp(new Date());
		AllpayCustomer customer = invoice.getCustomer()
				.setCustomerName("徐小名")
				.setCustomerAddr("永靖鄉")
				.setCustomerEmail("wowmama0107@gmail.com")
				.setCustomerId("1224")
				.setCustomerPhone("0933370691");
		
		
		AllpayItem item = invoice.createItem()
				.setItemName("單人活力水果箱")
				.setItemCount(2)
				.setItemPrice(499)
				.setItemRemark("4-5種新鮮水 客製化您<br>的水果箱 適用:小資女、單身、學生")
				.setItemWord("箱");
		
		
		Map<String, String> response = invoiceMachine.post(invoice);
		
//		response.forEach((k, v)->System.out.println("Key : " + k + ", Value : " + URLDecoder.decode(v, "UTF-8")));
    }
	
}
