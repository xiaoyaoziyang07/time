package cn.amichina.common.chart.demo;

import java.util.ArrayList;
import java.util.List;

import cn.amichina.common.chart.Chart;
import cn.amichina.common.chart.category.ChartCategory;
import cn.amichina.common.chart.property.ChartProperties;
import cn.amichina.common.chart.series.ChartSeries;

import com.opensymphony.xwork2.ActionSupport;

public class ChartAction extends ActionSupport {
	
	private String data;
	private String str1;
	private String str2;
	private String str3;
	private String str4;
	
	
	@Override
	public String execute() throws Exception {
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		return SUCCESS;
	}
	public String json01(){
		List<String> list1 = new ArrayList<String>();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list1.add("d");
		list1.add("e");
		ChartCategory category = new ChartCategory(list1);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		
		List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();
		
		List<String> list2 = new ArrayList<String>();
		list2.add("3345");
		list2.add("6253");
		list2.add("7645");
		list2.add("7069");
		list2.add("1033");
		ChartSeries series1 = new ChartSeries("aa",list2);
		chartSeries.add(series1);
		
		ChartProperties props = new ChartProperties();
		props.setCaption("hello world");
		Chart chart = new Chart(categories, chartSeries);
		chart.setChart(props);
		this.data = chart.drawChart();
		return SUCCESS;
	}
	public String json02(){
		List<String> list1 = new ArrayList<String>();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list1.add("d");
		list1.add("e");
		ChartCategory category = new ChartCategory(list1);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		
		List<String> list2 = new ArrayList<String>();
		list2.add("3345");
		list2.add("6253");
		list2.add("7645");
		list2.add("7069");
		list2.add("1033");
		ChartSeries series1 = new ChartSeries("First",list2);
		
		List<String> list3 = new ArrayList<String>();
		list3.add("1086");
		list3.add("2253");
		list3.add("3645");
		list3.add("4069");
		list3.add("2033");
		ChartSeries series2 = new ChartSeries("Second",list3);
		
		List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();
		chartSeries.add(series1);
		chartSeries.add(series2);
		
		Chart chart = new Chart(categories, chartSeries);
		this.data = chart.drawChart();
		return SUCCESS;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}
	public String getStr4() {
		return str4;
	}
	public void setStr4(String str4) {
		this.str4 = str4;
	}
}
