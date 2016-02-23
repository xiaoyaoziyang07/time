package cn.amichina.common.chart.utils;

import java.util.List;

import cn.amichina.common.chart.trendline.Line;

public class TrendLineUtils {

	public static double minValue(List<Line> list) {
		if(null==list||list.size()==0){
			throw new IllegalArgumentException("minValue方法的参数不正确");
		}
		double min = Double.parseDouble(list.get(0).getStartvalue());
		for (Line l : list) {
			double a = Math.min(Double.parseDouble(l.getStartvalue()),Double.parseDouble(l.getStartvalue()));
			if(min <a){
				min = a;
			}
		}
		return min;
	}

	public static double maxValue(List<Line> list) {
		if(null==list||list.size()==0){
			throw new IllegalArgumentException("maxValue方法的参数不正确");
		}
		double max = Double.parseDouble(list.get(0).getStartvalue());
		for (Line l : list) {
			double a = Math.max(Double.parseDouble(l.getStartvalue()),Double.parseDouble(l.getStartvalue()));
			if(max <a){
				max = a;
			}
		}
		return max;
	}
}
