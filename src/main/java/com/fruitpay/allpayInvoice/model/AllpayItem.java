package com.fruitpay.allpayInvoice.model;

/**
 * 發票項目
 * @author Churu
 *
 */
public class AllpayItem {
	private String itemName;
	private Integer itemCount = 0;
	private String itemWord;
	private Integer itemPrice = 0;
	private AllpayInvoice.TaxTypeEnum itemTaxType = AllpayInvoice.TaxTypeEnum.TAXABLE;
//	private Integer itemAmount = 0;
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
	public AllpayInvoice.TaxTypeEnum getItemTaxType() {
		return itemTaxType;
	}
	public Integer getItemAmount() {
		return itemCount * itemPrice;
	}
	public String getItemRemark() {
		return itemRemark;
	}
	
	
	public AllpayItem setItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	public AllpayItem setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
		return this;
	}
	public AllpayItem setItemWord(String itemWord) {
		this.itemWord = itemWord;
		return this;
	}
	public AllpayItem setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
		return this;
	}
	public AllpayItem setItemTaxType(AllpayInvoice.TaxTypeEnum itemTaxType) {
		this.itemTaxType = itemTaxType;
		return this;
	}
//	public AllpayItem setItemAmount(Integer itemAmount) {
//		this.itemAmount = itemAmount;
//		return this;
//	}
	public AllpayItem setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
		return this;
	}
	
}
