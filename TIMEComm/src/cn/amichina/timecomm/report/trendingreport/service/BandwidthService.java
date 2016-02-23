package cn.amichina.timecomm.report.trendingreport.service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.report.trendingreport.dao.BandwidthDao;
import cn.amichina.timecomm.report.trendingreport.model.StackedAreaCategoris;
import cn.amichina.timecomm.report.trendingreport.model.StackedAreaDateSet;
import cn.amichina.timecomm.report.trendingreport.model.StackedAreaSeriesname;
import cn.amichina.timecomm.util.DateUtil;
@Service
public class BandwidthService {
	private static final String DEFAULT_LABLEVALUE="0.00";
	private static final int MAX_DAYS_2_HOUR =7;
	private static final String DEFAULT_OTHER_CUSTOMERPLAN_NAME="Other";
	@Resource
	private BandwidthDao bandwidthDao;	
	/**
	 * 获取所有流量统计堆积图数据集
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StackedAreaDateSet  getBandwidthStackedAreaDateSet(final Date startDate,final Date endDate,final Integer type) {
		Set<Date> dates =new LinkedHashSet<Date>(0);
		for (Date tmpDate=new Date(startDate.getTime()) ;tmpDate.getTime()<=endDate.getTime(); tmpDate=DateUtil.add(tmpDate,Calendar.DATE,1)) {
			dates.add(tmpDate);
		}
		StackedAreaDateSet dataSet = null;
		if(dates.size()<=MAX_DAYS_2_HOUR){
			dataSet=hourData(dates,bandwidthDao.getTop5ServiceByStartDateAndEndDateHourOrder(startDate, endDate,type),startDate,endDate,type);
		}else{
			dataSet = dayData(startDate, endDate, bandwidthDao.getTop5ServiceByStartDateAndEndDate(startDate, endDate,type), dates,type);
		}
		return dataSet;
	}
	/**
	 * 天数据
	 * @param startDate
	 * @param endDate
	 * @param servicetop5
	 * @param dates
	 */
	public StackedAreaDateSet dayData(final Date startDate, final Date endDate,
			List<Map<String,Object>> servicetop5, Set<Date> dates,Integer type) {
		StackedAreaCategoris  categoris=new StackedAreaCategoris();
			for (Date date : dates) {
				categoris.addLabel(DateUtil.toStr(date,DateUtil.EN_DATA_FORMAT_MM_DD_YYYY));
		}
		StackedAreaDateSet dateSet =new StackedAreaDateSet();
		dateSet.setCategoris(categoris);
		for (Map<String,Object> map : servicetop5) {
			List<Map<String, Object>> list =bandwidthDao.getBandwidthAvg(startDate, endDate, map.get("serrvice").toString(),type);
			StackedAreaSeriesname seriesname=new StackedAreaSeriesname();
			seriesname.setSeriesname(map.get("name").toString());
			for(String lable : categoris.getLabels()){
				seriesname.addValue(findLableValue(lable,list));
			}
			dateSet.addSeriesname(seriesname);
		}
		StackedAreaSeriesname seriesname = new StackedAreaSeriesname();
		String [] servs =new String[servicetop5.size()];
		for (int i = 0; i < servs.length; i++) {
			servs[i] = servicetop5.get(i).get("serrvice").toString();
		}
		List<Map<String,Object >> totalList =bandwidthDao.getTotalBandwidthAvgFilterTop5(startDate, endDate, servs,type);
		seriesname.setSeriesname(DEFAULT_OTHER_CUSTOMERPLAN_NAME);
		for(String lable : categoris.getLabels()){
			seriesname.addValue(findLableValue(lable,totalList));
		}
		dateSet.addSeriesname(seriesname);
		return dateSet;
	}
	private String findLableValue(String lables ,List<Map<String, Object>> list){
		String result=null;
		int index =-1;
		for (int i = 0; i < list.size(); i++) {
			if(DateUtil.eqDate(DateUtil.parseDate(lables),DateUtil.parseDate(list.get(i).get("SJ").toString(),DateUtil.EN_DATA_FORMAT_YYYYMMDD))){
				index=i;
				break;
			}
		}
		if(index==-1){
			result= DEFAULT_LABLEVALUE;
		}else{
			result = String.format("%.2f", list.get(index).get("Mbps"));
			list.remove(index);
		}
		return result;
	}
	/**
	 * 小时
	 * @param lable
	 * @param list
	 * @return
	 */
	private String findLableValueHour(String lable ,List<Map<String, Object>> list){
		String result=null;
		int index =-1;
		for (int i = 0; i < list.size(); i++) {
			if(DateUtil.eqDateAndHour(DateUtil.parseDate(lable,DateUtil.EN_DATA_FORMAT_MM_DD_HH_MM),DateUtil.parseDate(list.get(i).get("SJ").toString().substring(0,10),DateUtil.EN_DATA_FORMAT_YYYYMMDDHH))){
				index=i;
				break;
			}
		}
		if(index==-1){
			result= DEFAULT_LABLEVALUE;
		}else{
				result = String.format("%.2f", list.get(index).get("Mbps"));
				list.remove(index);
		}
		return result;
	}
	/**
	 * 小时
	 * @param lable
	 * @param list
	 * @return
	 */
	private String findLableValueHourTemp(String lable ,List<Map<String, Object>> list){
		String result=null;
		int index =-1;
		for (int i = 0; i < list.size(); i++) {
			if(DateUtil.eqDateAndHour(DateUtil.parseDate(lable,DateUtil.EN_DATA_FORMAT_MM_DD_HH_MM),DateUtil.parseDate(list.get(i).get("SJ").toString().substring(0,10),DateUtil.EN_DATA_FORMAT_YYYYMMDDHH))){
				index=i;
				break;
			}
		}
		if(index==-1){
			result= DEFAULT_LABLEVALUE;
		}else{
			result = String.format("%.2f", list.get(index).get("Mbps"));
			list.remove(index);
		}
		return result;
	}
	/**
	 * 小时数据
	 * @param dates
	 * @param servicetop5
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StackedAreaDateSet hourData(Set<Date> dates,List<Map<String,Object>> servicetop5, Date startDate, Date endDate,Integer type) {
		StackedAreaCategoris  categoris=new StackedAreaCategoris();
		for (Date date : dates) {
			String lable = DateUtil.toStr(date,DateUtil.EN_DATA_FORMAT_MM_DD);
			for (int i = 0; i < 24; i++) {
				categoris.addLabel(lable+String.format(" %02d:00", i+1));
			}
		}
		StackedAreaDateSet dateSet =new StackedAreaDateSet();
		dateSet.setCategoris(categoris);
		for (Map<String,Object> map : servicetop5) {
			List<Map<String, Object>> list =bandwidthDao.getBandwidthAvgHour(startDate, endDate, map.get("service").toString(),type);
			StackedAreaSeriesname seriesname=new StackedAreaSeriesname();
			seriesname.setSeriesname(map.get("name").toString());
			for(String lable : categoris.getLabels()){
				seriesname.addValue(findLableValueHour(lable,list));
			}
			dateSet.addSeriesname(seriesname);
		}
			StackedAreaSeriesname seriesname = new StackedAreaSeriesname();
			String [] servs =new String[servicetop5.size()];
			for (int i = 0; i < servs.length; i++) {
				servs[i] = servicetop5.get(i).get("service").toString();
			}
			List<Map<String,Object >> totalList =bandwidthDao.getTotalBandwidthAvgHourFilterTop5(startDate, endDate, servs,type);
			if(servicetop5.size()>=5){
			seriesname.setSeriesname(DEFAULT_OTHER_CUSTOMERPLAN_NAME);
			for(String lable : categoris.getLabels()){
				seriesname.addValue(findLableValueHourTemp(lable,totalList));
			}
			dateSet.addSeriesname(seriesname);
			}
		return dateSet;
	}
}
