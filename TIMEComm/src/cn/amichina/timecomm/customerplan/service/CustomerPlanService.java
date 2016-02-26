package cn.amichina.timecomm.customerplan.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.chart.Chart;
import cn.amichina.common.chart.category.ChartCategory;
import cn.amichina.common.chart.property.ChartProperties;
import cn.amichina.common.chart.series.ChartSeries;
import cn.amichina.timecomm.customerplan.dao.CustomerPlanDao;
import cn.amichina.timecomm.report.trendingreport.model.StackedAreaCategoris;
import cn.amichina.timecomm.report.trendingreport.model.StackedAreaDateSet;
import cn.amichina.timecomm.report.trendingreport.model.StackedAreaSeriesname;
import cn.amichina.timecomm.util.ChartsLineUtils;
import cn.amichina.timecomm.util.DateUtil;

@Service
public class CustomerPlanService {
private static final int MAX_DAYS_2_HOUR =7;
private static final String DEFAULT_OTHER_CUSTOMERPLAN_NAME="Other";
@Resource
private CustomerPlanDao customerPlanDao;
	public StackedAreaDateSet getCustomerPlanUsageQuantityStackedAreaDateSet(Date startDate,Date endDate,int by,String vlanId) {
		List<Map<String, Object>>  top5 =null;
		if(by==0){
			top5=customerPlanDao.getTop5UsageQuantityByTotal(startDate, endDate,vlanId);
		}else if(by==1){
			top5=customerPlanDao.getTop5UsageQuantityByIn(startDate, endDate,vlanId);
		}else if(by==2){
			top5=customerPlanDao.getTop5UsageQuantityByOut(startDate,endDate,vlanId);
		}
		Set<Date> dates =new LinkedHashSet<Date>(1);
		for (Date tmpDate=new Date(startDate.getTime()) ;tmpDate.getTime()<=endDate.getTime(); tmpDate=DateUtil.add(tmpDate,Calendar.DATE,1)) {
			dates.add(tmpDate);
		}
		StackedAreaDateSet dataSet = null;
		if(dates.size()<=MAX_DAYS_2_HOUR){
				dataSet =getHourData(startDate,endDate,dates,top5,by,vlanId);
		}else{
				dataSet =getDayData(startDate,endDate,dates,top5,by,vlanId);
		}
		return dataSet;
	}
	private StackedAreaDateSet getDayData(Date startDate, Date endDate,
			Set<Date> dates, List<Map<String, Object>> top5,int by,String vlanId) {
		StackedAreaDateSet dateSet =new StackedAreaDateSet();
		StackedAreaCategoris  categoris=new StackedAreaCategoris();
		for (Date date : dates) {
			categoris.addLabel(DateUtil.toStr(date,DateUtil.EN_DATA_FORMAT_MM_DD_YYYY));
			}
		dateSet.setCategoris(categoris);
		List<String>  top5CustomerPlanList=null;
		if(top5.size()>=MAX_DAYS_2_HOUR){
			top5CustomerPlanList=new ArrayList<String>(MAX_DAYS_2_HOUR);
		}
		for (Map<String,Object> map : top5) {
			StackedAreaSeriesname seriesname=new StackedAreaSeriesname();
			String customerPlanName =map.get("servicename").toString();
			if(top5CustomerPlanList!=null){
				top5CustomerPlanList.add(customerPlanName);
			}
			seriesname.setSeriesname(customerPlanName);
			//获取数据
			for (Date date : dates) {
				String val =null;
				Map<String,Object> resultMap =customerPlanDao.getCustomerPlanDayUsageQuantity(date, customerPlanName, by,vlanId);
				if(resultMap!=null&&resultMap.get("total")!=null){
					val = resultMap.get("total").toString();
				}
				if(val!=null)
				seriesname.addValue(String.format("%.2f",Double.parseDouble(val)/1024/1024/1024));
				else{
					seriesname.addValue(null);
				}
			}
			dateSet.addSeriesname(seriesname);
		}
		if(top5CustomerPlanList!=null){
			StackedAreaSeriesname	seriesname =new StackedAreaSeriesname();
			seriesname.setSeriesname(DEFAULT_OTHER_CUSTOMERPLAN_NAME);
			for (Date date : dates) {
				String val =null;
				Map<String,Object> resultMap =customerPlanDao.getCustomerPlanUsageQuantityDayFilterCustomerPlanIds(date, by,vlanId,top5CustomerPlanList.toArray(new String[top5CustomerPlanList.size()]));
				if(resultMap!=null&&resultMap.get("total")!=null){
					val = resultMap.get("total").toString();
				}
				if(val!=null)
					seriesname.addValue(String.format("%.2f",Double.parseDouble(val)/1024/1024/1024));
				else
					seriesname.addValue(null);
					
			}
			dateSet.addSeriesname(seriesname);
		}
		return dateSet;
	}
	private StackedAreaDateSet getHourData(Date startDate, Date endDate,
			Set<Date> dates, List<Map<String, Object>> top5,int by,String vlanId) {
		StackedAreaCategoris  categoris=new StackedAreaCategoris();
		for (Date date : dates) {
			String lable = DateUtil.toStr(date,DateUtil.EN_DATA_FORMAT_MM_DD);
			for (int i = 1; i <=24; i++) {
				categoris.addLabel(lable+String.format(" %02d:00", i));
			}
		}
		StackedAreaDateSet dateSet =new StackedAreaDateSet();
		dateSet.setCategoris(categoris);
		List<String>  top5CustomerPlanList=null;
		if(top5.size()>=MAX_DAYS_2_HOUR){
			top5CustomerPlanList=new ArrayList<String>(MAX_DAYS_2_HOUR);
		}
		for (Map<String,Object> map : top5) {
			StackedAreaSeriesname seriesname=new StackedAreaSeriesname();
			String customerPlanName =map.get("servicename").toString();
			if(top5CustomerPlanList!=null){
				top5CustomerPlanList.add(customerPlanName);
			}
			seriesname.setSeriesname(customerPlanName);
			//获取数据
			for (Date date : dates) {
				List<Map<String,Object>> list =customerPlanDao.getCustomerPlanHourUsageQuantity(date, customerPlanName, by, vlanId);
				for (int i = 0; i < 24; i++) {
					String val ="0.00";
					int index=-1;
					for (int x = 0; x < list.size(); x++) {
						if(list.get(x).get("sj").toString().equals(DateUtil.toDBStr(date)+(String.format("%02d", i)+"0000"))){
							index=x;
						}
					}
					if(index!=-1){
						val=String.format("%.2f",Long.valueOf(list.get(index).get("total").toString()).doubleValue()/1024/1024/1024);
					}
						seriesname.addValue(val);
				}
			}
			dateSet.addSeriesname(seriesname);
		}
		if(top5CustomerPlanList!=null){
			StackedAreaSeriesname	seriesname =new StackedAreaSeriesname();
			seriesname.setSeriesname(DEFAULT_OTHER_CUSTOMERPLAN_NAME);
			for (Date date : dates) {
				List<Map<String,Object>> list =customerPlanDao.getCustomerPlanUsageQuantityHourFilterCustomerPlanIds(date, by,vlanId,top5CustomerPlanList.toArray(new String[top5CustomerPlanList.size()]));
				for (int i = 0; i < 24; i++) {
					String val ="0.00";
					int index=-1;
					for (int x = 0; x < list.size(); x++) {
						if(list.get(x).get("sj").toString().equals(DateUtil.toDBStr(date)+(String.format("%02d", i)+"0000"))){
							index=x;
						}
					}
					if(index!=-1){
						val=String.format("%.2f",Long.valueOf(list.get(index).get("total").toString()).doubleValue()/1024/1024/1024);
					}
						seriesname.addValue(val);
				}
			}
			dateSet.addSeriesname(seriesname);
			
		}
		return dateSet;
	}
	public List<Object> getCustomerPlanHourFiles(){
		 List<Object>  list =customerPlanDao.getCustomerPlanHourFiles();
		 for (int i = 0; i < list.size(); i++) {
			 String data = list.get(i).toString();
			 list.set(i, DateUtil.toStr(DateUtil.parseDate(data,DateUtil.EN_DATA_FORMAT_YYYYMMDD), DateUtil.EN_DATA_FORMAT_MM_DD_YYYY));
		}
		return list;
	}
	public String getCustomerPlanHourPathByDate(String date){
		return customerPlanDao.getCustomerPlanHourPathByDate(date);
	}
	
	public Chart getTopCustomerPlanByStartDateAndEndDateAndFlowType(Date startDate,Date endDate,int flowType){
		//获取套餐排名
		List<Map<String,Object>> topCustomerPlanList= customerPlanDao.getTopCustomerPlan(startDate, endDate, flowType);
		
		List<String> lableList = new ArrayList<String>();
		List<LinkedHashMap<String, String>> cols =new ArrayList<LinkedHashMap<String,String>>();
		for (Map<String, Object> map : topCustomerPlanList) {
			lableList.add(map.get("servicename").toString());
			List<Map<String,Object>> topServiceList = customerPlanDao.getTopService(startDate, endDate,map.get("servicename").toString(), flowType);
			Long topServiceUsageQuantity =Long.parseLong(map.get("traffic").toString());
			LinkedHashMap<String, String> col =new LinkedHashMap<String, String>();
			for (Map<String, Object> serviceMap : topServiceList) {
				Long serviceUsageQuantity =Long.parseLong(serviceMap.get("traffic").toString());
				col.put(serviceMap.get("servicename").toString(),String.format("%.2f",(serviceUsageQuantity.doubleValue()/1024/1024/1024)));
				topServiceUsageQuantity -=serviceUsageQuantity;
			}
			col.put(DEFAULT_OTHER_CUSTOMERPLAN_NAME, String.format("%.2f",(topServiceUsageQuantity.doubleValue()/1024/1024/1024)));
			cols.add(col);
		}
		
		Map<String,List<String>> rows =ChartsLineUtils.generateLines(cols, "0");
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
		ChartProperties props = new ChartProperties();
		props.setyAxisName("Traffic(GB)");
		props.setCaption("Hottest Package");
		props.setDecimals("3");
		Chart chart = new Chart(props,categories, chartSeriesList);
		return chart;
	}
}
