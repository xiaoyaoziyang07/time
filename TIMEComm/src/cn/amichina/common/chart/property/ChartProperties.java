package cn.amichina.common.chart.property;
/********************************
 * 
 * 2015/10/30 liyang 创建文件
 * 
 *******************************/
import java.lang.reflect.Field;

import cn.amichina.common.chart.exception.ChartBuildException;

@SuppressWarnings("unused")
public class ChartProperties {
	
	/*
	 *图控常量部分 
	 */
	private static final String legendBorderAlpha  = "0";
//	private static final String allowpinmode = "1";
	private static final String dynamicaxis = "0";
	private static final String bgcolor = "#FFFFFF";
	private static final String divlinecolor = "#CCCCCC";
	private static final String showalternatehgridcolor = "0";
	private static final String showplotborder = "0";
	private static final String showcanvasborder = "0";
	private static final String showshadow = "0";
	private static final String scrollcolor = "#CCCCCC";
	private static final String canvasbottommargin = "30";
	private static final String yaxisvaluespadding = "10";
	private static final String legendShadow = "0";
	private static final String showborder = "0";
	private static final String showLabels = "1";
	private static final String xAxisLineColor = "#4da9a3";
//	private static final String palettecolors="#a301a1,#f64ffa,#fe0b00,#fe4d01,#fb7b1b,#f7c645,#c4b42e,#61b42e,#acd718,#e4f208,#4d3fc9,#0122af,#0491c9,#7adbf2,#6fffff";
	private static final String palettecolors="";
	private static final String usePlotGradientColor="0";
	private static final String showvalues = "0";
//	private static final String plotFillAlpha = "80";
	private static final String plotFillAlpha = "";
	private static final String labelDisplay = "rotate";
	private static final String slantLabels = "1";
	private static final String divLineDashed = "1";
	private static final String showXAxisLine = "1";
	private static final String showHoverEffect = "1";
	private static final String maxLabelHeight = "100";
	private static final String useEllipsesWhenOverflow = "1";
	private static final String formatNumberScale = "0";
	private static final String numDivLines = "9";
	private static final String drawAnchors = "1";
	/*
	 * 公共变量部分
	 */
	private String anchorradius;
	private String caption;
	private String subCaption;
	private String xAxisName;
	private String yAxisName;
	private String labelStep;
	private String palette;
	private String canvasPadding;
	private String clickURL;
	private String yAxisMinValue;
	private String yAxisMaxValue;
	private String showLegend;
//	private String numDivLines;
//	
//	
//	public void setNumDivLiness(String numDivLines) {
//		this.numDivLines = numDivLines;
//	}

	public String getClickURL() {
		return clickURL;
	}
	
	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}
	private String appids;
	public String getAppids() {
		return appids;
	}

	public void setAppids(String appids) {
		this.appids = appids;
	}
	/*
	 *线图变量 
	 */
	private String connectNullData;//1,0
	private String linethickness;
	/*
	 * 柱图变量
	 */
	private String decimals;

	/*
	 * 实时图变量
	 */
	private String dataStreamURL;
	private String dataStamp;
	private String refreshInterval;
	
	/**
	 * 焦点的半径
	 * 大于等于0
	 * @param anchorradius 大于等于0
	 */
	public void setAnchorradius(String anchorradius) {
		this.anchorradius = anchorradius;
	}

	/**
	 * 图表标题
	 * @param caption 图表标题
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * 图表副标题
	 * @param subCaption 图表副标题
	 */
	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}
	

	public String getDataStreamURL() {
		return dataStreamURL;
	}

	public void setDataStreamURL(String dataStreamURL) {
		this.dataStreamURL = dataStreamURL;
	}

	/**
	 * X轴名称
	 * @param xAxisName X轴名称
	 */
	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}

	/**
	 * Y轴名称
	 * @param yAxisName Y轴名称
	 */
	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}

	/**
	 * label的步进
	 * @param labelStep 大于1的整数
	 */
	public void setLabelStep(String labelStep) {
		this.labelStep = labelStep;
	}

	/**
	 * 设置主题
	 * 1-5
	 * @param palette 1-5
	 */
	public void setPalette(String palette) {
		this.palette = palette;
	}

	/**
	 * 
	 * @param 还没有弄懂
	 */
	public void setConnectNullData(String connectNullData) {
		this.connectNullData = connectNullData;
	}

	/**
	 * 设置线宽
	 * @param linethickness  >1的整数
	 */
	public void setLinethickness(String linethickness) {
		this.linethickness = linethickness;
	}

	/**
	 * 设置画布的边距
	 * @param canvasPadding >10的整数
	 */
	public void setCanvasPadding(String canvasPadding) {
		this.canvasPadding = canvasPadding;
	}

	/**
	 * 
	 * @param decimals 小数点的位数，大于0小于10
	 */
	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}

	/**
	 * 
	 * @param refreshInterval 刷新实时图的时间间隔
	 */
	public void setRefreshInterval(String refreshInterval) {
		this.refreshInterval = refreshInterval;
	}
	
	public void setYAxisMinValue(String yAxisMinValue) {
		this.yAxisMinValue = yAxisMinValue;
	}

	public void setYAxisMaxValue(String yAxisMaxValue) {
		this.yAxisMaxValue = yAxisMaxValue;
	}
	
	public void setShowLegend(String showLegend) {
		this.showLegend = showLegend;
	}

	/**
	 * @return fusionchart中chart部分对应的字符串
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("\"chart\":{");
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