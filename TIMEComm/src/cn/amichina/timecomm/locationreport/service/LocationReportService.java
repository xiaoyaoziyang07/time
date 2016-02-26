package cn.amichina.timecomm.locationreport.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.chart.Chart;
import cn.amichina.common.chart.category.ChartCategory;
import cn.amichina.common.chart.property.ChartProperties;
import cn.amichina.common.chart.series.ChartSeries;
import cn.amichina.timecomm.locationreport.dao.LocationReportDao;
import cn.amichina.timecomm.util.DateUtil;

@Service
public class LocationReportService {
	@Resource
	private LocationReportDao locationReportDao;
	private static final int MAX_DAYS_2_HOUR =7;
	public Chart[] getLocationReport(Date startDate,Date endDate ,int flowType,String vlanId) {
		List<Map<String,Object>> topVlansMap= null;
		if(vlanId==null||vlanId.equals("all")){
			topVlansMap=locationReportDao.getTopLocationVlanList(startDate,endDate,flowType);
		}else{
			topVlansMap= new ArrayList<Map<String,Object>>();
			Map<String,Object> map =new HashMap<String, Object>();
			map.put("vlan",vlanId);
			topVlansMap.add(map);
		}
		Set<Date> dates =new LinkedHashSet<Date>(1);
		for (Date tmpDate=new Date(startDate.getTime()) ;tmpDate.getTime()<=endDate.getTime(); tmpDate=DateUtil.add(tmpDate,Calendar.DATE,1)) {
			dates.add(tmpDate);
		}
		Chart[] charts =null;
		if(dates.size()<=MAX_DAYS_2_HOUR){
			charts=getHourData(startDate,endDate,dates,topVlansMap);
		}else{
			charts=getDayData(startDate,endDate,dates,topVlansMap);
		}
		if(flowType==1){
			charts[0]=null;
		}else if(flowType==2){
			charts[1]=null;
		}
		return charts;
	}
	private Chart[] getDayData(Date startDate, Date endDate, Set<Date> dates,
			List<Map<String, Object>> topVlansMap) {
		List<String> lableList = new ArrayList<String>();
		for (Date date : dates) {
			lableList.add(DateUtil.toStr(date,DateUtil.EN_DATA_FORMAT_MM_DD_YYYY));
		}
		List<ChartSeries> ulChartSeries =  new ArrayList<ChartSeries>();
		List<ChartSeries> dlChartSeries =  new ArrayList<ChartSeries>();
		for (Map<String, Object> vlanMap : topVlansMap) {
			String vlanId=vlanMap.get("vlan").toString();
			List<Map<String,Object>> vlanListMap =locationReportDao.getLocationVlanByDayList(startDate, endDate, vlanId);
			List<String> ulValueList = new ArrayList<String>();
			List<String> dlValueList = new ArrayList<String>();
			String [] values={"0","0"};
			for (Date date : dates) {
				int index = -1;
				for (int x = 0; x < vlanListMap.size(); x++) {
					if (vlanListMap.get(x).get("sj").toString().equals(DateUtil.toStr(date,DateUtil.EN_DATA_FORMAT_YYYYMMDD))) {
						values[0]=String.format("%.2f",Long.valueOf(vlanListMap.get(x).get("upload").toString()).doubleValue()/1024/1024/1024);
						values[1]=String.format("%.2f",Long.valueOf(vlanListMap.get(x).get("download").toString()).doubleValue()/1024/1024/1024);
						index=x;
						break;
					}
				}
				if (index != -1) {
					vlanListMap.remove(index);
				}
					ulValueList.add(values[0]);
					dlValueList.add(values[1]);
			}
			ulChartSeries.add(new ChartSeries(vlanId, ulValueList));
			dlChartSeries.add(new ChartSeries(vlanId, dlValueList));
		}
		ChartCategory category = new ChartCategory(lableList);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		Chart [] charts =new Chart[2];
		charts [0]=new Chart(categories,ulChartSeries);
		charts [1]=new Chart(categories,dlChartSeries);
		return charts;
	}
	
	private Chart[] getHourData(Date startDate, Date endDate, Set<Date> dates,
			List<Map<String, Object>> topVlansMap) {
		List<String> lableList = new ArrayList<String>();
		for (Date date : dates) {
			String lable = DateUtil.toStr(date,DateUtil.EN_DATA_FORMAT_MM_DD);
			for (int i = 0; i < 24; i++) {
				lableList.add(lable+String.format(" %02d:00", i+1));
			}
		}
		List<ChartSeries> ulChartSeries =  new ArrayList<ChartSeries>();
		List<ChartSeries> dlChartSeries =  new ArrayList<ChartSeries>();
		for (Map<String, Object> vlanMap : topVlansMap) {
			String vlanId=vlanMap.get("vlan").toString();
			List<Map<String,Object>> vlanListMap =locationReportDao.getLocationVlanByHourList(startDate, endDate, vlanId);
			List<String> ulValueList = new ArrayList<String>();
			List<String> dlValueList = new ArrayList<String>();
			for (Date date : dates) {
				int index = -1;
				for (int i = 0; i < 24; i++) {
					String [] values={"0","0"};
					for (int x = 0; x < vlanListMap.size(); x++) {
						if (vlanListMap.get(x).get("sj").toString().equals(DateUtil.toDBStr(date)+(String.format("%02d", i) + "0000"))) {
							values[0]=String.format("%.2f",Long.valueOf(vlanListMap.get(x).get("upload").toString()).doubleValue()/1024/1024/1024);
							values[1]=String.format("%.2f",Long.valueOf(vlanListMap.get(x).get("download").toString()).doubleValue()/1024/1024/1024);
							break;
						}
					}
				if (index != -1) {
					vlanListMap.remove(index);
				}
				ulValueList.add(values[0]);
				dlValueList.add(values[1]);
				}
			}
			ulChartSeries.add(new ChartSeries(vlanId, ulValueList));
			dlChartSeries.add(new ChartSeries(vlanId, dlValueList));
		}
		ChartCategory category = new ChartCategory(lableList);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		Chart [] charts =new Chart[2];
		ChartProperties charproperties =new ChartProperties();
		charproperties.setLabelStep(lableList.size() / 12+"");
		
		charts [0]=new Chart(charproperties,categories,ulChartSeries);
		charts [1]=new Chart(charproperties,categories,dlChartSeries);
		return charts;
	}
}
