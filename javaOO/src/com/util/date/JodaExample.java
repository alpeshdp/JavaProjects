package com.util.date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaExample {

	public static void main(String[] args) {
		
//		DateTime current = new DateTime();
//		System.out.println(current);
//		
//		DateTime utc = current.toDateTime(DateTimeZone.UTC);
//		System.out.println(utc);
//		
//		DateTime indiaDate = utc.toDateTime(DateTimeZone.forID("Asia/Kolkata"));
//		DateTime tokyoDate = utc.toDateTime(DateTimeZone.forID("Asia/Tokyo"));
//		System.out.println(indiaDate);
//		System.out.println(tokyoDate);
		
		
		//0. Les say user(In India time zone) submitted a form and following string came as an input.
		String event1 = "10 May 2013 10:10 AM"; // IST Time

		//1. When User Submits date through UI lets convert into Local
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMM yyyy HH:mm a");
		DateTime istDateTimeForEvent1 = formatter.withZone(DateTimeZone.forID("Asia/Kolkata")).parseDateTime(event1);
		
		//2. Convert Local datetime to UTC and Store in database.
		DateTime utcDateTimeForEvent1 = istDateTimeForEvent1.toDateTime(DateTimeZone.UTC);

		//3. While displaying all we need is target DateTimeZone. 
		// We need to store the DateTimeZone or TimeZoneId(String - Exa: Asia/Kolkata, Asia/Tokyo) in user preferences  
		// So during registration we have to map country to DateTimeZone/TimeZoneId and save it.
		
		DateTime tokyoDateTimeForEvent1 = utcDateTimeForEvent1.toDateTime(DateTimeZone.forID("Asia/Tokyo"));
		DateTime puneDateTimeForEvent1 = utcDateTimeForEvent1.toDateTime(DateTimeZone.forID("Asia/Kolkata"));
		System.out.println(event1);
		
		System.out.println("Input date time:" + istDateTimeForEvent1);
		System.out.println("UTC date time:" + utcDateTimeForEvent1);
		System.out.println("PUNE date time:" + puneDateTimeForEvent1);
		System.out.println("TOKYO date time:" + tokyoDateTimeForEvent1);
		
		
	}
}
