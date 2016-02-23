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
import cn.amichina.common.chart.trendline.Line;
import cn.amichina.common.chart.trendline.TrendLine;

/**
 * @author liyang
 */
public class Chart {

	private ChartProperties chart = new ChartProperties();
	private List<ChartCategory> categories;
	private List<ChartSeries> dataset;
	private List<TrendLine> trendlines;
	/*
	 * 构造函数
	 */
	public Chart(List<ChartCategory> categories,List<ChartSeries> dataset){
		this.categories = categories;
		this.dataset = dataset;
	}
	
	public void setChart(ChartProperties chart) {
		this.chart = chart;
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
	
	public String drawLineChart(){
		trendlines = new ArrayList<TrendLine>();
		List<Line> lines = new ArrayList<Line>();
		for(int i=0;i<dataset.size();i++){
			Line line = new Line();
			double startValue = dataset.get(i).getRatio()[0]*0+dataset.get(i).getRatio()[1];
			double endValue = dataset.get(i).getRatio()[0]*(dataset.get(0).getValue().size()-1)+dataset.get(i).getRatio()[1];
			line.setStartvalue(String.valueOf(startValue));
			line.setEndvalue(String.valueOf(endValue));
			lines.add(line);
		}
//		chart.setYAxisMaxValue(String.valueOf(TrendLineUtils.maxValue(lines)));
//		chart.setYAxisMinValue(String.valueOf(TrendLineUtils.minValue(lines)));
		TrendLine trendline = new TrendLine();
		trendline.setLines(lines);
		trendlines.add(trendline);
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(chart.toString());
		sb.append(",\"categories\":");
		sb.append(categories.toString());
		sb.append(",\"dataset\":");
		sb.append(dataset.toString());
		sb.append(",\"trendlines\":");
		sb.append(trendlines.toString());
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