package cn.amichina.timecomm.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LabelUtil {

	public static List<String> labelsByDay(Date startDate,Date endDate){
		//得到List<Date>
		List<Date> LabelDates = new ArrayList<Date>();
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		do{
			LabelDates.add(startCalendar.getTime());
			startCalendar.add(Calendar.DATE, 1);
		}while(startCalendar.before(endCalendar));
		LabelDates.add(endDate);
		
		//把List<Date>转换成List<String>
		List<String> labelStrings = new ArrayList<String>();
		for(Date d:LabelDates){
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			String label = format.format(d);
			labelStrings.add(label);
		}
		return labelStrings;
	}
	public static List<String> labelsByHour(Date startDate,Date endDate){
		//得到List<Date>
		List<Date> LabelDates = new ArrayList<Date>();
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		startCalendar.add(Calendar.HOUR, 1);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		endCalendar.add(Calendar.DATE, 1);
		endCalendar.add(Calendar.HOUR, 1);
		do{
			LabelDates.add(startCalendar.getTime());
			startCalendar.add(Calendar.HOUR_OF_DAY, 1);
		}while(startCalendar.before(endCalendar));
		
		//把List<Date>转换成List<String>
		List<String> labelStrings = new ArrayList<String>();
		for(Date d:LabelDates){
			SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm");
			String label = format.format(d);
			labelStrings.add(label);
		}
		return labelStrings;
	}
	public static Long DayLabel2Long(String label){
		String[] strs = label.split("/");
		String stat = strs[2]+strs[0]+strs[1];
		return Long.parseLong(stat);
	}
	public static Long HourLabel2Long(String label){
		char[] strs = label.toCharArray();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String stat = year+""+strs[0]+strs[1]+strs[3]+strs[4]+strs[6]+strs[7]+"0000";
		return Long.parseLong(stat);
	}
}
