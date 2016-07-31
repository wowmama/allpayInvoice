package com.fruitpay.allpayInvoice.model;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.builder.ParameterMapBuilder;
import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;
import com.fruitpay.allpayInvoice.machine.MachineType;

/**
 * 載具
 * @author Churu
 *
 */
public class AllpayCarruer implements PostParameterMap{
	
	@PostParameterName(name="CarruerNum",method={MachineType.CREATE}) 
	private String carruerNum;
	@PostParameterName(name="CarruerType",method={MachineType.CREATE}) 
	private CarruerTypeEnum carruerType;
	
	public AllpayCarruer(){
//		carruerType = CarruerTypeEnum.MEMBER;
	}
	
	public String getCarruerNum() {
		return carruerNum;
	}
	public CarruerTypeEnum getCarruerType() {
		return carruerType;
	}
	/**
	 *  1. 當載具類別[CarruerType]為無載具或會員載具時，則請帶空字串。<br>
	 *	2. 當載具類別[CarruerType]為買受人之自然人憑證時，則請帶固定長度為 16 且格式為 2 碼大小寫字母加上 14 碼數字。<br>
	 *	3. 當載具類別[CarruerType]為買受人之手機條碼時，則請帶固定長度為 8 且格式為 1 碼斜線「/」加上由 7 碼數字及大小寫字母組成。<br>
	 * @param carruerNum 載具編號
	 * @return AllpayCarruer 載具
	 */
	public AllpayCarruer setCarruerNum(String carruerNum) {
		this.carruerNum = carruerNum;
		return this;
	}
	/**
	 * 若為無載具時，則請帶空字串。<br>
	 *	1. 若為會員載具時，則請帶 CarruerTypeEnum.MEMBER<br>
	 *	2. 若為買受人之自然人憑證號碼時，則請帶 CarruerTypeEnum.CITIZEN_DIGITAL_CERTIFICATE<br>
	 *	3. 若為買受人之手機條碼資料時，則請帶CarruerTypeEnum.MOBILE_BARCODE<br>
	 *	4. 若統一編號[CustomerIdentifier]有值時，則載具類別不可為會員載具或自然人憑證載具。<br>
	 * @param carruerType 載具類別
	 * @return AllpayCarruer 載具
	 */
	public AllpayCarruer setCarruerType(CarruerTypeEnum carruerType) {
		this.carruerType = carruerType;
		return this;
	}
	
	/**
	 *  載具類別 
	 */
	public enum CarruerTypeEnum{
		/**
		 * 會員
		 */
		MEMBER(1),
		/**
		 * 自然人憑證
		 */
		CITIZEN_DIGITAL_CERTIFICATE(2),
		/**
		 * 手機條碼
		 */
		MOBILE_BARCODE(3);
		private Integer carruerType;
		CarruerTypeEnum(int carruerType){
			this.carruerType = carruerType;
		}
		public String value(){
			return carruerType.toString();
		}
	}

	public Map<String, String> getParameterMap(MachineType machineType) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException {
		return new ParameterMapBuilder(machineType).build(this);
	}
}
