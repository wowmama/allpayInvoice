package com.fruitpay.allpayInvoice.model;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.builder.ParameterMapBuilder;
import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;
import com.fruitpay.allpayInvoice.machine.MachineType;

/**
 * 會員
 * @author Churu
 *
 */
public class AllpayCustomer implements PostParameterMap{
	@PostParameterName(name="CustomerID",method={MachineType.CREATE}) 
	private String customerId;
	@PostParameterName(name="CustomerIdentifier",method={MachineType.CREATE}) 
	private String customerIdentifier;
	@PostParameterName(name="CustomerName",method={MachineType.CREATE}) 
	private String customerName;
	@PostParameterName(name="CustomerAddr",method={MachineType.CREATE}) 
	private String customerAddr;
	@PostParameterName(name="CustomerPhone",method={MachineType.CREATE}) 
	private String customerPhone;
	@PostParameterName(name="CustomerEmail",method={MachineType.CREATE}) 
	private String customerEmail;
	
	public String getCustomerId() {
		return customerId;
	}
	public String getCustomerIdentifier() {
		return customerIdentifier;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getCustomerAddr() {
		return customerAddr;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	/**
	 * 1. 當開立發票載具類別[CarruerType]為 CarruerTypeEnum.MEMBER(會員載具)時，則該參數必須有值。<br>
	 * 2. 該參數有值時，僅接受『英文、數字、下底線』等字元。<br>
	 * 
	 * @param customerID 設定客戶代號
	 * @return AllpayCustomer 客戶
	 */
	public AllpayCustomer setCustomerId(String customerID) {
		this.customerId = customerID;
		return this;
	}
	/**
	 * 此為統一編號，固定長度為數字 8 碼<br>
	 * @param customerIdentifier 統一編號
	 * @return AllpayCustomer 客戶
	 */
	public AllpayCustomer setCustomerIdentifier(String customerIdentifier) {
		this.customerIdentifier = customerIdentifier;
		return this;
	}
	/**
	 *  1. 當列印註記為列印(PrintEnum.YES)時，則該參數必須有值。<br>
	 *	2. 該參數有值時，僅接受『中、英文及數字』等字元。<br>
	 *	3. 請將參數值做 UrlEncode 方式編碼。<br>
	 *
	 * @param customerName 客戶名稱
	 * @return AllpayCustomer 客戶
	 */
	public AllpayCustomer setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}
	/**
	 *  1. 當列印註記為列印(PrintEnum.YES)時，則該參數必須有值。<br>
	 *	2. 該參數有值時，僅接受『中、英文及數字』等字元。<br>
	 *	3. 請將參數值做 UrlEncode 方式編碼。<br>
	 * @param customerAddr 客戶地址
	 * @return AllpayCustomer 客戶
	 */
	public AllpayCustomer setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
		return this;
	}
	/**
	 *  1. 當客戶電子信箱[CustomerEmail]為空字串時，則該參數必須有值。<br>
	 *	2. 格式為數字。<br>
	 * @param customerPhone 客戶手機號碼
	 * @return AllpayCustomer 客戶
	 */
	public AllpayCustomer setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
		return this;
	}
	/**
	 *  1. 當客戶手機號碼[CustomerPhone]為空字串時，則該參數必須有值。<br>
	 *	2. 格式需符合 EMAIL 格式。<br>
	 *	3. 請將參數值做 UrlEncode 方式編碼。
	 * @param customerEmail 客戶電子信箱
	 * @return AllpayCustomer 客戶
	 */
	public AllpayCustomer setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
		return this;
	}
	public Map<String, String> getParameterMap(MachineType machineType)
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException {
		return new ParameterMapBuilder(machineType).build(this);
	}
}
