package cn.amichina.timecomm.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * Create by 石磊  on 2015年11月9日 下午5:31:50
 *
 *
 */
public class ChartsLineUtils {

	public static  Map<String, List<String>> generateLines(List<LinkedHashMap<String,String>> cols,final String DefaultNullVal){
		List<List<String>> lines=new ArrayList<List<String>>();
		//所有列元素
		List<String> colAll =new ArrayList<String>();
		//遍历每列
		for (Map<String, String> colMap : cols) {
			//生成该列的元素
			List<String> colList = new ArrayList<String>();
			String val = null;
			//生成参照列格式的数据
			for (String key : colAll) {
				val = colMap.get(key);
				if (val != null) {
					colList.add(val);
					colMap.remove(key);
				}else{
					colList.add(DefaultNullVal);
				}
			}
			//生成新增列数据
			for (Map.Entry<String, String> entry : colMap.entrySet()) {
				colAll.add(entry.getKey());
				colList.add(entry.getValue());
			}
			lines.add(colList);
		}
		Map<String,List<String>> rowMap =new LinkedHashMap<String, List<String>>();
		System.out.println(colAll);
		for (int x = 0; x < colAll.size(); x++) {
			List<String> row =new ArrayList<String>();
			for (int i = 0; i < lines.size(); i++) {
				List<String> colList =lines.get(i);
				String tmpDefaultNullVal= DefaultNullVal;
				if(x<colList.size()){
					tmpDefaultNullVal=colList.get(x);
				}
				row.add(tmpDefaultNullVal);
			}
			rowMap.put(colAll.get(x), row);
		}
		for (Entry<String, List<String>> entry : rowMap.entrySet()) {
			System.out.print(entry.getKey());
			System.out.println(entry.getValue());
		}
		return rowMap;
	}

	public static void main(String[] args) {
		List<LinkedHashMap<String, String>> cols = new ArrayList<LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("a1", "11");
		map.put("a2", "22");
		map.put("a3", "33");
		map.put("a4", "44");
		map.put("Ohter", "44");
		cols.add(map);
		map = new LinkedHashMap<String, String>();
		map.put("a1", "11");
		map.put("a3", "33");
		map.put("a2", "22");
		map.put("a5", "55");
		map.put("a4", "44");
		cols.add(map);
		map = new LinkedHashMap<String, String>();
		map.put("a1", "11");
		map.put("a3", "33");
		map.put("a4", "44");
		map.put("a2", "22");
		map.put("a6", "22");
		cols.add(map);

		map = new LinkedHashMap<String, String>();
		map.put("a6", "11");
		map.put("a7", "33");
		map.put("a2", "22");
		map.put("a5", "55");
		map.put("a4", "44");
		cols.add(map);
		
		Map<String,List<String>> rows =generateLines(cols, "0");
		//test(cols);
		for (Entry<String, List<String>> entry : rows.entrySet()) {
			System.out.print(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
}
