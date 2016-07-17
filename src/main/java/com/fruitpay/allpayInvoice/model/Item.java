package com.fruitpay.allpayInvoice.model;

public class Item {
	private String itemName;
	private Integer itemCount = 0;
	private String itemWord;
	private Integer itemPrice = 0;
	private Invoice.TaxTypeEnum itemTaxType = Invoice.TaxTypeEnum.TAXABLE;
	private Integer itemAmount = 0;
	private String itemRemark;
	
	
	public String getItemName() {
		return itemName;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public String getItemWord() {
		return itemWord;
	}
	public Integer getItemPrice() {
		return itemPrice;
	}
	public Invoice.TaxTypeEnum getItemTaxType() {
		return itemTaxType;
	}
	public Integer getItemAmount() {
		return itemAmount;
	}
	public String getItemRemark() {
		return itemRemark;
	}
	
	
	public Item setItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	public Item setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
		return this;
	}
	public Item setItemWord(String itemWord) {
		this.itemWord = itemWord;
		return this;
	}
	public Item setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
		return this;
	}
	public Item setItemTaxType(Invoice.TaxTypeEnum itemTaxType) {
		this.itemTaxType = itemTaxType;
		return this;
	}
	public Item setItemAmount(Integer itemAmount) {
		this.itemAmount = itemAmount;
		return this;
	}
	public Item setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
		return this;
	}
	
}
