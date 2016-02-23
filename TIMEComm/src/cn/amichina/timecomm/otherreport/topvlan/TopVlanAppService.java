package cn.amichina.timecomm.otherreport.topvlan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.chart.Chart;
import cn.amichina.common.chart.category.ChartCategory;
import cn.amichina.common.chart.property.ChartProperties;
import cn.amichina.common.chart.series.ChartSeries;
import cn.amichina.common.chart.utils.DataSetUtils;
import cn.amichina.timecomm.model.application.ApplicationDao;
import cn.amichina.timecomm.vlan.service.VlanService;

@Service
public class TopVlanAppService {

	@Resource
	private ApplicationDao applicationDao;
	@Resource
	private TopVlanAppDao vlanAppDao;
	@Resource
	private VlanService vlanService;

	public String toJson(Long startDate, Long endDate, String appId, int type)throws SQLException {

		List<String> labels = null;
		List<String> appList = null;

		List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();
		//total程序段
		if (type == 0) {
			if (appId.equals("all")) {
				labels = vlanAppDao.vlanTop10ListByAll(startDate, endDate);
				if (labels.size() == 0) {
					return null;
				}
				appList = vlanAppDao.serviceListByAll(startDate, endDate);
				appList.add("@@@");
				List<List<Object[]>> table = new ArrayList<List<Object[]>>();
				for (int i=0;i<labels.size();i++) {
					List<Object[]> dbData = vlanAppDao.getServiceByVlanByAll(startDate, endDate, labels.get(i));
					List<Object[]> values = DataSetUtils.labelvalue(appList,dbData);
					table.add(values);
					Object[] otherDbData = vlanAppDao.getOtherByVlanByAll(startDate, endDate,labels.get(i));
					if(otherDbData.length==0){
						table.get(i).get(appList.size()-1)[1] = null;
					}else{
						table.get(i).get(appList.size()-1)[1] = otherDbData[1];
					}
				}
				for (int j = 0; j < table.get(0).size(); j++) {
					List<String> chartData = new ArrayList<String>();
					for (int i = 0; i < table.size(); i++) {
						if (table.get(i).get(j)[1] == null) {
							chartData.add(null);
						} else {
							chartData.add(String.valueOf(((Long.parseLong(table.get(i).get(j)[1].toString()))/8.0/1024/1024/1024)));
						}
					}
					String aName = null;
					String t = (String)table.get(0).get(j)[0];
					if(t.equals("@@@")){
						aName = "Other";
					}else{
						String v = applicationDao.getNameById(t);
						if(v==null){
							aName = "Undefine";
						}else{
							aName = v;
						}
					}
					ChartSeries series = new ChartSeries(aName, chartData);
					chartSeries.add(series);
				}
			}else{
				labels = vlanAppDao.vlanTop10ListByAll(startDate, endDate,appId);
				if (labels.size() == 0) {
					return null;
				}
				List<Object[]> dbData = vlanAppDao.getVlanByAppByAll(startDate,endDate,appId);
				List<Object[]> chartData = DataSetUtils.labelvalue(labels, dbData);
				List<String> list = new ArrayList<String>();
				for(int i=0;i<chartData.size();i++){
					if(chartData.get(i)[1]==null){
						list.add(null);
					}else{
						list.add(String.valueOf(Long.parseLong(chartData.get(i)[1].toString())/8.0/1024/1024/1024));
					}
				}
				String appName = applicationDao.getNameById(appId);
				ChartSeries series = new ChartSeries(appName, list);
				chartSeries.add(series);
			}
		}
		//下载程序段
		if (type == 1) {
			if (appId.equals("all")) {
				labels = vlanAppDao.vlanTop10ListByIn(startDate, endDate);
				if (labels.size() == 0) {
					return null;
				}
				appList = vlanAppDao.serviceListByIn(startDate, endDate);
				appList.add("Other");
				List<List<Object[]>> table = new ArrayList<List<Object[]>>();
				for (int i=0;i<labels.size();i++) {
					List<Object[]> dbData = vlanAppDao.getAppByVlanByIn(startDate, endDate, labels.get(i));
					List<Object[]> values = DataSetUtils.labelvalue(appList,dbData);
					table.add(values);
					Object[] otherDbData = vlanAppDao.getOtherByVlanByIn(startDate, endDate,labels.get(i));
					if(otherDbData.length==0){
						table.get(i).get(appList.size()-1)[1] = null;
					}else{
						table.get(i).get(appList.size()-1)[1] = otherDbData[1];
					}
				}
				for (int j = 0; j < table.get(0).size(); j++) {
					List<String> chartData = new ArrayList<String>();
					for (int i = 0; i < table.size(); i++) {
						if (table.get(i).get(j)[1] == null) {
							chartData.add(null);
						} else {
							chartData.add(String.valueOf((Long.parseLong(table.get(i).get(j)[1].toString()))/8.0/1024/1024/1024));
						}
					}
					String aName = null;
					String t = (String)table.get(0).get(j)[0];
					if(t.equals("@@@")){
						aName = "Other";
					}else{
						String v = applicationDao.getNameById(t);
						if(v==null){
							aName = "Undefine";
						}else{
							aName = v;
						}
					}
					ChartSeries series = new ChartSeries(aName, chartData);
					chartSeries.add(series);
				}
			}else{
				labels = vlanAppDao.vlanTop10ListByIn(startDate, endDate,appId);
				if (labels.size() == 0) {
					return null;
				}
				List<Object[]> dbData = vlanAppDao.getVlanByAppByIn(startDate,endDate,appId);
				List<Object[]> chartData = DataSetUtils.labelvalue(labels, dbData);
				List<String> list = new ArrayList<String>();
				for(int i=0;i<chartData.size();i++){
					if(chartData.get(i)[1]==null){
						list.add(null);
					}else{
						list.add(String.valueOf(Long.parseLong(chartData.get(i)[1].toString())/8.0/1024/1024/1024));
					}
				}
				String appName = applicationDao.getNameById(appId);
				ChartSeries series = new ChartSeries(appName, list);
				chartSeries.add(series);
			}
		}
		//上传程序段
		if (type == 2) {
			if (appId.equals("all")) {
				labels = vlanAppDao.vlanTop10ListByOut(startDate, endDate);
				if (labels.size() == 0) {
					return null;
				}
				appList = vlanAppDao.serviceListByOut(startDate, endDate);
				appList.add("Other");
				List<List<Object[]>> table = new ArrayList<List<Object[]>>();
				for (int i=0;i<labels.size();i++) {
					List<Object[]> dbData = vlanAppDao.getAppByVlanByOut(startDate, endDate, labels.get(i));
					List<Object[]> values = DataSetUtils.labelvalue(appList,dbData);
					table.add(values);
					Object[] otherDbData = vlanAppDao.getOtherByVlanByOut(startDate, endDate,labels.get(i));
					if(otherDbData.length==0){
						table.get(i).get(appList.size()-1)[1] = null;
					}else{
						table.get(i).get(appList.size()-1)[1] = otherDbData[1];
					}
				}
				for (int j = 0; j < table.get(0).size(); j++) {
					List<String> chartData = new ArrayList<String>();
					for (int i = 0; i < table.size(); i++) {
						if (table.get(i).get(j)[1] == null) {
							chartData.add(null);
						} else {
							chartData.add(String.valueOf((Long.parseLong(table.get(i).get(j)[1].toString()))/8.0/1024/1024/1024));
						}
					}
					String aName = null;
					String t = (String)table.get(0).get(j)[0];
					if(t.equals("@@@")){
						aName = "Other";
					}else{
						String v = applicationDao.getNameById(t);
						if(v==null){
							aName = "Undefine";
						}else{
							aName = v;
						}
					}
					ChartSeries series = new ChartSeries(aName, chartData);
					chartSeries.add(series);
				}
			}else{
				labels = vlanAppDao.vlanTop10ListByOut(startDate, endDate,appId);
				if (labels.size() == 0) {
					return null;
				}
				List<Object[]> dbData = vlanAppDao.getVlanByAppByOut(startDate,endDate,appId);
				List<Object[]> chartData = DataSetUtils.labelvalue(labels, dbData);
				List<String> list = new ArrayList<String>();
				for(int i=0;i<chartData.size();i++){
					if(chartData.get(i)[1]==null){
						list.add(null);
					}else{
						list.add(String.valueOf(Long.parseLong(chartData.get(i)[1].toString())/8.0/1024/1024/1024));
					}
				}
				String appName = applicationDao.getNameById(appId);
				ChartSeries series = new ChartSeries(appName, list);
				chartSeries.add(series);
			}
		}
		List<String> chartLabels = new ArrayList<String>();
		for (String vlanId : labels) {
			String vlanName = vlanService.getNameById(vlanId);
			chartLabels.add(vlanName);
		}
		ChartCategory category = new ChartCategory(chartLabels);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		Chart chart = new Chart(categories, chartSeries);
		ChartProperties props = new ChartProperties();
		props.setyAxisName("Traffic(GB)");
		props.setCaption("Top Vlans");
		props.setDecimals("3");
		props.setShowLegend("0");
		chart.setChart(props);
		return chart.drawColchart();
	}
}
