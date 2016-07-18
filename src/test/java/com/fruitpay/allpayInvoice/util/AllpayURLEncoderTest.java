package com.fruitpay.allpayInvoice.util;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;

@RunWith(ZohhakRunner.class)
public class AllpayURLEncoderTest {

	@TestWith(
		separator="[A]",
		value={
			//  expected  A  input
			"          -  A  '-'",
			"          _  A  '_'",
			"          .  A  '.'",
			"          !  A  '!'",
			"        %7e  A  '~'",
			"          *  A  '*'",
			"          (  A  '('",
			"          )  A  ')'",
			"          +  A  ' '",
			"        %40  A  '@'",
			"        %23  A  '#'",
			"        %24  A  '$'",
			"        %25  A  '%'",
			"        %5e  A  '^'",
			"        %26  A  '&'",
			"        %3d  A  '='",
			"        %2b  A  '+'",
			"        %3b  A  ';'",
			"        %3f  A  '?'",
			"        %2f  A  '/'",
			"        %5c  A  '\\'",
			"        %3e  A  '>'",
			"        %3c  A  '<'",
			"        %25  A  '%'",
			"        %60  A  '`'",
			"        %5b  A  '['",
			"        %5d  A  ']'",
			"        %7b  A  '{'",
			"        %7d  A  '}'",
			"        %3a  A  ':'",
			"        %27  A  '''",
			"        %22  A  '\"'",
			"        %2c  A  ','",
			"        %7c  A  '|'",
		}
	)
	public void testEncode(String expected, String input) throws UnsupportedEncodingException {
		String actual = AllpayURLEncoder.encode(input,"UTF-8");
		assertEquals(expected, actual);
	}

}
