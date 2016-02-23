package cn.amichina.timecomm.report.trendingreport.model;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * Create by 石磊  on 2015年10月23日 下午5:32:40
 *堆积图 列
 *
 */
public class StackedAreaSeriesname {
	private String  seriesname;
	private List<String> values=new ArrayList<String>(0);
	@Override
	public String toString() {
		return "StackedAreaSeriesname [seriesname=" + seriesname + ", values="
				+ values + "]";
	}

	public void addValue(String val){
		values.add(val);
	}
	
	public String getSeriesname() {
		return seriesname;
	}
	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
	

}
