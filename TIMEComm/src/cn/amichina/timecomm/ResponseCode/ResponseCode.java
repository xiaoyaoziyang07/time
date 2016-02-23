package cn.amichina.timecomm.ResponseCode;

import java.util.Properties;
/**
 * 
 * Create by 石磊  on 2015年8月20日 下午4:23:15
 * 错误日志类型返回值 字典
 *
 */
public class ResponseCode {
private static  Properties props = null;
public synchronized static String get(String key){
	if(props==null){
		ini();
	}
	return props.getProperty(key);
}
private static  void ini(){
	props =new Properties();
	try {
		props.load(ResponseCode.class.getResourceAsStream("responseCode.properties"));
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}


}
