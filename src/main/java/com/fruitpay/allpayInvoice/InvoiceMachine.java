package com.fruitpay.allpayInvoice;

import com.fruitpay.allpayInvoice.model.Carruer;
import com.fruitpay.allpayInvoice.model.Customer;
import com.fruitpay.allpayInvoice.model.Invoice;
import com.fruitpay.allpayInvoice.model.Item;

public class InvoiceMachine {
	
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
	
	public void postInvoice(){
		
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
	
}
