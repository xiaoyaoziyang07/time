package cn.amichina.common.chart.trendline;

import java.lang.reflect.Field;

import cn.amichina.common.chart.exception.ChartBuildException;

@SuppressWarnings("unused")
public class Line {

	private String startvalue;
	private String endvalue;
	private String color = "0075c2";
	private String displayvalue;
	private String valueOnRight = "1";
	private String thickness ="2";
	
	public void setStartvalue(String startvalue) {
		this.startvalue = startvalue;
	}

	public void setEndvalue(String endvalue) {
		this.endvalue = endvalue;
	}

	public String getStartvalue() {
		return startvalue;
	}

	public String getEndvalue() {
		return endvalue;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{");
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {

			fields[i].setAccessible(true);
			String name = fields[i].getName();
			String value;
			try {
				value = (String) fields[i].get(this);
			} catch (Exception e) {
				throw new ChartBuildException(e);
			}
			if (value != null) {
				sb.append("\"");
				sb.append(name);
				sb.append("\":\"");
				sb.append(value);
				sb.append("\"");
				sb.append(",");
			}
			if(i==fields.length-1){
				if(sb.charAt(sb.length()-1)==','){
					sb.setCharAt(sb.length()-1, '}');
				}
			}
		}
		return sb.toString();
	}
	
}
