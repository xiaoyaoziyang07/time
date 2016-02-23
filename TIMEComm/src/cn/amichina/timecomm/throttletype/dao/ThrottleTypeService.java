package cn.amichina.timecomm.throttletype.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.chart.Chart;
import cn.amichina.common.chart.category.ChartCategory;
import cn.amichina.common.chart.property.ChartProperties;
import cn.amichina.common.chart.series.ChartSeries;
import cn.amichina.timecomm.util.ChartsLineUtils;
/**
 * 
 * Create by 石磊  on 2015年11月12日 下午4:08:40
 *
 * throttletype 业务组件
 */
@Service
public class ThrottleTypeService {
	@Resource
	private ThrottleTypeDao throttleTypeDao;
	private static final String DEFAULT_OTHER_CUSTOMERPLAN_NAME="Other";
	private static List<String> throttlenameList ;
	
	public Chart getTopThrottleTypeListOfPlans(Date startDate, Date endDate, String throttleplanName) {
		List<Map<String,Object>> topThrottleType =null;
		if(throttleplanName ==null||throttleplanName.equals("all")){
			topThrottleType=throttleTypeDao.getTopThrottleTypeList(startDate, endDate);
		}else{
			topThrottleType=throttleTypeDao.getTopThrottleTypeListByAboutPlan(startDate, endDate, throttleplanName);
		}
		List<String> lableList = new ArrayList<String>();
		List<LinkedHashMap<String, String>> cols =new ArrayList<LinkedHashMap<String,String>>();
		for (Map<String,Object> map : topThrottleType) {
			String splanid =map.get("splanid").toString();
			lableList.add(splanid);
			List<Map<String,Object>> topThrottleTypePlanList = null;
			if(throttleplanName==null||throttleplanName.equals("all")){
				topThrottleTypePlanList=throttleTypeDao.getTopThrottleTypePlanList(startDate, endDate, splanid);
			}else{
				topThrottleTypePlanList =throttleTypeDao.getTopThrottleTypePlanListByAboutPlan(startDate, endDate, splanid,throttleplanName, splanid );
			}
			Long topThrottleTypeUsageQuantity =Long.parseLong(map.get("nums").toString());
			LinkedHashMap<String, String> col =new LinkedHashMap<String, String>();
			for (Map<String, Object> topThrottleTypePlanMap : topThrottleTypePlanList) {
				Long throttleTypeUsageQuantity =Long.parseLong(topThrottleTypePlanMap.get("nums").toString());
				col.put(topThrottleTypePlanMap.get("aboutplan").toString(),String.valueOf(throttleTypeUsageQuantity));
				topThrottleTypeUsageQuantity -=throttleTypeUsageQuantity;
			}
			col.put(DEFAULT_OTHER_CUSTOMERPLAN_NAME,String.valueOf(topThrottleTypeUsageQuantity));
			cols.add(col);
		}
		Map<String,List<String>> rows =ChartsLineUtils.generateLines(cols, DEFAULT_OTHER_CUSTOMERPLAN_NAME);
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		for (Entry<String, List<String>> entry : rows.entrySet()) {
			List<String> tmpValList =new ArrayList<String>(entry.getValue().size());
			for (int i = 0; i < entry.getValue().size(); i++) {
				tmpValList.add(entry.getValue().get(i));
			}
			if(entry.getKey()!=null&&(!entry.getKey().equals(DEFAULT_OTHER_CUSTOMERPLAN_NAME)))
			chartSeriesList.add(new ChartSeries(entry.getKey(), tmpValList));
		}
		chartSeriesList.add(new ChartSeries(DEFAULT_OTHER_CUSTOMERPLAN_NAME,rows.get(DEFAULT_OTHER_CUSTOMERPLAN_NAME)));
		ChartCategory category = new ChartCategory(lableList);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		Chart chart = new Chart(categories, chartSeriesList);
		ChartProperties props = new ChartProperties();
		props.setyAxisName("Traffic(GB)");
		props.setCaption("Throttle Type");
		props.setDecimals("3");
		props.setShowLegend("0");
		chart.setChart(props);
		return chart;
	}
	public  List<String>  getThrottlenameList(){
		synchronized(this){
			if(throttlenameList==null){
				throttlenameList=throttleTypeDao.getThrottlenameList();
			}
		}
		return throttlenameList;
		
	}
}
