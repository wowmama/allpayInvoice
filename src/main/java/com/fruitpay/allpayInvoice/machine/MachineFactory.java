package com.fruitpay.allpayInvoice.machine;


public class MachineFactory {
	
	public static boolean IS_DEBUG = true;
	public final static String CREATE_URL = "https://einvoice.allpay.com.tw/Invoice/Issue";
	public final static String CREATE_URL_TEST = "https://einvoice-stage.allpay.com.tw/Invoice/Issue";
	public final static String CANCEL_URL = "https://einvoice.allpay.com.tw/Invoice/IssueInvalid";
	public final static String CANCEL_URL_TEST = "https://einvoice-stage.allpay.com.tw/Invoice/IssueInvalid";
	public final static String QUERY_URL= "https://einvoice.allpay.com.tw/Query/Issue";
	public final static String QUERY_URL_TEST= "https://einvoice-stage.allpay.com.tw/Query/Issue";
	
	
	public static InvoiceMachine getInvoiceMachine(MachineType machineType){
		if(machineType.equals(MachineType.CREATE)){
			return new InvoiceMachine(IS_DEBUG ? CREATE_URL_TEST : CREATE_URL, machineType);
		}else if(machineType.equals(MachineType.CANCEL)){
			return new InvoiceMachine(IS_DEBUG ? CANCEL_URL_TEST : CANCEL_URL, machineType);
		}else if(machineType.equals(MachineType.QUERY)){
			return new InvoiceMachine(IS_DEBUG ? QUERY_URL_TEST : QUERY_URL, machineType);
		}else{
			return null;
		}
	}
}

