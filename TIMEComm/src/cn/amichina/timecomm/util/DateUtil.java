package cn.amichina.timecomm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String EN_DATA_FORMAT_MM_DD_YYYY="MM/dd/yyyy";
	public static final String EN_DATA_FORMAT_MM_DD="MM/dd";
	public static final String EN_DATA_FORMAT_MM_DD_HH="MM/dd:HH";
	public static final String EN_DATA_FORMAT_MM_DD_HH_MM="MM/dd HH:mm";
	public static final String EN_DATA_FORMAT_MM_DD_HOUR="MM/dd:mm";
	public static final String EN_DATA_FORMAT_YYYY_MM_DD="yyyy/MM/dd";
	public static final String EN_DATA_FORMAT_YYYYMMDD="yyyyMMdd";
	public static final String EN_DATA_FORMAT_YYYYMMDDMM="yyyyMMddmm";
	public static final String EN_DATA_FORMAT_YYYYMMDDHH="yyyyMMddhh";
	public static final String EN_DATA_FORMAT_MMDD="MMdd";
	public static final String EN_DATA_FORMAT_MM_DD_YYYY_HH_MM_SS="MM/dd/yyyy HH:mm:ss";
	public static final String EN_DATA_FORMAT_HH_MM="HH:mm";
	public static final String DB_DATA_FORMAT_MM_DD_YYYY_MM_HH_SS="yyyyMMddHHmmss";
	
	public static final String EN_DATA_FORMAT_MM_DD_YYYY_MM_HH_SS_MS="MM/dd/yyyy HH:mm:ss:ms";
	public static final String CN_DATA_FORMAT_MM_DD_YYYY_MM_HH_SS_MS="yyyy年MM月dd日 HH:mm:ss:ms";
	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	public static void main(String[] args) {
		System.out.println(parseDate("10/12 00:00","MM/dd hh:mm"));
	}
	public static Date add(final Date date, int field, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);  
		switch (field) {
		case Calendar.DATE:
			int currentMon= calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			if(currentMon==calendar.get(Calendar.DATE)){
				if(value>0){
			        calendar.set(Calendar.DAY_OF_MONTH, 1);
			        calendar.add(Calendar.MONTH, 1);
					break;
				}
			}
			calendar.add(field, value);
			break;
		case Calendar.MONTH:
			break;
		case Calendar.MINUTE:
			calendar.add(field, value);
			break;
		default:
			calendar.add(field, value);
			//throw new RuntimeException("还没实现呢!");
		}
		return calendar.getTime();
	}
	public static boolean eqDate(Date date1,Date date2) {
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
		String str_Date1 =format.format(date1);
		return str_Date1.equals(format.format(date2));
	}
	public static Date parseDate(String str_Date,String formatStr) {
		SimpleDateFormat format =new SimpleDateFormat(formatStr);
		try {
			return format.parse(str_Date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static Date parseDate(String str_Date) {
		return parseDate(str_Date,EN_DATA_FORMAT_MM_DD_YYYY);
	}
	public static String toStr(Date date,String format){
		return new SimpleDateFormat(format).format(date);
	}
	public static String toDBStr(Date date){
		return new SimpleDateFormat(EN_DATA_FORMAT_YYYYMMDD).format(date);
	}
	public static String toStr(Date date){
		return toStr(date,CN_DATA_FORMAT_MM_DD_YYYY_MM_HH_SS_MS);
	}
	public static Long date2DBDateAsLong(Date date){
		return Long.parseLong(toStr(date,DateUtil.EN_DATA_FORMAT_YYYYMMDD));
	}
	public static Integer toDBStrInteger(Date date){
		return Integer.parseInt(toDBStr(date));
	}
	public static boolean eqDateAndHour(Date date1, Date date2) {
		SimpleDateFormat format =new SimpleDateFormat("MM-dd mm");
		String str_Date1 =format.format(date1);
		return str_Date1.equals(format.format(date2));
	}
	public static Long toDBDateTime(Date date){
		return Long.parseLong(toStr(date, DB_DATA_FORMAT_MM_DD_YYYY_MM_HH_SS));
	}
	public static Long toDBDate(String str){
		return Long.parseLong(str.replace("/", ""));
	}
	
}
