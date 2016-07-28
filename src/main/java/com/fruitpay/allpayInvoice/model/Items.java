package com.fruitpay.allpayInvoice.model;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fruitpay.allpayInvoice.interfaces.PostParameterMap;
import com.fruitpay.allpayInvoice.machine.MachineType;
import com.fruitpay.allpayInvoice.util.AllpayURLEncoder;

public class Items implements PostParameterMap{
	private List<Item> items;
	
	public Items(){
		items = new ArrayList<Item>();
	}
	
	public Items addItem(Item item){
		items.add(item);
		return this;
	}
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	


	public String getItemRemark() throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for(Item item : items){
			sb.append((item.getItemRemark() == null ? "" : item.getItemRemark()) + "|");
		}
		if(items.size() > 0){
			return AllpayURLEncoder.encode(sb.substring(0, sb.length() - 1),"UTF-8");
		}else{
			return "";
		}
	}

	public String getItemAmount() {
		StringBuilder sb = new StringBuilder();
		for(Item item : items){
			sb.append((item.getItemAmount() == null ? "0" : item.getItemAmount()) + "|");
		}
		if(items.size() > 0){
			return sb.substring(0, sb.length() - 1);
		}else{
			return "";
		}
	}

	public String getItemTaxType() {
		StringBuilder sb = new StringBuilder();
		for(Item item : items){
			sb.append((item.getItemTaxType() == null ? "" : item.getItemTaxType().value()) + "|");
		}
		if(items.size() > 0){
			return sb.substring(0, sb.length() - 1);
		}else{
			return "";
		}
	}

	public String getItemPrice() {
		StringBuilder sb = new StringBuilder();
		for(Item item : items){
			sb.append((item.getItemPrice() == null ? "0" : item.getItemPrice()) + "|");
		}
		if(items.size() > 0){
			return sb.substring(0, sb.length() - 1);
		}else{
			return "";
		}
	}

	public String getItemWord() throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for(Item item : items){
			sb.append((item.getItemWord() == null ? "" : item.getItemWord()) + "|");
		}
		if(items.size() > 0){
			return AllpayURLEncoder.encode(sb.substring(0, sb.length() - 1),"UTF-8");
		}else{
			return "";
		}
	}

	public String getItemCount() {
		StringBuilder sb = new StringBuilder();
		for(Item item : items){
			sb.append((item.getItemCount() == null ? "0" : item.getItemCount()) + "|");
		}
		if(items.size() > 0){
			return sb.substring(0, sb.length() - 1);
		}else{
			return "";
		}
	}

	public String getItemName() throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for(Item item : items){
			sb.append((item.getItemName() == null ? "" : item.getItemName()) + "|");
		}
		if(items.size() > 0){
			return AllpayURLEncoder.encode(sb.substring(0, sb.length() - 1),"UTF-8");
		}else{
			return "";
		}
	}
	
	@Override
	public Map<String, String> getParameterMap(MachineType machineType)
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, InvocationTargetException, UnsupportedEncodingException {
		Map<String, String> parameterMap = new TreeMap<String, String>();
		if(items.size() > 0){
			parameterMap.put("ItemName", getItemName());
			parameterMap.put("ItemCount", getItemCount());
			parameterMap.put("ItemWord", getItemWord());
			parameterMap.put("ItemPrice", getItemPrice());
			parameterMap.put("ItemTaxType", getItemTaxType());
			parameterMap.put("ItemAmount", getItemAmount());
			parameterMap.put("ItemRemark", getItemRemark());
		}
		return parameterMap;
	}
}
