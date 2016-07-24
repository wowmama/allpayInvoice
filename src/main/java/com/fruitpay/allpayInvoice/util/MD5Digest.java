package com.fruitpay.allpayInvoice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Digest {
	public static String hexMD5String(String str) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
        byte[] mdbytes = md.digest();
     
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1).toUpperCase());
        }
		return sb.toString();
	}
}
