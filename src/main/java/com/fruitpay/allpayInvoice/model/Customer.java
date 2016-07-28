package com.fruitpay.allpayInvoice.model;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.builder.ParameterMapBuilder;
import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;
import com.fruitpay.allpayInvoice.machine.MachineType;

public class Customer implements PostParameterMap{
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
	public Customer setCustomerId(String customerID) {
		this.customerId = customerID;
		return this;
	}
	public String getCustomerIdentifier() {
		return customerIdentifier;
	}
	public Customer setCustomerIdentifier(String customerIdentifier) {
		this.customerIdentifier = customerIdentifier;
		return this;
	}
	public String getCustomerName() {
		return customerName;
	}
	public Customer setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}
	public String getCustomerAddr() {
		return customerAddr;
	}
	public Customer setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
		return this;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public Customer setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
		return this;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public Customer setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
		return this;
	}
	public Map<String, String> getParameterMap(MachineType machineType)
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException {
		return new ParameterMapBuilder(machineType).build(this);
	}
}
