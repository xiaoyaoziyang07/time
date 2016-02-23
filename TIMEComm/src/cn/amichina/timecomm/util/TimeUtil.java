package cn.amichina.timecomm.util;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
public class TimeUtil {
  /**
   * 将java 从1970java记数的毫秒转化为14位的时间字符串
   * YYYYMMDDHHMMSS
   * @param longstr long
   * @return String
   */
  public static String returnDate1970to14(long longstr) {
    String oldstr = String.valueOf(longstr);
    long theMinutes = 0;
    if (oldstr.length() == 13) {
        theMinutes = longstr / 1000;
     }else if (oldstr.length() == 10) {
        theMinutes = longstr;
     }
      //增加了格林尼治时间的修正
    theMinutes = theMinutes + (8*60*60);
    theMinutes = SysMchanges("19700101000000", theMinutes);
    return String.valueOf(theMinutes);
  }
  /**
   * 将java 从1970java记数的毫秒转化为14位的时间字符串
   * YYYYMMDDHHMMSS
   * @param longstr long
   * @return String
   */
  public static String returnDate1970to13(long longstr) {
    String oldstr = String.valueOf(longstr);
    long theMinutes = 0;
    if (oldstr.length() == 13) {
        theMinutes = longstr / 1000;
     }else if (oldstr.length() == 10) {
        theMinutes = longstr;
     }
      //增加了格林尼治时间的修正
    theMinutes = theMinutes + (8*60*60);
    theMinutes = SysMchanges("19700101000000", theMinutes);
    theMinutes /= 1000;
    return String.valueOf(theMinutes);
  }
  /**
   * 返回一个14位日期字符串的1970记数法的值
   * 单位:秒(毫秒已经去除)
   * @param longstr long
   * @return String
   */
  public static long returnDate14to1970(String rq) {
  long theMinutes = 0;
  try {
    SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String rqnew = String.valueOf(rq).substring(0,4) + "-" +
    String.valueOf(rq).substring(4, 6) + "-" +
    String.valueOf(rq).substring(6, 8) + " " +
    String.valueOf(rq).substring(8, 10) + ":" +
    rq.substring(10, 12) + ":" +
    rq.substring(12, 14);
    theMinutes = simpledate.parse(rqnew).getTime();
    theMinutes=theMinutes/1000;
  } catch (ParseException e) {
	  throw new RuntimeException(e);
  }
  return theMinutes;
}

  /**
   * 返回该日期后多少秒的日期
   * 返回日期格式YYYYMMDDHHMMSS
   * @param rq String
   * @param changs int
   * @return long
   */
  public static long SysMchanges(String rq, long changs) {
    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
    String tmps = "";
    try {
      Date date = myFormatter.parse(rq);
      long Time = (date.getTime() / 1000) + changs;
      date.setTime(Time * 1000);
      tmps = DateTimeFunction(date);
      tmps=tmps.replaceAll("-", "");
      tmps=tmps.replaceAll( " ", "");
      tmps=tmps.replaceAll(":", "");
      tmps = tmps.substring(0, 14);
    }catch (ParseException e) {
    	throw new RuntimeException(e);
    }
    return Long.parseLong(tmps);
  }
public static String DateTimeFunction(Date date) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(DateFunction(date) + " ");
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    if (String.valueOf(calendar.get(Calendar.HOUR)).length() == 1) {
	      sb.append("0");
	    }
	    sb.append(String.valueOf(calendar.get(Calendar.HOUR)) + ":");
	    if (String.valueOf(calendar.get(Calendar.MINUTE)).length() == 1) {
	      sb.append("0");
	    }
	    sb.append(String.valueOf(calendar.get(Calendar.MINUTE)) + ":");
	    if (String.valueOf(calendar.get(Calendar.SECOND)).length() == 1) {
	      sb.append("0");
	    }
	    sb.append(String.valueOf(calendar.get(Calendar.SECOND)));
	    return sb.toString();
	  }
  
  //返回当前日期
  public static String DateFunction(Date date) {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
    StringBuffer sb = new StringBuffer();
    sb.append(String.valueOf(calendar.get(Calendar.YEAR) + 1900) + "-");
    if (String.valueOf(calendar.get(Calendar.MONTH)+ 1).length() == 1) {
      sb.append("0");
    }
    sb.append(String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-");
    if (String.valueOf(calendar.get(Calendar.DATE)).length() == 1) {
      sb.append("0");
    }
    sb.append(String.valueOf(calendar.get(Calendar.DATE)));
    return sb.toString();
  }
  
  
  /**
   * 将14位的日期字符串转换为标准格式的日期字符串
   * @param s String
   * @return String
   */
  public static String dateFormat14toDate(String s){
    String tmps = s;
    if(s.length()>=14){
      tmps = s.substring(0,4) + "-" +
          s.substring(4,6) + "-" +
          s.substring(6,8) + " " +
          s.substring(8,10) + ":" +
          s.substring(10,12) + ":" +
          s.substring(12,14);
    }
    return tmps;
  }

  /**
   * 将标准格式的日期字符串转换为14位的日期字符串
   * @param s String
   * @return String
   */
  public static String dateFormatDateto14(String s){
      String tmps = s;
      tmps = tmps.replaceAll(":","");
      tmps = tmps.replaceAll("-","");
      tmps = tmps.replaceAll(" ","");
      if(tmps.length()>14)tmps=tmps.substring(0,14);
      return tmps;
  }

  /**
   * 将13位的java日期装换为 MM/dd/yyyy的日期
   * @param _13Javadate long
   * @return String
   */
  public String time13bitJavatoForigenDate(long _13Javadate){
    String _returnForDate = "";
    _returnForDate = returnDate1970to14(_13Javadate);
    int year=Integer.parseInt(_returnForDate.substring(0,4));
    int mon=Integer.parseInt(_returnForDate.substring(4,6));
    int day=Integer.parseInt(_returnForDate.substring(6,8));
    Calendar calendar=Calendar.getInstance();
    calendar.set(year, mon - 1, day);
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    _returnForDate=formatter.format(calendar.getTime());
    return _returnForDate;
  }

  /**
   * 将普通日期格式装换为 MM/dd/yyyy的日期
   * @param _13Javadate long
   * @return String
   */
  public String time14bittoForigenDate(long _14Javadate){
    String _returnForDate = "";
    _returnForDate = String.valueOf(_14Javadate);
    int year=Integer.parseInt(_returnForDate.substring(0,4));
    int mon=Integer.parseInt(_returnForDate.substring(4,6));
    int day=Integer.parseInt(_returnForDate.substring(6,8));
    Calendar calendar=Calendar.getInstance();
    calendar.set(year, mon - 1, day);
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    _returnForDate=formatter.format(calendar.getTime());
    return _returnForDate;
  }

  /**
   * 返回8位或10位或12位或14位字符型日期的YY-MM-DD hh:mm:ss秒形式
   * @param rq
   * @return
   */
  public String returnStringToRq(String sj) {
      	String retrq = "";
        if (sj == null || sj.length() < 8) {
             return retrq;
        }
        retrq = sj.substring(0, 4) + "-" + sj.substring(4, 6) + "-" + sj.substring(6, 8);
        retrq = sj.length() > 8 ? retrq + " " + sj.substring(8, 10) : retrq;
        retrq = sj.length() > 10 ? retrq + ":" + sj.substring(10, 12) : retrq;
        retrq = sj.length() > 12 ? retrq + ":" + sj.substring(12, 14) : retrq;
        return retrq;
  }

  /**
   * 返回8位或10位或12位或14位字符型日期的YY-MM-DD hh:mm:ss秒形式
   * 只以hh:mm:ss形式返回
   * @param rq
   * @return
   */
  public String returnStringToRq2(String sj) {
      	String retrq = "";
        if (sj == null || sj.length() < 8) {
              return retrq;
        }
        retrq = sj.length() > 8 ? retrq + "" + sj.substring(8, 10) : retrq;
        retrq = sj.length() > 10 ? retrq + ":" + sj.substring(10, 12) : retrq;
        retrq = sj.length() > 12 ? retrq + ":" + sj.substring(12, 14) : retrq;
      return retrq;
    }

  public static void main(String[] args){
	  long ll = System.currentTimeMillis();
	  System.out.println(ll);
	  System.out.println(returnDate14to1970("1437753600"));
  }
}
