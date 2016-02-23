package cn.amichina.timecomm.report.trendingreport.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Create by 石磊  on 2015年10月23日 下午5:33:04
 *
 *堆积图 数据集
 */
public class StackedAreaDateSet {
	/**
	 * 
	 */
	private StackedAreaCategoris categoris;
	
	private ChartProperties chartProperties;
	
	public ChartProperties getChartProperties() {
		return chartProperties;
	}
	public void setChartProperties(ChartProperties chartProperties) {
		this.chartProperties = chartProperties;
	}
	private List<StackedAreaSeriesname> seriesnames= new ArrayList<StackedAreaSeriesname>(0);
	public void addSeriesname(StackedAreaSeriesname seriesname){
		seriesnames.add(seriesname);
	}
	public StackedAreaCategoris getCategoris() {
		return categoris;
	}
	public void setCategoris(StackedAreaCategoris categoris) {
		this.categoris = categoris;
	}
	public List<StackedAreaSeriesname> getSeriesnames() {
		return seriesnames;
	}
	public void setSeriesnames(List<StackedAreaSeriesname> seriesnames) {
		this.seriesnames = seriesnames;
	}
	@Override
	public String toString() {
		return "StackedAreaDateSet [categoris=" + categoris + ", seriesnames="
				+ seriesnames + "]";
	}
}
