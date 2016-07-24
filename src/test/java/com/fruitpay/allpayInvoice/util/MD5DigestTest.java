package com.fruitpay.allpayInvoice.util;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class MD5DigestTest {

	@Test
	public void testHexMD5String() throws NoSuchAlgorithmException {
		String expected1 = "40D9A6C00A4A78A300ED458237071BDA";
		String expected2 = "66B16070688F865C9F4B2DA18E854948";
		
		String str1 = "hashkey%3dxdfaefasdfasdfa32d%26itemname%3dsdfasdfa%26merchantid%3d12345678%26merchanttradedate%3d2013%2f03%2f12+15%3a30%3a23%26merchanttradeno%3dallpay_1234%26paymenttype%3dallpay%26returnurl%3dhttp%3asdfasdfa%26totalamount%3d500%26tradedesc%3ddafsdfaff%26hashiv%3dsdfxfafaeafwexfe";
		String str2 = "hashkey%3dxdfaefasdfasdfa32d%26itemname%3dsdfasdfa%26merchantid%3d12345678%26merchanttradedate%3d2013%2f03%2f12+15%3a30%3a23%26merchanttradeno%3dallpay1234%26paymenttype%3dallpay%26returnurl%3dhttp%3asdfasdfa%26totalamount%3d500%26tradedesc%3ddafsdfaff%26hashiv%3dsdfxfafaeafwexfe";
		
		String actual1 = MD5Digest.hexMD5String(str1);
		String actual2 = MD5Digest.hexMD5String(str2);
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

}
