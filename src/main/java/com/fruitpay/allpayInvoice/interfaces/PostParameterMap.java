package com.fruitpay.allpayInvoice.interfaces;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.fruitpay.allpayInvoice.annotations.PostParameterName;
import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;

public interface PostParameterMap {
	
	public Map<String, String> getParameterMap() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException;
}
