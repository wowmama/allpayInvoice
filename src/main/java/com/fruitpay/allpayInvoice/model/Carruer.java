package com.fruitpay.allpayInvoice.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.fruitpay.allpayInvoice.ParameterMapBuilder;
import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;

public class Carruer implements PostParameterMap{
	
	@PostParameterName(name="CarruerNum") private String carruerNum;
	@PostParameterName(name="CarruerType") private CarruerTypeEnum carruerType;
	
	public String getCarruerNum() {
		return carruerNum;
	}
	public CarruerTypeEnum getCarruerType() {
		return carruerType;
	}
	public Carruer setCarruerNum(String carruerNum) {
		this.carruerNum = carruerNum;
		return this;
	}
	public Carruer setCarruerType(CarruerTypeEnum carruerType) {
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

	public Map<String, String> getParameterMap() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		return new ParameterMapBuilder().build(this);
	}
}
