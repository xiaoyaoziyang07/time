package cn.amichina.timecomm.report.trendingreport.model;
/**
 * 
 * Create by 石磊  on 2015年10月23日 下午5:32:22
 *
 *
 */
public class ChartProperties {
	private static String NUll = "";
	/**
	 * 图表主标题
	 */
	private String caption = NUll;
	/**
	 * 图表副标题
	 */
	private String subCaption = NUll;
	/**
	 * 横向坐标轴X名称
	 */
	private String yAxisname = NUll;
	/**
	 * 是否格式化数字,默认为1(True),自动的给你的数字加上K（千）或M（百万）；若取0,则不加K或M
	 */
	private boolean formatNumberScale = true;
	/**
	 * 增加数字前缀
	 */
	private String numberSuffix = NUll;
	/**
	 * 增加数字前缀
	 */
	private String numberPrefix = NUll;
	/**
	 * 自定义图表元素颜色(为多个,如过过少会重复) 
	 */
	private String paletteColors = NUll;
	/**
	 * 背景颜色
	 */
	private String bgColor = NUll;
	/**
	 * 边框透明度
	 */
	private int borderAlpha = 0;
	/**
	 * 是否显示画布背景边框
	 */
	private boolean showCanvasBorder = true;
	/**
	 * 是否使用渐变颜色
	 */
	private int usePlotGradientColor = 0;
	/**
	 * 每一片的边框透明度
	 */
	private int plotBorderAlpha = 0;
	/**
	 * 每一片的边框填充透明度
	 */
	private int plotFillAlpha = 0;
	/**
	 * 设置图例说明的边框透明度
	 */
	private int legendBorderAlpha = 0;
	/**
	 * 是否显示为图例说明显示阴影
	 */
	private int legendShadow = 0;
	/**
	 * 是否显示值
	 */
	private int showValues = 0;
	/**
	 * 是否显示边框
	 */
	private int showBorder = 0;
	/**
	 * 标签旋转显示时的倾斜角度
	 */
	private int slantlabels = 0;
	/**
	 * 旋转Label 
	 */
	private int rotatelabels = 0;
	/**
	 * label间隔
	 */
	private int labelStep = 0;
	/**
	 * X轴上的刻度值的显示与否，1表示显示、0表示不显示
	 */
	private int showXAxisLine = 0;
	/**
	 * x轴刻度颜色
	 */
	private String xAxisLineColor;
	/**
	 * 设置div的颜色
	 */
	private String divlineColor;
	/**
	 * 水平分区线是否为虚线
	 */
	private int divLineDashed = 0;
	/**
	 * 是否在横向网格带交替的颜色，默认为0(False) 
	 */
	private int showAlternateHGridColor = 0;
	/**
	 * 子标题字体粗细
	 */
	private int subcaptionFontBold = 0;
	/**
	 * 子标题字体大小
	 */
	private int subcaptionFontSize = 0;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSubCaption() {
		return subCaption;
	}

	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}

	public String getyAxisname() {
		return yAxisname;
	}

	public void setyAxisname(String yAxisname) {
		this.yAxisname = yAxisname;
	}

	public boolean getFormatNumberScale() {
		return formatNumberScale;
	}

	public void setFormatNumberScale(boolean formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}

	public String getNumberSuffix() {
		return numberSuffix;
	}

	public void setNumberSuffix(String numberSuffix) {
		this.numberSuffix = numberSuffix;
	}

	public String getNumberPrefix() {
		return numberPrefix;
	}

	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}

	public String getPaletteColors() {
		return paletteColors;
	}

	public void setPaletteColors(String paletteColors) {
		this.paletteColors = paletteColors;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public int getBorderAlpha() {
		return borderAlpha;
	}

	public void setBorderAlpha(int borderAlpha) {
		this.borderAlpha = borderAlpha;
	}

	public boolean isShowCanvasBorder() {
		return showCanvasBorder;
	}

	public void setShowCanvasBorder(boolean showCanvasBorder) {
		this.showCanvasBorder = showCanvasBorder;
	}

	public int getUsePlotGradientColor() {
		return usePlotGradientColor;
	}

	public void setUsePlotGradientColor(int usePlotGradientColor) {
		this.usePlotGradientColor = usePlotGradientColor;
	}

	public int getPlotBorderAlpha() {
		return plotBorderAlpha;
	}

	public void setPlotBorderAlpha(int plotBorderAlpha) {
		this.plotBorderAlpha = plotBorderAlpha;
	}

	public int getPlotFillAlpha() {
		return plotFillAlpha;
	}

	public void setPlotFillAlpha(int plotFillAlpha) {
		this.plotFillAlpha = plotFillAlpha;
	}

	public int getLegendBorderAlpha() {
		return legendBorderAlpha;
	}

	public void setLegendBorderAlpha(int legendBorderAlpha) {
		this.legendBorderAlpha = legendBorderAlpha;
	}

	public int getLegendShadow() {
		return legendShadow;
	}

	public void setLegendShadow(int legendShadow) {
		this.legendShadow = legendShadow;
	}

	public int getShowValues() {
		return showValues;
	}

	public void setShowValues(int showValues) {
		this.showValues = showValues;
	}

	public int getShowBorder() {
		return showBorder;
	}

	public void setShowBorder(int showBorder) {
		this.showBorder = showBorder;
	}

	public int getSlantlabels() {
		return slantlabels;
	}

	public void setSlantlabels(int slantlabels) {
		this.slantlabels = slantlabels;
	}

	public int getRotatelabels() {
		return rotatelabels;
	}

	public void setRotatelabels(int rotatelabels) {
		this.rotatelabels = rotatelabels;
	}

	public int getLabelStep() {
		return labelStep;
	}

	public void setLabelStep(int labelStep) {
		this.labelStep = labelStep;
	}

	public int getShowXAxisLine() {
		return showXAxisLine;
	}

	public void setShowXAxisLine(int showXAxisLine) {
		this.showXAxisLine = showXAxisLine;
	}

	public String getxAxisLineColor() {
		return xAxisLineColor;
	}

	public void setxAxisLineColor(String xAxisLineColor) {
		this.xAxisLineColor = xAxisLineColor;
	}

	public String getDivlineColor() {
		return divlineColor;
	}

	public void setDivlineColor(String divlineColor) {
		this.divlineColor = divlineColor;
	}

	public int getDivLineDashed() {
		return divLineDashed;
	}

	public void setDivLineDashed(int divLineDashed) {
		this.divLineDashed = divLineDashed;
	}

	public int getShowAlternateHGridColor() {
		return showAlternateHGridColor;
	}

	public void setShowAlternateHGridColor(int showAlternateHGridColor) {
		this.showAlternateHGridColor = showAlternateHGridColor;
	}

	public int getSubcaptionFontBold() {
		return subcaptionFontBold;
	}

	public void setSubcaptionFontBold(int subcaptionFontBold) {
		this.subcaptionFontBold = subcaptionFontBold;
	}

	public int getSubcaptionFontSize() {
		return subcaptionFontSize;
	}

	public void setSubcaptionFontSize(int subcaptionFontSize) {
		this.subcaptionFontSize = subcaptionFontSize;
	}

}
