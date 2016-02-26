package cn.amichina.common.chart.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.amichina.common.chart.series.ChartSeries;
import cn.amichina.timecomm.util.LabelUtil;

public class DataSetUtils {

	/**
	 * 
	 * @param isDay 天还是小时
	 * @param labels category系列
	 * @param dbData 从数据库中查出来的数据
	 * @param col 要进行转换的列名
	 * @return 空数据补零之后的数据集合，单位没有进行转换，传给图控就可以用了
	 */
	public static List<String> labelvalue(boolean isDay, List<String> labels,Map<Long, Map<String, Object>> dbData, String col) {
		Map<Long, Long> map = new TreeMap<Long, Long>();
		for (int i = 0; i < labels.size(); i++) {
			Long key;
			if (isDay) {
				key = LabelUtil.DayLabel2Long(labels.get(i));
			} else {
				key = LabelUtil.HourLabel2Long(labels.get(i));
			}
			map.put(key, 0L);
		}
		for (Map.Entry<Long, Map<String, Object>> entry : dbData.entrySet()) {
			String tmpLong = String.valueOf(entry.getKey());
			map.put(Long.parseLong(tmpLong),Long.parseLong(entry.getValue().get(col).toString()));
		}
		List<String> values = new ArrayList<String>(labels.size());
		for (Map.Entry<Long, Long> en : map.entrySet()) {
			values.add(String.valueOf(en.getValue()));
		}
		return values;
	}
	
	/**
	 * 
	 * @param isDay 天还是小时
	 * @param labels category系列
	 * @param dbData 从数据库查出来的数据
	 * @param col 进行转换的列
	 * @return 空数据补零之后的数据集合，并且单位转换成MB，传给图控就可以用了
	 */
	public static List<String> labelvalueMB(boolean isDay, List<String> labels,Map<Long, Map<String, Object>> dbData, String col) {
		Map<Long, String> map = new TreeMap<Long, String>();
		for (int i = 0; i < labels.size(); i++) {
			Long key;
			if (isDay) {
				key = LabelUtil.DayLabel2Long(labels.get(i));
			} else {
				key = LabelUtil.HourLabel2Long(labels.get(i));
			}
			map.put(key, "0");
		}
		for (Map.Entry<Long, Map<String, Object>> entry : dbData.entrySet()) {
			String tmpLong = String.valueOf(entry.getKey());
			map.put(Long.parseLong(tmpLong),String.valueOf(Long.parseLong(entry.getValue().get(col).toString())/8.0/1024/1024));
		}
		List<String> values = new ArrayList<String>(labels.size());
		for (Map.Entry<Long, String> en : map.entrySet()) {
			values.add(en.getValue());
		}
		return values;
	}

	/**
	 * 
	 * @param vlanList category系列
	 * @param dbData 从数据库查出来的数据
	 * @return 空数据补零之后的数据集合
	 */
	public static List<Object[]> labelvalue(List<String> vlanList,List<Object[]> dbData) {
		if(dbData ==null){
			return null;
		}
		List<Object[]> values = new ArrayList<Object[]>(vlanList.size());
		for (String vlan : vlanList) {
			Object[] o = new Object[2];
			o[0] = vlan;
			o[1] = null;
			values.add(o);
		}
		for (int i = 0; i < dbData.size(); i++) {
			for (int j = 0; j < values.size(); j++) {
				if (dbData.get(i)[0].equals(values.get(j)[0])) {
					values.get(j)[1] = dbData.get(i)[1];
				}
			}
		}
		return values;
	}
	
	public static double getMax(List<ChartSeries> dataset){
		if(null==dataset||dataset.size()==0){
			throw new IllegalArgumentException("参数异常");
		}
		double max = 0;
		for(int i=0;i<dataset.size();i++){
			for(int j=0;j<dataset.get(i).getValue().size();j++){
				if(Double.parseDouble(dataset.get(i).getValue().get(j))>max){
					max=Double.parseDouble(dataset.get(i).getValue().get(j));
				}
			}
		}
		return max;
	}
	
	public static double getMin(List<ChartSeries> dataset){
		if(null==dataset||dataset.size()==0){
			throw new IllegalArgumentException("参数异常");
		}
		double min = 0;
		for(int i=0;i<dataset.size();i++){
			for(int j=0;j<dataset.get(i).getValue().size();j++){
				if(Double.parseDouble(dataset.get(i).getValue().get(j))<min){
					min=Double.parseDouble(dataset.get(i).getValue().get(j));
				}
			}
		}
		return min;
	}
}
