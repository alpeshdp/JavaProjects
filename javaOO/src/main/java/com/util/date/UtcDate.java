package com.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Locale.Builder;
import java.util.spi.TimeZoneNameProvider;

public class UtcDate {

	public static void main(String[] args) throws ParseException {
		
		String input = "10 May 2013 10:10 AM";
		
		// Parse input to user's locale
		Locale in = new Locale.Builder().setLanguage("en").setRegion("IN").build();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a", in);
		Date indiaDateTime = dateFormat.parse(input);
		System.out.println("India Date Time :" + indiaDateTime);
		
		// Convert it to UTC
		
	}
}
