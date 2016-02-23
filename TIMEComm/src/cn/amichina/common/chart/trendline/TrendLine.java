package cn.amichina.common.chart.trendline;

import java.util.List;

public class TrendLine {

	private List<Line> lines;
	
	@Override
	public String toString(){
		return "{\"line\":"+lines.toString()+"}";
	}

	public List<Line> getLines() {
		return lines;
	}
	
	public void setLines(List<Line> lines){
		this.lines = lines;
	}
}
