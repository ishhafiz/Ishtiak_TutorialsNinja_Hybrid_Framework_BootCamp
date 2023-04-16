package com.tn.qa.utils;

import java.util.Date;

public class Utilities {

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = (date.toString().replace(" ", "_").replace(":", "_").trim().substring(4).trim()
				.substring(0, 15).concat("_2023"));
		return "ihafiz75" + timeStamp +"@gmail.com";

	}
	
	public static final int implicitWaitTime = 10;
	public static final int pageLoadTime = 10;
	public static final int scriptTime = 100;
	
}
