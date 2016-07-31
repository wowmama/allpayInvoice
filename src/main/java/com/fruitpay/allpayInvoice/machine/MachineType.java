package com.fruitpay.allpayInvoice.machine;

/**
 * Machine類別<br>
 * @author Churu
 *
 */
public enum MachineType {
	/**
	 * 開立發票Machine
	 */
	CREATE,
	/**
	 * 作廢發票Machine
	 */
	CANCEL,
	/**
	 * 查詢發票Machine
	 */
	QUERY
}
