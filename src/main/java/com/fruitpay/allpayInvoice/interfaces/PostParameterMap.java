package com.fruitpay.allpayInvoice.interfaces;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.fruitpay.allpayInvoice.machine.MachineType;

public interface PostParameterMap {
	
	public Map<String, String> getParameterMap(MachineType machineType) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException;
}
