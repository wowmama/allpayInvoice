package com.fruitpay.allpayInvoice.model;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.builder.ParameterMapBuilder;
import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;
import com.fruitpay.allpayInvoice.machine.MachineType;

public class Invoice implements PostParameterMap{
	@PostParameterName(name="TimeStamp",method={MachineType.CREATE,MachineType.CANCEL,MachineType.QUERY}) 
	private Date timeStamp;
	
	@PostParameterName(name="RelateNumber",method={MachineType.CREATE,MachineType.QUERY}) 
	private String relateNumber;
	
	@PostParameterName(method={MachineType.CREATE}) 
	private Customer customer;
	
	@PostParameterName(name="ClearanceMark",method={MachineType.CREATE}) 
	private CustomsClearanceMarkEnum clearanceMark;
	
	@PostParameterName(name="Print",method={MachineType.CREATE}) 
	private PrintEnum print;
	
	@PostParameterName(name="Donation",method={MachineType.CREATE}) 
	private DonationEnum donation;
	
	@PostParameterName(name="LoveCode",method={MachineType.CREATE}) 
	private String loveCode;
	
	@PostParameterName(method={MachineType.CREATE}) 
	private Carruer carruer;
	
	@PostParameterName(name="TaxType",method={MachineType.CREATE}) 
	private TaxTypeEnum taxType;
	
	@PostParameterName(name="SalesAmount",method={MachineType.CREATE}) 
	private Integer salesAmount;
	
	@PostParameterName(name="InvoiceRemark",method={MachineType.CREATE}) 
	private String invoiceRemark;
	
	@PostParameterName(method={MachineType.CREATE}) 
	private Items itemList;
	
	@PostParameterName(name="InvType",method={MachineType.CREATE}) 
	private InvTypeEnum invType;
	
	@PostParameterName(name="vat",method={MachineType.CREATE}) 
	private VatEnum vat;
	
	@PostParameterName(name="Reason",method={MachineType.CANCEL}) 
	private String reason;
	
	@PostParameterName(name="InvoiceNumber",method={MachineType.CANCEL}) 
	private String invoiceNumber;
	
	
	

	public Invoice(){
		customer = new Customer();
		carruer = new Carruer();
		itemList = new Items();
		//預設為不列印、不捐贈、應稅、一般稅額
		print = PrintEnum.NO;
		donation = DonationEnum.NO;
		taxType = TaxTypeEnum.TAXABLE;
		invType = InvTypeEnum.NORMAL;
		itemList = new Items();
	}
	
	public Item createItem(){
		Item item = new Item();
		itemList.addItem(item);
		return item;
	}
	
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public String getRelateNumber() {
		return relateNumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public CustomsClearanceMarkEnum getClearanceMark() {
		return clearanceMark;
	}
	public PrintEnum getPrint() {
		return print;
	}
	public DonationEnum getDonation() {
		return donation;
	}
	public String getLoveCode() {
		return loveCode;
	}
	public Carruer getCarruer() {
		return carruer;
	}
	public TaxTypeEnum getTaxType() {
		return taxType;
	}
	public Integer getSalesAmount() {
		return salesAmount;
	}
	public String getInvoiceRemark() {
		return invoiceRemark;
	}
	public Items getItemList() {
		return itemList;
	}
	public VatEnum getVat() {
		return vat;
	}
	public InvTypeEnum getInvType() {
		return invType;
	}
	public String getReason() {
		return reason;
	}

	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public Invoice setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
		return this;
	}

	public Invoice setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}
	public Invoice setRelateNumber(String relateNumber) {
		this.relateNumber = relateNumber;
		return this;
	}
	public Invoice setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}
	public Invoice setClearanceMark(CustomsClearanceMarkEnum clearanceMark) {
		this.clearanceMark = clearanceMark;
		return this;
	}
	public Invoice setPrint(PrintEnum print) {
		this.print = print;
		return this;
	}
	public Invoice setDonation(DonationEnum donation) {
		this.donation = donation;
		return this;
	}
	public Invoice setLoveCode(String loveCode) {
		this.loveCode = loveCode;
		return this;
	}
	public Invoice setCarruer(Carruer carruer) {
		this.carruer = carruer;
		return this;
	}
	public Invoice setTaxType(TaxTypeEnum taxType) {
		this.taxType = taxType;
		return this;
	}
	public Invoice setSalesAmount(Integer salesAmount) {
		this.salesAmount = salesAmount;
		return this;
	}
	public Invoice setInvoiceRemark(String invoiceRemark) {
		this.invoiceRemark = invoiceRemark;
		return this;
	}
	public Invoice setItemList(Items itemList) {
		this.itemList = itemList;
		return this;
	}
	public Invoice setVat(VatEnum vat) {
		this.vat = vat;
		return this;
	}
	public Invoice setInvType(InvTypeEnum invType) {
		this.invType = invType;
		return this;
	}
	public Invoice setReason(String reason) {
		this.reason = reason;
		return this;
	}
	
	/**
	 * 字軌類別
	 */
	public enum InvTypeEnum{
		NORMAL("07"),
		SPECIAL("08");
		
		private String invType;
		InvTypeEnum(String invType){
			this.invType = invType;
		}
		public String value() {
			return invType;
		}
		
	}
	
	/**
	 * 商品單價是否含稅
	 */
	public enum VatEnum{
		NO(0),
		YES(1);
		private Integer vat;
		VatEnum(int vat){
			this.vat = vat;
		}
		public String value() {
			return vat.toString();
		}
	}
	
	/**
	 * 課稅類別
	 */
	public enum TaxTypeEnum{
		/**
		 * 應稅
		 */
		TAXABLE(1),
		/**
		 * 零稅率
		 */
		ZERO_TAX_RATE(2),
		/**
		 * 免稅
		 */
		DUTY_FREE(3),
		/**
		 * 混合應稅
		 */
		MIXED_TAXABLE(9);
		
		private Integer taxType;
		TaxTypeEnum(int taxType){
			this.taxType = taxType;
		}
		public String value() {
			return taxType.toString();
		}
		
	}
	
	/**
	 *捐贈註記 
	 */
	public enum DonationEnum{
		/**
		 * 捐贈
		 */
		YES(1),
		/**
		 * 不捐贈
		 */
		NO(2);
		
		private Integer donationMark;
		DonationEnum(int donationMark){
			this.donationMark = donationMark;
		}
		public String value() {
			return donationMark.toString();
		}
		
	}
	
	/**
	 *	列印註記 
	 */
	public enum PrintEnum{
		/**
		 * 需列印
		 */
		YES(1),
		/**
		 * 不須列印
		 */
		NO(0);
		private Integer printMark;
		PrintEnum(int printMark){
			this.printMark = printMark;
		}
		public String value(){
			return printMark.toString();
		}
	}
	/**
	 * 通關方式 
	 */
	public enum CustomsClearanceMarkEnum{
		
		/**
		 * 經海關出口
		 */
		YES(1),
		
		/**
		 * 非經海關出口
		 */
		NO(2);
		
		private Integer clearanceMark;
		CustomsClearanceMarkEnum(int clearanceMark){
			this.clearanceMark = clearanceMark;
		}
		
		public String value(){
			return clearanceMark.toString();
		}
	}
	public Map<String, String> getParameterMap(MachineType machineType)
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException{
		return new ParameterMapBuilder(machineType).build(this);
	}
}
