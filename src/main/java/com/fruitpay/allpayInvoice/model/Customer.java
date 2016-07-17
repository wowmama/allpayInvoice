package com.fruitpay.allpayInvoice.model;

import java.util.Map;

import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;

public class Customer extends PostParameterMap{
	@PostParameterName(name="CustomerID") private String customerId;
	@PostParameterName(name="CustomerIdentifier") private String customerIdentifier;
	@PostParameterName(name="CustomerName") private String customerName;
	@PostParameterName(name="CustomerAddr") private String customerAddr;
	@PostParameterName(name="CustomerPhone") private String customerPhone;
	@PostParameterName(name="CustomerEmail") private String customerEmail;
	
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
}
