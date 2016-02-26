package cn.amichina.common.chart;
import java.util.ArrayList;
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
import cn.amichina.common.chart.utils.DataSetUtils;

/**
 * @author liyang
 */
public class LineChart extends Chart {

	private List<TrendLine> trendlines;
	/*
	 * 构造函数
	 */
	public LineChart(ChartProperties chart,List<ChartCategory> categories,List<ChartSeries> dataset){
		chart.setYAxisMaxValue(String.valueOf(Math.ceil(DataSetUtils.getMax(dataset)*1.2)));
		chart.setYAxisMinValue(String.valueOf(Math.floor(DataSetUtils.getMin(dataset)*0.8)));
		this.categories = categories;
		this.dataset = dataset;
	}
	
	/**
	 * 拼接成JSON字符串
	 * @return fusionchart需要的JSON字符串
	 */
	public String drawChart(){
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
}