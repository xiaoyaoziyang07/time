package cn.amichina.common.chart.utils;

import java.util.List;

public class RealTimeChartUtils {

	/**
	 * 
	 * @param label 时间
	 * @param value 从数据库中查出来的数据
	 * @return 实时图需要的字符串
	 */
	public static String realTimeString(List<String> label,List<List<String>> values){
		String json1 = "&label=";
		for(int i=0;i<label.size();i++){
			if(label.get(i)!=null){
				json1 += label.get(i);
			}
			if(i==label.size()-1){
				break;
			}
			json1 += ",";
		}
		
		String json2 = "&value=";
		for(int i=0;i<values.size();i++){
			for(int j=0;j<values.get(i).size();j++){
				String value = values.get(i).get(j);
				if(value!=null){
					json2 += value;
				}else{
					json2 += "0";
				}
				if(j==values.get(i).size()-1){
					break;
				}
				json2 += ",";
			}
			if(i==values.size()-1){
				break;
			}
			json2 += "|";
		}
		return json1+json2;
	}
	
	public static String realTimeString(String label,List<String> values){
		String json = "&label="+label+"&value=";
		for(int i=0;i<values.size();i++){
			if(values.get(i)!=null){
				json += values.get(i);
			}else{
				json +="0";
			}
			if(i==values.size()-1){
				break;
			}
			json += "|";
		}
		return json;
	}
}
