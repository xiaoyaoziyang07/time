package cn.amichina.common.chart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/***********************************
 * HISTORY
 * 
 * 2015/10/30 liyang 创建文件
 * 
 ***********************************/
import java.util.List;

import cn.amichina.common.chart.category.ChartCategory;
import cn.amichina.common.chart.property.ChartProperties;
import cn.amichina.common.chart.series.ChartSeries;

/**
 * @author liyang
 */
public class Chart {

	protected ChartProperties chart = new ChartProperties();
	protected List<ChartCategory> categories;
	protected List<ChartSeries> dataset;
//	private List<TrendLine> trendlines;
	
	protected Chart(){
		
	}
	/*
	 * 构造函数
	 */
	public Chart(List<ChartCategory> categories,List<ChartSeries> dataset){
		this.categories = categories;
		this.dataset = dataset;
	}
	
	public Chart(ChartProperties chart,List<ChartCategory> categories,List<ChartSeries> dataset){
		this.chart = chart;
		this.categories = categories;
		this.dataset = dataset;
	}
	
	/**
	 * 拼接成JSON字符串
	 * @return fusionchart需要的JSON字符串
	 */
	public String drawChart(){
		String s1 = chart.toString();
		String s2 = categories.toString();
		String s3 = dataset.toString();
		return "{"+s1+",\"categories\":"+s2+",\"dataset\":"+s3+"}";
	}
	
	public String drawColchart(){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(chart.toString());
		sb.append(",\"categories\":");
		sb.append(categories.toString());
		sb.append(",\"dataset\":");
		sb.append(refact());
		sb.append("}");
		
		return sb.toString();
	}
	
	private String refact(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		List<List<Object[]>> outerList = new ArrayList<List<Object[]>>();
		for(int j=0;j<categories.get(0).getLabel().size();j++){
			List<Object[]> innerList = new ArrayList<Object[]>();
			for(int i=0;i<dataset.size()-1;i++){
				String value = dataset.get(i).getValue().get(j);
				if(null!=value&&!value.equals("0")&&!value.equals("Other")){
					innerList.add(new Object[]{value,dataset.get(i).getSeriesname()});
				}
			}
			Collections.sort(innerList, new Comparator<Object[]>() {

				@Override
				public int compare(Object[] o1, Object[] o2) {
					return Double.parseDouble((String)o1[0])>Double.parseDouble((String)o2[0])?-1:1;
				}
				
			});
			innerList.add(new Object[]{dataset.get(dataset.size()-1).getValue().get(j),dataset.get(dataset.size()-1).getSeriesname()});
			for(int m=innerList.size();m<6;m++){
				innerList.add(new Object[]{"",""});
			}
			outerList.add(innerList);
		}
		for(int i=0;i<6;i++){
			sb.append("{\"data\":[");
			for(int j=0;j<outerList.size();j++){
				sb.append("{\"value\":\"");
				sb.append(outerList.get(j).get(i)[0]);
				sb.append("\",\"tooltext\":\"");
				if(null!=outerList.get(j).get(i)[0]&&!outerList.get(j).get(i)[0].equals("")){
					sb.append(outerList.get(j).get(i)[1]+", "+"$label"+", "+"$datavalue");
				}
				sb.append("\"}");
				if(j==outerList.size()-1){
					sb.append("]}");
					break;
				}
				sb.append(",");
			}
			if(i==5){
				sb.append("]");
				break;
			}
			sb.append(",");
		}
		return sb.toString();
	}
}