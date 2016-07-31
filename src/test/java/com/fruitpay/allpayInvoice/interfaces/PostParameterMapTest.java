package com.fruitpay.allpayInvoice.interfaces;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.junit.runner.RunWith;

import com.fruitpay.allpayInvoice.builder.ParameterMapBuilder;
import com.fruitpay.allpayInvoice.machine.MachineType;
import com.fruitpay.allpayInvoice.model.AllpayCarruer;
import com.fruitpay.allpayInvoice.model.AllpayCustomer;
import com.fruitpay.allpayInvoice.model.AllpayInvoice;
import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;
import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;

@RunWith(ZohhakRunner.class)
public class PostParameterMapTest {

	@TestWith(
		separator="[\\|]",
		value={
			//  carruerNum|                 carruerType|
			"   0123456789|                      MEMBER",
			"   0123456789|              MOBILE_BARCODE",
			"   0123456789| CITIZEN_DIGITAL_CERTIFICATE",
			"         null| CITIZEN_DIGITAL_CERTIFICATE",
			"   0123456789|                        null",
			"         null|                        null",
		}
	)
	public void testCarruerGetParameterMap(String CarruerNum, AllpayCarruer.CarruerTypeEnum CarruerType) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException {
		AllpayCarruer carruer = new AllpayCarruer()
			.setCarruerNum(CarruerNum)
			.setCarruerType(CarruerType);
		
		
		Map<String, String> expected = createCarruerExpected(CarruerNum, CarruerType);
		
		
		Map<String, String> actual1 = carruer.getParameterMap(MachineType.CREATE);
		Map<String, String> actual2 = new ParameterMapBuilder(MachineType.CREATE).build(carruer);
		
		assertEquals(expected, actual1);
		assertEquals(expected, actual2);
	}
	
	private Map<String, String> createCarruerExpected(String CarruerNum,
			AllpayCarruer.CarruerTypeEnum CarruerType) throws UnsupportedEncodingException {
		Map<String, String> expected = new TreeMap<String, String>();
		if(CarruerNum != null){
			expected.put("CarruerNum", AllpayURLEncoder.encode(CarruerNum,"UTF-8"));
		}
		if(CarruerType != null){
			expected.put("CarruerType", AllpayURLEncoder.encode(CarruerType.value(),"UTF-8"));
		}
		return expected;
	}
	
	
	@TestWith(
		separator="[\\|]",
		value={
			//  CustomerID|  CustomerIdentifier|  CustomerName|  CustomerAddr|  CustomerPhone|  CustomerEmail
			"   CustomerID|  CustomerIdentifier|  CustomerName|  CustomerAddr|  CustomerPhone|  CustomerEmail",
			"         null|  CustomerIdentifier|  CustomerName|  CustomerAddr|  CustomerPhone|  CustomerEmail",
			"   CustomerID|                null|  CustomerName|  CustomerAddr|  CustomerPhone|  CustomerEmail",
			"   CustomerID|  CustomerIdentifier|          null|  CustomerAddr|  CustomerPhone|  CustomerEmail",
			"   CustomerID|  CustomerIdentifier|  CustomerName|          null|  CustomerPhone|  CustomerEmail",
			"   CustomerID|  CustomerIdentifier|  CustomerName|  CustomerAddr|           null|  CustomerEmail",
			"   CustomerID|  CustomerIdentifier|  CustomerName|  CustomerAddr|  CustomerPhone|           null",
			"         null|                null|          null|          null|           null|           null",
			"         null|                null|   `%[]{},\"':|        <>\\/;|           null|           null",
		}
	)
	public void testCustomerGetParameterMap(String CustomerID, String CustomerIdentifier, String CustomerName, String CustomerAddr, String CustomerPhone, String CustomerEmail) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException {
		AllpayCustomer customer = new AllpayCustomer()
			.setCustomerId(CustomerID)
			.setCustomerIdentifier(CustomerIdentifier)
			.setCustomerName(CustomerName)
			.setCustomerAddr(CustomerAddr)
			.setCustomerPhone(CustomerPhone)
			.setCustomerEmail(CustomerEmail);
		
		Map<String, String> expected = createCustomerExpected(CustomerID,
				CustomerIdentifier, CustomerName, CustomerAddr, CustomerPhone,
				CustomerEmail);

		Map<String, String> actual1 = customer.getParameterMap(MachineType.CREATE);
		Map<String, String> actual2 = new ParameterMapBuilder(MachineType.CREATE).build(customer);
		
		assertEquals(expected, actual1);
		assertEquals(expected, actual2);
	}


	private Map<String, String> createCustomerExpected(String CustomerID,
			String CustomerIdentifier, String CustomerName,
			String CustomerAddr, String CustomerPhone, String CustomerEmail) throws UnsupportedEncodingException {
		Map<String, String> expected = new TreeMap<String, String>();
		if(CustomerID != null){
			expected.put("CustomerID", AllpayURLEncoder.encode(CustomerID,"UTF-8"));
		}
		if(CustomerIdentifier != null){
			expected.put("CustomerIdentifier", AllpayURLEncoder.encode(CustomerIdentifier,"UTF-8"));
		}
		if(CustomerName != null){
			expected.put("CustomerName", AllpayURLEncoder.encode(CustomerName,"UTF-8"));
		}
		if(CustomerAddr != null){
			expected.put("CustomerAddr", AllpayURLEncoder.encode(CustomerAddr,"UTF-8"));
		}
		if(CustomerPhone != null){
			expected.put("CustomerPhone", AllpayURLEncoder.encode(CustomerPhone,"UTF-8"));
		}
		if(CustomerEmail != null){
			expected.put("CustomerEmail", AllpayURLEncoder.encode(CustomerEmail,"UTF-8"));
		}
		return expected;
	}

	@TestWith(
		separator="[\\|]",
		value={
			//  RelateNumber|  ClearanceMark|  Print|  Donation|  LoveCode|        TaxType|  SalesAmount|  InvoiceRemark|   InvType|  vat
			"   RelateNumber|            YES|    YES|       YES|  LoveCode|        TAXABLE|         1000|  InvoiceRemark|    NORMAL|  YES",
			"   RelateNumber|             NO|     NO|        NO|  LoveCode|  ZERO_TAX_RATE|         1000|  InvoiceRemark|   SPECIAL|   NO",
			"           null|            YES|    YES|       YES|  LoveCode|        TAXABLE|         1000|  InvoiceRemark|    NORMAL|  YES",
			"   RelateNumber|           null|    YES|       YES|  LoveCode|        TAXABLE|         1000|  InvoiceRemark|    NORMAL|  YES",
			"   RelateNumber|            YES|   null|       YES|  LoveCode|        TAXABLE|         1000|  InvoiceRemark|    NORMAL|  YES",
			"   RelateNumber|            YES|    YES|      null|  LoveCode|        TAXABLE|         1000|  InvoiceRemark|    NORMAL|  YES",
			"   RelateNumber|            YES|    YES|       YES|      null|        TAXABLE|         1000|  InvoiceRemark|    NORMAL|  YES",
			"   RelateNumber|            YES|    YES|       YES|  LoveCode|           null|         1000|  InvoiceRemark|    NORMAL|  YES",
			"   RelateNumber|            YES|    YES|       YES|  LoveCode|        TAXABLE|         null|  InvoiceRemark|    NORMAL|  YES",
			"   RelateNumber|            YES|    YES|       YES|  LoveCode|        TAXABLE|         1000|           null|    NORMAL|  YES",
			"   RelateNumber|            YES|    YES|       YES|  LoveCode|        TAXABLE|         1000|  InvoiceRemark|      null|  YES",
			"   RelateNumber|            YES|    YES|       YES|  LoveCode|        TAXABLE|         1000|  InvoiceRemark|    NORMAL| null",
			"           null|           null|   null|      null|      null|           null|         null|           null|      null| null",
			"   RelateNumber|            YES|    YES|       YES|  LoveCode|        TAXABLE|         1000|    []_!+-()*,:|    NORMAL|  YES",

		}
	)
	public void testInvoiceGetParameterMap(String RelateNumber, AllpayInvoice.CustomsClearanceMarkEnum ClearanceMark, 
			AllpayInvoice.PrintEnum Print, AllpayInvoice.DonationEnum Donation, String LoveCode, AllpayInvoice.TaxTypeEnum TaxType,
			Integer SalesAmount, String InvoiceRemark,  AllpayInvoice.InvTypeEnum InvType, AllpayInvoice.VatEnum vat) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException{
		Date TimeStamp = new Date();
		AllpayInvoice invoice = new AllpayInvoice()
			.setTimeStamp(TimeStamp)
			.setRelateNumber(RelateNumber)
			.setClearanceMark(ClearanceMark)
			.setPrint(Print)
			.setDonation(Donation)
			.setLoveCode(LoveCode)
			.setTaxType(TaxType)
			.setSalesAmount(SalesAmount)
			.setInvoiceRemark(InvoiceRemark)
			.setInvType(InvType)
			.setVat(vat);
		
		Map<String, String> expected = createInvoiceExpected(RelateNumber,
				ClearanceMark, Print, Donation, LoveCode, TaxType, SalesAmount,
				InvoiceRemark, InvType, vat, TimeStamp);
		
		Map<String, String> actual1 = invoice.getParameterMap(MachineType.CREATE);
		Map<String, String> actual2 = new ParameterMapBuilder(MachineType.CREATE).build(invoice);
		
		assertEquals(expected, actual1);
		assertEquals(expected, actual2);
			
	}

	private Map<String, String> createInvoiceExpected(String RelateNumber,
			AllpayInvoice.CustomsClearanceMarkEnum ClearanceMark,
			AllpayInvoice.PrintEnum Print, AllpayInvoice.DonationEnum Donation,
			String LoveCode, AllpayInvoice.TaxTypeEnum TaxType, Integer SalesAmount,
			String InvoiceRemark, AllpayInvoice.InvTypeEnum InvType,
			AllpayInvoice.VatEnum vat, Date TimeStamp) throws UnsupportedEncodingException {
		Map<String, String> expected = new TreeMap<String, String>();
		
//		expected.put("ItemName", "");
//		expected.put("ItemCount", "");
//		expected.put("ItemWord", "");
//		expected.put("ItemPrice", "");
//		expected.put("ItemTaxType", "");
//		expected.put("ItemAmount", "");
//		expected.put("ItemRemark", "");
		
		if(TimeStamp != null){
			expected.put("TimeStamp", AllpayURLEncoder.encode(String.valueOf(TimeStamp.getTime()/1000),"UTF-8"));
		}
		if(RelateNumber != null){
			expected.put("RelateNumber", AllpayURLEncoder.encode(RelateNumber,"UTF-8"));
		}
		if(ClearanceMark != null){
			expected.put("ClearanceMark", AllpayURLEncoder.encode(ClearanceMark.value(),"UTF-8"));
		}
		if(Print != null){
			expected.put("Print", AllpayURLEncoder.encode(Print.value(),"UTF-8"));
		}
		if(Donation != null){
			expected.put("Donation", AllpayURLEncoder.encode(Donation.value(),"UTF-8"));
		}
		if(LoveCode != null){
			expected.put("LoveCode", AllpayURLEncoder.encode(LoveCode,"UTF-8"));
		}
		if(TaxType != null){
			expected.put("TaxType", AllpayURLEncoder.encode(TaxType.value(),"UTF-8"));
		}
		if(SalesAmount != null){
			expected.put("SalesAmount", AllpayURLEncoder.encode(SalesAmount.toString(),"UTF-8"));
		}
		if(InvoiceRemark != null){
			expected.put("InvoiceRemark", AllpayURLEncoder.encode(InvoiceRemark,"UTF-8"));
		}
		if(InvType != null){
			expected.put("InvType", AllpayURLEncoder.encode(InvType.value(),"UTF-8"));
		}
		if(vat != null){
			expected.put("vat", AllpayURLEncoder.encode(vat.value(),"UTF-8"));
		}
		return expected;
	}
	
}
