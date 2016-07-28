package com.fruitpay.allpayInvoice.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.junit.runner.RunWith;

import com.fruitpay.allpayInvoice.machine.MachineType;
import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;
import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;

@RunWith(ZohhakRunner.class)
public class ItemsTest {

	@TestWith(
		separator="[\\|]",
		value={
			//  ItemName|  ItemCount|  ItemWord|  ItemPrice|  ItemTaxType|  ItemAmount|  ItemRemark
			"   ItemName|  ItemCount|  ItemWord|  ItemPrice|  ItemTaxType|  ItemAmount|  ItemRemark"
		}
	)
	public void testGetParameterMap(String ItemName, String ItemCount, String ItemWord, String ItemPrice,String ItemTaxType, String ItemAmount, String ItemRemark) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException {
		Items items = spy(new Items());
		doReturn(ItemName).when(items).getItemName();
		doReturn(ItemCount).when(items).getItemCount();
		doReturn(ItemWord).when(items).getItemWord();
		doReturn(ItemPrice).when(items).getItemPrice();
		doReturn(ItemTaxType).when(items).getItemTaxType();
		doReturn(ItemAmount).when(items).getItemAmount();
		doReturn(ItemRemark).when(items).getItemRemark();
		
		Map<String,String> expected = new TreeMap<String,String>();
//		expected.put("ItemName", ItemName);
//		expected.put("ItemCount", ItemCount);
//		expected.put("ItemWord", ItemWord);
//		expected.put("ItemPrice", ItemPrice);
//		expected.put("ItemTaxType", ItemTaxType);
//		expected.put("ItemAmount", ItemAmount);
//		expected.put("ItemRemark", ItemRemark);
		
		
		Map<String,String> actual = items.getParameterMap(MachineType.CREATE);
		
		assertEquals(expected, actual);
	}

	@TestWith(
		separator="[\\|]",
		value={
			//      expected|               itemRemark|
			" abc1,abc2,abc3|   ['abc1','abc2','abc3']",
			"     ,abc2,abc3|   ['null','abc2','abc3']",
			"     abc1,,abc3|   ['abc1','null','abc3']",
			"     abc1,abc2,|   ['abc1','abc2','null']",
			"             ,,|               ['','','']",
			"               |                       []",
		}
	)
	public void testGetItemRemark(String expected, String input) throws UnsupportedEncodingException {
		expected = AllpayURLEncoder.encode(expected.replaceAll(",", "|"),"UTF-8");
		JSONArray jsonArr = new JSONArray(input);
		Items items = new Items();
		for(int i = 0 ; i < jsonArr.length() ; i++){
			String itemRemark = "null".equals(jsonArr.getString(i)) ? null : jsonArr.getString(i);
			items.addItem(
					new Item().setItemRemark(itemRemark));
		}
		
		String actual = items.getItemRemark();
		
		assertEquals(expected, actual);
	}

	@TestWith(
		separator="[\\|]",
		value={
			// expected|              itemAmount|
			"     1,2,3|           ['1','2','3']",
			"     0,2,3|        ['null','2','3']",
			"     1,0,3|        ['1','null','3']",
			"     1,2,0|        ['1','2','null']",
			"     0,0,0|  ['null','null','null']",
			"          |                      []",
		}
	)
	public void testGetItemAmount(String expected, String input) {
		expected = expected.replaceAll(",", "|");
		JSONArray jsonArr = new JSONArray(input);
		Items items = new Items();
		for(int i = 0 ; i < jsonArr.length() ; i++){
			String str = "null".equals(jsonArr.getString(i)) ? null : jsonArr.getString(i);
			Integer itemAmount = str == null ? null : Integer.valueOf(str);
			items.addItem(
					new Item().setItemAmount(itemAmount));
		}
		
		String actual = items.getItemAmount();
		
		assertEquals(expected, actual);
	}

	@TestWith(
		separator="[\\|]",
		value={
			//      expected|                                                itemRemark|
			"        1,2,3,9|   ['TAXABLE','ZERO_TAX_RATE','DUTY_FREE','MIXED_TAXABLE']",
			"         ,2,3,9|      ['null','ZERO_TAX_RATE','DUTY_FREE','MIXED_TAXABLE']",
			"         1,,3,9|            ['TAXABLE','null','DUTY_FREE','MIXED_TAXABLE']",
			"         1,2,,9|        ['TAXABLE','ZERO_TAX_RATE','null','MIXED_TAXABLE']",
			"         1,2,3,|            ['TAXABLE','ZERO_TAX_RATE','DUTY_FREE','null']",
			"            ,,,|                             ['null','null','null','null']",
			"               |                                                        []",
		}
	)
	public void testGetItemTaxType(String expected, String input) {
		expected = expected.replaceAll(",", "|");
		JSONArray jsonArr = new JSONArray(input);
		Items items = new Items();
		for(int i = 0 ; i < jsonArr.length() ; i++){
			String itemTaxType = "null".equals(jsonArr.getString(i)) ? null : jsonArr.getString(i);
			Invoice.TaxTypeEnum taxType = itemTaxType == null ? null : Invoice.TaxTypeEnum.valueOf(itemTaxType);
			items.addItem(
					new Item().setItemTaxType(taxType));
		}
		
		String actual = items.getItemTaxType();
		
		assertEquals(expected, actual);
	}

	@TestWith(
		separator="[\\|]",
		value={
			//       expected|                     itemPrice|
			"     100,200,300|           ['100','200','300']",
			"       0,200,300|          ['null','200','300']",
			"       100,0,300|          ['100','null','300']",
			"       100,200,0|          ['100','200','null']",
			"           0,0,0|        ['null','null','null']",
			"                |                            []",
		}
	)
	public void testGetItemPrice(String expected, String input) {
		expected = expected.replaceAll(",", "|");
		JSONArray jsonArr = new JSONArray(input);
		Items items = new Items();
		for(int i = 0 ; i < jsonArr.length() ; i++){
			String str = "null".equals(jsonArr.getString(i)) ? null : jsonArr.getString(i);
			Integer itemPrice = str == null ? null : Integer.valueOf(str);
			items.addItem(
					new Item().setItemPrice(itemPrice));
		}
		
		String actual = items.getItemPrice();
		
		assertEquals(expected, actual);
	}

	@TestWith(
		separator="[\\|]",
		value={
			// expected|       itemRemark|
			"   箱,瓶,打|   ['箱','瓶','打']",
			"     ,瓶,打|  ['null','瓶','打']",
			"     箱,,打|  ['箱','null','打']",
			"     箱,瓶,|  ['箱','瓶','null']",
			"        ,,|         ['','','']",
			"          |                 []",
		}
	)
	public void testGetItemWord(String expected, String input) throws UnsupportedEncodingException {
		expected = AllpayURLEncoder.encode(expected.replaceAll(",", "|"),"UTF-8");
		JSONArray jsonArr = new JSONArray(input);
		Items items = new Items();
		for(int i = 0 ; i < jsonArr.length() ; i++){
			String itemRemark = "null".equals(jsonArr.getString(i)) ? null : jsonArr.getString(i);
			items.addItem(
					new Item().setItemWord(itemRemark));
		}
		
		
		String actual = items.getItemWord();
		
		assertEquals(expected, actual);
	}

	@TestWith(
		separator="[\\|]",
		value={
			// expected|              itemAmount|
			"     1,2,3|           ['1','2','3']",
			"     0,2,3|        ['null','2','3']",
			"     1,0,3|        ['1','null','3']",
			"     1,2,0|        ['1','2','null']",
			"     0,0,0|  ['null','null','null']",
			"          |                      []",
		}
	)
	public void testGetItemCount(String expected, String input) {
		expected = expected.replaceAll(",", "|");
		JSONArray jsonArr = new JSONArray(input);
		Items items = new Items();
		for(int i = 0 ; i < jsonArr.length() ; i++){
			String str = "null".equals(jsonArr.getString(i)) ? null : jsonArr.getString(i);
			Integer itemCount = str == null ? null : Integer.valueOf(str);
			items.addItem(
					new Item().setItemCount(itemCount));
		}
		
		String actual = items.getItemCount();
		
		assertEquals(expected, actual);
	}

	@TestWith(
		separator="[\\|]",
		value={
			//          expected|                  itemRemark|
			"  name1,name2,name3|   ['name1','name2','name3']",
			"       ,name2,name3|    ['null','name2','name3']",
			"       name1,,name3|    ['name1','null','name3']",
			"       name1,name2,|    ['name1','name2','null']",
			"                 ,,|                  ['','','']",
			"                   |                          []",
		}
	)
	public void testGetItemName(String expected, String input) throws UnsupportedEncodingException {
		expected = AllpayURLEncoder.encode(expected.replaceAll(",", "|"),"UTF-8");
		JSONArray jsonArr = new JSONArray(input);
		Items items = new Items();
		for(int i = 0 ; i < jsonArr.length() ; i++){
			String itemName = "null".equals(jsonArr.getString(i)) ? null : jsonArr.getString(i);
			items.addItem(
					new Item().setItemName(itemName));
		}
		
		String actual = items.getItemName();
		
		assertEquals(expected, actual);
	}

}
