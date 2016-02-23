package cn.amichina.timecomm.sys.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Create by 石磊 on 2015年8月20日 下午4:24:38
 * 
 * 系统常量字典
 */
public class FinalString {
	/**
	 * 套餐状态字典
	 */
	public static final Map<String, String> SERVICE_STAUTS;
	static {
		SERVICE_STAUTS = new HashMap<String, String>();
		SERVICE_STAUTS.put("0", "In Service");
		SERVICE_STAUTS.put("1", "NEN");
		SERVICE_STAUTS.put("2", "Throttle");
		SERVICE_STAUTS.put("3", "Throttle");
		SERVICE_STAUTS.put("4", "Postpaid Suspand");
		SERVICE_STAUTS.put("5", "Free Boost");
		SERVICE_STAUTS.put("6", "Pay Boost");
	}
}
