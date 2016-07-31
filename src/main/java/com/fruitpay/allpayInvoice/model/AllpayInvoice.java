package com.fruitpay.allpayInvoice.model;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.builder.ParameterMapBuilder;
import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;
import com.fruitpay.allpayInvoice.machine.MachineType;

/**
 * 發票
 * @author Churu
 *
 */
public class AllpayInvoice implements PostParameterMap{
	@PostParameterName(name="TimeStamp",method={MachineType.CREATE,MachineType.CANCEL,MachineType.QUERY}) 
	private Date timeStamp;
	
	@PostParameterName(name="RelateNumber",method={MachineType.CREATE,MachineType.QUERY}) 
	private String relateNumber;
	
	@PostParameterName(method={MachineType.CREATE}) 
	private AllpayCustomer customer;
	
	@PostParameterName(name="ClearanceMark",method={MachineType.CREATE}) 
	private CustomsClearanceMarkEnum clearanceMark;
	
	@PostParameterName(name="Print",method={MachineType.CREATE}) 
	private PrintEnum print;
	
	@PostParameterName(name="Donation",method={MachineType.CREATE}) 
	private DonationEnum donation;
	
	@PostParameterName(name="LoveCode",method={MachineType.CREATE}) 
	private String loveCode;
	
	@PostParameterName(method={MachineType.CREATE}) 
	private AllpayCarruer carruer;
	
	@PostParameterName(name="TaxType",method={MachineType.CREATE}) 
	private TaxTypeEnum taxType;
	
	@PostParameterName(name="SalesAmount",method={MachineType.CREATE}) 
	private Integer salesAmount;
	
	@PostParameterName(name="InvoiceRemark",method={MachineType.CREATE}) 
	private String invoiceRemark;
	
	@PostParameterName(method={MachineType.CREATE}) 
	private AllpayItems itemList;
	
	@PostParameterName(name="InvType",method={MachineType.CREATE}) 
	private InvTypeEnum invType;
	
	@PostParameterName(name="vat",method={MachineType.CREATE}) 
	private VatEnum vat;
	
	@PostParameterName(name="Reason",method={MachineType.CANCEL}) 
	private String reason;
	
	@PostParameterName(name="InvoiceNumber",method={MachineType.CANCEL}) 
	private String invoiceNumber;
	
	
	
	/**
	 * 預設發票為不列印、不捐贈、免稅、會員載具
	 */
	public AllpayInvoice(){
		customer = new AllpayCustomer();
		carruer = new AllpayCarruer();
		itemList = new AllpayItems();
		//預設為不列印、不捐贈、應稅、一般稅額
		print = PrintEnum.NO;
		donation = DonationEnum.NO;
		taxType = TaxTypeEnum.TAXABLE;
		invType = InvTypeEnum.NORMAL;
		itemList = new AllpayItems();
	}
	
	public AllpayItem createItem(){
		AllpayItem item = new AllpayItem();
		itemList.addItem(item);
		return item;
	}
	
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public String getRelateNumber() {
		return relateNumber;
	}
	public AllpayCustomer getCustomer() {
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
	public AllpayCarruer getCarruer() {
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
	public AllpayItems getItemList() {
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
	
	/**
	 * 長度固定為10碼(ex:XN12345678)<br>
	 * @param invoiceNumber 發票號碼
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
		return this;
	}
	/**
	 * 歐付寶會利用此參數將當下的時間轉為 UnixTimeStamp 來驗證此次介接的時間區間。<br>
	 * 注意事項：驗證時間區間暫訂為 5 分鐘內有效，若超過此驗證時間則此次訂單將無法建立參考資料：<br>
	 * @param timeStamp 時間戳記
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}
	/**
	 * 	此為會員自訂編號<br>
	 *	1. 均為唯一值不可重覆使用。<br>
	 * @param relateNumber 會員自訂編號
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setRelateNumber(String relateNumber) {
		this.relateNumber = relateNumber;
		return this;
	}
	/**
	 * 設定客戶資訊
	 * @param customer 客戶
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setCustomer(AllpayCustomer customer) {
		this.customer = customer;
		return this;
	}
	/**
	 * 當課稅類別為零稅率(TaxTypeEnum.ZERO_TAX_RATE)時，則該參數請帶經海關出口(CustomsClearanceMarkEnum.YES)或非經海關出口(CustomsClearanceMarkEnum.NO)。<br>
	 * @param clearanceMark 通關方式
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setClearanceMark(CustomsClearanceMarkEnum clearanceMark) {
		this.clearanceMark = clearanceMark;
		return this;
	}
	/**
	 *  １. 若為不列印或捐贈註記捐贈時，參數請帶：PrintEnum.NO。<br>
	 *	２. 若為列印或統一編號有值時，參數請帶：PrintEnum.YES。<br>
	 * @param print 列印註記
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setPrint(PrintEnum print) {
		this.print = print;
		return this;
	}
	/**
	 *  1. 若為捐贈時，參數請帶：DonationEnum.YES。
	 *	2. 若為不捐贈或統一編號有值時，參數請帶：DonationEnum.NO。
	 * @param donation 捐贈註記
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setDonation(DonationEnum donation) {
		this.donation = donation;
		return this;
	}
	/**
	 * 消費者選擇捐贈發票則於此欄位須填入受贈單位之愛心碼。<br>
	 *	1. 若捐贈註記捐贈時，此欄位須有值。<br>
	 *	2. 長度限制為 3 至 7 碼。<br>
	 *	3. 格式為大小寫「X」加上 2 至 6 碼數字或全數字。<br>
	 * @param loveCode 愛心碼
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setLoveCode(String loveCode) {
		this.loveCode = loveCode;
		return this;
	}
	/**
	 * 設定載具資訊
	 * @param carruer 載具
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setCarruer(AllpayCarruer carruer) {
		this.carruer = carruer;
		return this;
	}
	/**
	 *  1. 若為應稅，請帶 TaxTypeEnum.TAXABLE。<br>
	 *	2. 若為零稅率，請帶 TaxTypeEnum.ZERO_TAX_RATE。<br>
	 *	3. 若為免稅，請帶 TaxTypeEnum.DUTY_FREE。<br>
	 *	4. 若為混合應稅與免稅時(限收銀機發票無法分辨時使用，且需通過申請核可)，則請帶TaxTypeEnum.MIXED_TAXABLE。<br>
	 * @param taxType 課稅類別
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setTaxType(TaxTypeEnum taxType) {
		this.taxType = taxType;
		return this;
	}
	/**
	 *  總金額(含稅)<br>
	 *	1. 請帶整數，不可有小數點。<br>
	 *	2. 僅限新台幣。<br>
	 *	3. 金額不可為 0 元。<br>
	 * @param salesAmount 發票金額
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setSalesAmount(Integer salesAmount) {
		this.salesAmount = salesAmount;
		return this;
	}
	/**
	 * 當該參數有值時，請將參數值做UrlEncode 方式編碼。<br>
	 * @param invoiceRemark 備註
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setInvoiceRemark(String invoiceRemark) {
		this.invoiceRemark = invoiceRemark;
		return this;
	}
	public AllpayInvoice setItemList(AllpayItems itemList) {
		this.itemList = itemList;
		return this;
	}
	/**
	 *  預設為含稅價<br>
	 *	VatEnum.YES:含稅<br>
	 *	VatEnum.NO:未稅<br>
	 * @param vat 商品單價是否含稅
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setVat(VatEnum vat) {
		this.vat = vat;
		return this;
	}
	/**
	 *  該張發票的發票字軌類型。<br>
	 *	InvTypeEnum.NORMAL:一般稅額<br>
	 *	InvTypeEnum.SPECIAL:特種稅額計<br>
	 * @param invType 字軌類別
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setInvType(InvTypeEnum invType) {
		this.invType = invType;
		return this;
	}
	/**
	 * 作廢原因
	 * @param reason 作廢原因
	 * @return AllpayInvoice 發票
	 */
	public AllpayInvoice setReason(String reason) {
		this.reason = reason;
		return this;
	}
	
	/**
	 * 字軌類別
	 */
	public enum InvTypeEnum{
		/**
		 * 一般稅額
		 */
		NORMAL("07"),
		/**
		 * 特種稅額計
		 */
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
