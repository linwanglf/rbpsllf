package com.java.test;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestLanguage {

	
	
	
	public static void main(String[] args) {
		//返回Java所支持的全部国家和语言的数组
		Locale[] localeList = Locale.getAvailableLocales();
		//遍历数组的每个元素，依次获取所支持的国家和语言
		for (int i = 0; i < localeList.length ; i++ )
		{
		//打印出所支持的国家和语言
		System.out.println(localeList[i].getDisplayCountry()  + "=" + localeList[i].getCountry()+ "|" + 
						   localeList[i].getDisplayLanguage() + "=" + localeList[i].getLanguage());
		}		
		
		// Locale myLocale = Locale.getDefault(); //如何指定en_US这个Local	
		 Locale  usLocale = Locale.US; //Local类
		 ResourceBundle bundle= ResourceBundle.getBundle("resources/mess",usLocale);  		  
		 String value= bundle.getString("hello");  
		 System.out.println(value);
		
	}
	
}
