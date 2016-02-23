package cn.amichina.common.chart.category;
/***************************************
 * HISTORY
 * 
 * 2015/10/30 liyang 创建文件
 * 
 **************************************/
import java.util.List;

public class ChartCategory {

	private List<String> label;

	public ChartCategory(List<String> labels){
		this.label = labels;
	}
	
	/**
	 * 返回category模块对应的JSON字符串
	 */
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("{\"category\":[");
		for (int i=0;i<label.size();i++) {
			sb.append("{\"label\":\"");
			sb.append(label.get(i));
			sb.append("\"}");
			if(i==label.size()-1){
				sb.append("]}");
				break;
			}
			sb.append(",");
		}
		return sb.toString();
	}

	public List<String> getLabel(){
		return this.label;
	}
}
