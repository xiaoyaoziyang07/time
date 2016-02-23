package cn.amichina.timecomm.typeconverter;

import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
/**
 * 
 * Create by 石磊  on 2015年10月13日 下午4:35:57
 *
 *
 */
public class LongConverter extends DefaultTypeConverter {
	@Override 
	public Object convertValue(Map context, Object value, Class toType) { 
		String[] params = (String[])value;
		return Long.parseLong(params[0]); 
	}
}
