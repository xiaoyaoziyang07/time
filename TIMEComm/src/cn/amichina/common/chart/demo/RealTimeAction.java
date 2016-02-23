package cn.amichina.common.chart.demo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import cn.amichina.common.chart.utils.DataSetUtils;
import cn.amichina.common.chart.utils.RealTimeChartUtils;

public class RealTimeAction extends ActionSupport{

	private String data;
	
	@Override
	public String execute(){
		
		List<String> labels = new ArrayList<String>();
		labels.add("a");
		labels.add("b");
		labels.add("c");
		List<String> value1 = new ArrayList<String>();
		value1.add("25");
		value1.add("45");
		value1.add("42");
		
		List<String> value2 = new ArrayList<String>();
		value2.add("37");
		value2.add("51");
		value2.add("64");
		
		List<List<String>> values = new ArrayList<List<String>>();
		values.add(value1);
		values.add(value2);
		
		data = RealTimeChartUtils.realTimeString(labels, values);
		System.out.println(data);
		return SUCCESS;
	}

	public String json(){
		List<String> list = new ArrayList<String>();
		double d1 = Math.random()*100;
		double d2 = Math.random()*100;
		String str1 = String.valueOf(d1);
		String str2 = String.valueOf(d2);
		list.add(str1);
		list.add(str2);
		Date date = new Date();
		DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
		String label = df.format(date);
		data = RealTimeChartUtils.realTimeString(label, list);
		System.out.println(data);
		return SUCCESS;
	}
	
	public String getData() {
		return data;
	}
	
}
