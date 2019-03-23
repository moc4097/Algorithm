package com.moc.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main{
	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = df.parse("1901-01-01");
			Date d2 = df.parse("2018-12-25");
			Calendar c1 = new GregorianCalendar();
			Calendar c2 = new GregorianCalendar();
			int cnt = 0;
			c1.setTime(d1);
			c2.setTime(d2);
			c1.add(Calendar.DATE, 1);
			System.out.println(c1.get(Calendar.DATE));
			/*while(c1.before(c2)) {
				if(c1.get(Calendar.DAY_OF_WEEK) == 2) {
					cnt++;
				}
				c1.add(Calendar.DATE, 1);
			}*/
			System.out.println(cnt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}