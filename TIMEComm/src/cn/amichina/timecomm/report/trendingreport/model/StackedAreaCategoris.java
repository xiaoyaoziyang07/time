package cn.amichina.timecomm.report.trendingreport.model;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * Create by 石磊  on 2015年10月23日 下午5:32:51
 *
 *堆积图分类
 */
public class StackedAreaCategoris {

	@Override
	public String toString() {
		return "StackedAreaCategoris [labels=" + labels + "]";
	}
	private List<String> labels = new ArrayList<String>(0);
	public void addLabel(String lable){
		labels.add(lable);
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
}
