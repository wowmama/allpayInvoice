package com.fruitpay.allpayInvoice.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchAlgorithmException, IOException{
		MessageDigest md = MessageDigest.getInstance("MD5");
	    
		String str = "hashkey%3dxdfaefasdfasdfa32d%26itemname%3dsdfasdfa%26merchantid%3d12345678%26merchanttradedate%3d2013%2f03%2f12+15%3a30%3a23%26merchanttradeno%3dallpay_1234%26paymenttype%3dallpay%26returnurl%3dhttp%3asdfasdfa%26totalamount%3d500%26tradedesc%3ddafsdfaff%26hashiv%3dsdfxfafaeafwexfe";
		md.update(str.getBytes());
        byte[] mdbytes = md.digest();
     
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1).toUpperCase());
        }

        System.out.println("Digest(in hex format):: " + sb.toString());
        
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<mdbytes.length;i++) {
    		String hex=Integer.toHexString(0xff & mdbytes[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex.toUpperCase());
    	}
    	System.out.println("Digest(in hex format):: " + hexString.toString());
    }
	
}
