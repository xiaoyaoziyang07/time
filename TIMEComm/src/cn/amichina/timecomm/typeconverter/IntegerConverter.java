package cn.amichina.timecomm.typeconverter;

import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
/**
 * 
 * Create by 石磊  on 2015年10月13日 下午4:35:57
 *
 *
 */
public class IntegerConverter extends DefaultTypeConverter {
	@Override 
	public Object convertValue(Map context, Object value, Class toType) { 
		String[] params = (String[])value;
		if(toType==Integer.class){
			return Integer.valueOf(params[0]); 
		}else if(toType == String.class){
			return params[0];
		}
		return null;
	}
}
