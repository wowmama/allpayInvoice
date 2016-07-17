package com.fruitpay.allpayInvoice.interfaces;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.junit.runner.RunWith;

import com.fruitpay.allpayInvoice.model.Carruer;
import com.fruitpay.allpayInvoice.model.Customer;
import com.fruitpay.allpayInvoice.model.Invoice;
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
	public void testCarruerGetParameterMap(String CarruerNum, Carruer.CarruerTypeEnum CarruerType) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		Carruer carruer = new Carruer()
			.setCarruerNum(CarruerNum)
			.setCarruerType(CarruerType);
		
		
		Map<String, String> expected = createCarruerExpected(CarruerNum, CarruerType);
		
		
		Map<String, String> actual = carruer.getParameterMap();
		
		assertEquals(expected, actual);
	}
	
	private Map<String, String> createCarruerExpected(String CarruerNum,
			Carruer.CarruerTypeEnum CarruerType) {
		Map<String, String> expected = new TreeMap<String, String>();
		if(CarruerNum != null){
			expected.put("CarruerNum", CarruerNum);
		}
		if(CarruerType != null){
			expected.put("CarruerType", CarruerType.value());
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
		}
	)
	public void testCustomerGetParameterMap(String CustomerID, String CustomerIdentifier, String CustomerName, String CustomerAddr, String CustomerPhone, String CustomerEmail) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		Customer customer = new Customer()
			.setCustomerId(CustomerID)
			.setCustomerIdentifier(CustomerIdentifier)
			.setCustomerName(CustomerName)
			.setCustomerAddr(CustomerAddr)
			.setCustomerPhone(CustomerPhone)
			.setCustomerEmail(CustomerEmail);
		
		Map<String, String> expected = createCustomerExpected(CustomerID,
				CustomerIdentifier, CustomerName, CustomerAddr, CustomerPhone,
				CustomerEmail);
		
		Map<String, String> actual = customer.getParameterMap();
		
		assertEquals(expected, actual);
	}

	private Map<String, String> createCustomerExpected(String CustomerID,
			String CustomerIdentifier, String CustomerName,
			String CustomerAddr, String CustomerPhone, String CustomerEmail) {
		Map<String, String> expected = new TreeMap<String, String>();
		if(CustomerID != null){
			expected.put("CustomerID", CustomerID);
		}
		if(CustomerIdentifier != null){
			expected.put("CustomerIdentifier", CustomerIdentifier);
		}
		if(CustomerName != null){
			expected.put("CustomerName", CustomerName);
		}
		if(CustomerAddr != null){
			expected.put("CustomerAddr", CustomerAddr);
		}
		if(CustomerPhone != null){
			expected.put("CustomerPhone", CustomerPhone);
		}
		if(CustomerEmail != null){
			expected.put("CustomerEmail", CustomerEmail);
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

		}
	)
	public void testInvoiceGetParameterMap(String RelateNumber, Invoice.CustomsClearanceMarkEnum ClearanceMark, 
			Invoice.PrintEnum Print, Invoice.DonationEnum Donation, String LoveCode, Invoice.TaxTypeEnum TaxType,
			Integer SalesAmount, String InvoiceRemark,  Invoice.InvTypeEnum InvType, Invoice.VatEnum vat) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException{
		Date TimeStamp = new Date();
		Invoice invoice = new Invoice()
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

		
		Map<String, String> actual = invoice.getParameterMap();
		
		assertEquals(expected, actual);
			
	}

	private Map<String, String> createInvoiceExpected(String RelateNumber,
			Invoice.CustomsClearanceMarkEnum ClearanceMark,
			Invoice.PrintEnum Print, Invoice.DonationEnum Donation,
			String LoveCode, Invoice.TaxTypeEnum TaxType, Integer SalesAmount,
			String InvoiceRemark, Invoice.InvTypeEnum InvType,
			Invoice.VatEnum vat, Date TimeStamp) {
		Map<String, String> expected = new TreeMap<String, String>();
		if(TimeStamp != null){
			expected.put("TimeStamp", String.valueOf(TimeStamp.getTime()));
		}
		if(RelateNumber != null){
			expected.put("RelateNumber", RelateNumber);
		}
		if(ClearanceMark != null){
			expected.put("ClearanceMark", ClearanceMark.value());
		}
		if(Print != null){
			expected.put("Print", Print.value());
		}
		if(Donation != null){
			expected.put("Donation", Donation.value());
		}
		if(LoveCode != null){
			expected.put("LoveCode", LoveCode);
		}
		if(TaxType != null){
			expected.put("TaxType", TaxType.value());
		}
		if(SalesAmount != null){
			expected.put("SalesAmount", SalesAmount.toString());
		}
		if(InvoiceRemark != null){
			expected.put("InvoiceRemark", InvoiceRemark);
		}
		if(InvType != null){
			expected.put("InvType", InvType.value());
		}
		if(vat != null){
			expected.put("vat", vat.value());
		}
		return expected;
	}
	
}
