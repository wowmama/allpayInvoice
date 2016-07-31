package com.fruitpay.allpayInvoice.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.runner.RunWith;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;

@RunWith(ZohhakRunner.class)
public class ParameterParserTest {

	@TestWith(
			separator="[|]",
			value={
				"{key1=value1, key2=value2, key3=value3, key4=vaue4} | key1=value1&key2=value2&key3=value3&key4=vaue4",
				"  {key1=, key2=value2, key3=value3, key4=vaue4} | key1=&key2=value2&key3=value3&key4=vaue4",
				"{InvoiceNumber=JK00002424, RtnCode=1600006, RtnMsg=作廢發票失敗(該發票已被作廢過), CheckMacValue=B011C700B1AE11F4640D959FBDA45EAD} | InvoiceNumber=JK00002424&RtnCode=1600006&RtnMsg=作廢發票失敗(該發票已被作廢過)&CheckMacValue=B011C700B1AE11F4640D959FBDA45EAD",
			}
		)
	public void testParse(String expected,String response) {
		Map<String, String> actual = ParameterParser.parse(response);
		assertEquals(expected, actual.toString());
	}

}
