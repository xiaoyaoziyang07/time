package cn.amichina.timecomm.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtilTest {

	public static void main(String[] args) {
		String str_Date ="08/19/2015";
		//System.out.println(DateUtil.toStr(new Date(1435644107795l)));
		//Date d_Date =DateUtil.add(DateUtil.parseDate(str_Date),Calendar.DATE,1);
		System.out.println(DateUtil.toStr(DateUtil.parseDate(str_Date)));
		System.out.println(DateUtil.parseDate(str_Date).getTime());
		
		Date d = new Date(Long.parseLong("1439740800000"));
		System.out.println(DateUtil.toStr(d));
		System.out.println(DateUtil.toStr(DateUtil.add(d, Calendar.MONTH, 1)));
		System.out.println(DateUtil.add(d, Calendar.MONTH, 1).getTime());
		System.out.println(DateUtil.add(d, Calendar.MONTH, 1).getTime()-Long.parseLong("1435643533655"));
		System.out.println("2592000000");
	}
}
