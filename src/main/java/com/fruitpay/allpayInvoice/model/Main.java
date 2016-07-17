package com.fruitpay.allpayInvoice.model;

import java.lang.reflect.InvocationTargetException;

public class Main {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Invoice invoice = new Invoice();
		System.out.println(invoice.getParameterMap());
	}
}
