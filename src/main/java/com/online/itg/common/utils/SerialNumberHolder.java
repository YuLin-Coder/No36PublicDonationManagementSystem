package com.online.itg.common.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class SerialNumberHolder {
	
	 // 原子Integer递增对象
	 public static AtomicInteger counter_integer = new AtomicInteger(0);// = new AtomicInteger(0);
	 public static AtomicInteger getCounter_integer() {
		return counter_integer;
	}
	public static void setCounter_integer(AtomicInteger counter_integer) {
		SerialNumberHolder.counter_integer = counter_integer;
	}
	static class singletonHolder {      
	        static SerialNumberHolder instance = new SerialNumberHolder();  
	    }     
	 public static SerialNumberHolder getInstance() {     
	 
	     return singletonHolder.instance;     
	 
	 }
}
