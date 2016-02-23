package cn.amichina.timecomm.topapp.service;

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
import cn.amichina.timecomm.app.AppDao;
import cn.amichina.timecomm.topapp.dao.AppByVlanDao;
import cn.amichina.timecomm.vlan.dao.VlanDao;

@Service
public class AppByVlanService {

	@Resource
	private VlanDao vlanDao;
	@Resource
	private AppByVlanDao appByVlanDao;
	@Resource
	private AppDao appDao;

	public String toJson(Long startDate, Long endDate, String vlanId, int type)throws SQLException {

		List<String> labels = null;
		List<String> vlanList = null;

		List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();
		//total程序段
		if (type == 0) {
			if (vlanId.equals("all")) {
				labels = appByVlanDao.appTop10ListByAll(startDate, endDate);
				if (labels.size() == 0) {
					return null;
				}
				vlanList = appByVlanDao.vlanListByAll(startDate, endDate);
				vlanList.add("@@@");
				List<List<Object[]>> table = new ArrayList<List<Object[]>>();
				for (int i=0;i<labels.size();i++) {
					List<Object[]> dbData = appByVlanDao.getVlanByAppByAll(startDate, endDate, labels.get(i));
					List<Object[]> values = DataSetUtils.labelvalue(vlanList,dbData);
					table.add(values);
					Object[] otherDbData = appByVlanDao.getOtherByAppByAll(startDate, endDate,labels.get(i));
					if(otherDbData.length==0){
						table.get(i).get(vlanList.size()-1)[1] = null;
					}else{
						table.get(i).get(vlanList.size()-1)[1] = otherDbData[1];
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
					String vName = null;
					String t = (String)table.get(0).get(j)[0];
					if(t.equals("@@@")){
						vName = "Other";
					}else{
						String v = vlanDao.getVlanNameByvId(t);
						if(v==null){
							vName = "Undefine";
						}else{
							vName = v;
						}
					}
					ChartSeries series = new ChartSeries(vName, chartData);
					chartSeries.add(series);
				}
			}else{
				labels = appByVlanDao.appTop10ListByAll(startDate, endDate,vlanId);
				if (labels.size() == 0) {
					return null;
				}
				List<Object[]> dbData = appByVlanDao.getAppByVlanByAll(startDate,endDate,vlanId);
				List<Object[]> chartData = DataSetUtils.labelvalue(labels, dbData);
				List<String> list = new ArrayList<String>();
				for(int i=0;i<chartData.size();i++){
					if(chartData.get(i)[1]==null){
						list.add(null);
					}else{
						list.add(String.valueOf(Long.parseLong(chartData.get(i)[1].toString())/8.0/1024/1024/1024));
					}
				}
				String vlanName = vlanDao.getVlanNameByvId(vlanId);
				ChartSeries series = new ChartSeries(vlanName, list);
				chartSeries.add(series);
			}
		}
		//下载程序段
		if (type == 1) {
			if (vlanId.equals("all")) {
				labels = appByVlanDao.appTop10ListByIn(startDate, endDate);
				if (labels.size() == 0) {
					return null;
				}
				vlanList = appByVlanDao.vlanListByIn(startDate, endDate);
				vlanList.add("Other");
				List<List<Object[]>> table = new ArrayList<List<Object[]>>();
				for (int i=0;i<labels.size();i++) {
					List<Object[]> dbData = appByVlanDao.getVlanByAppByIn(startDate, endDate, labels.get(i));
					List<Object[]> values = DataSetUtils.labelvalue(vlanList,dbData);
					table.add(values);
					Object[] otherDbData = appByVlanDao.getOtherByAppByIn(startDate, endDate,labels.get(i));
					if(otherDbData.length==0){
						table.get(i).get(vlanList.size()-1)[1] = null;
					}else{
						table.get(i).get(vlanList.size()-1)[1] = otherDbData[1];
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
					String vName = null;
					String t = (String)table.get(0).get(j)[0];
					if(t.equals("@@@")){
						vName = "Other";
					}else{
						String v = vlanDao.getVlanNameByvId(t);
						if(v==null){
							vName = "Undefine";
						}else{
							vName = v;
						}
					}
					ChartSeries series = new ChartSeries(vName, chartData);
					chartSeries.add(series);
				}
			}else{
				labels = appByVlanDao.appTop10ListByIn(startDate, endDate,vlanId);
				if (labels.size() == 0) {
					return null;
				}
				List<Object[]> dbData = appByVlanDao.getAppByVlanByIn(startDate,endDate,vlanId);
				List<Object[]> chartData = DataSetUtils.labelvalue(labels, dbData);
				List<String> list = new ArrayList<String>();
				for(int i=0;i<chartData.size();i++){
					if(chartData.get(i)[1]==null){
						list.add(null);
					}else{
						list.add(String.valueOf(Long.parseLong(chartData.get(i)[1].toString())/8.0/1024/1024/1024));
					}
				}
				String vlanName = vlanDao.getVlanNameByvId(vlanId);
				ChartSeries series = new ChartSeries(vlanName, list);
				chartSeries.add(series);
			}
		}
		//上传程序段
		if (type == 2) {
			if (vlanId.equals("all")) {
				labels = appByVlanDao.appTop10ListByOut(startDate, endDate);
				if (labels.size() == 0) {
					return null;
				}
				vlanList = appByVlanDao.vlanListByOut(startDate, endDate);
				vlanList.add("Other");
				List<List<Object[]>> table = new ArrayList<List<Object[]>>();
				for (int i=0;i<labels.size();i++) {
					List<Object[]> dbData = appByVlanDao.getVlanByAppByOut(startDate, endDate, labels.get(i));
					List<Object[]> values = DataSetUtils.labelvalue(vlanList,dbData);
					table.add(values);
					Object[] otherDbData = appByVlanDao.getOtherByAppByOut(startDate, endDate,labels.get(i));
					if(otherDbData.length==0){
						table.get(i).get(vlanList.size()-1)[1] = null;
					}else{
						table.get(i).get(vlanList.size()-1)[1] = otherDbData[1];
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
					String vName = null;
					String t = (String)table.get(0).get(j)[0];
					if(t.equals("@@@")){
						vName = "Other";
					}else{
						String v = vlanDao.getVlanNameByvId(t);
						if(v==null){
							vName = "Undefine";
						}else{
							vName = v;
						}
					}
					ChartSeries series = new ChartSeries(vName, chartData);
					chartSeries.add(series);
				}
			}else{
				labels = appByVlanDao.appTop10ListByIn(startDate, endDate,vlanId);
				if (labels.size() == 0) {
					return null;
				}
				List<Object[]> dbData = appByVlanDao.getAppByVlanByOut(startDate,endDate,vlanId);
				List<Object[]> chartData = DataSetUtils.labelvalue(labels, dbData);
				List<String> list = new ArrayList<String>();
				for(int i=0;i<chartData.size();i++){
					if(chartData.get(i)[1]==null){
						list.add(null);
					}else{
						list.add(String.valueOf(Long.parseLong(chartData.get(i)[1].toString())/8.0/1024/1024/1024));
					}
				}
				String vlanName = vlanDao.getVlanNameByvId(vlanId);
				ChartSeries series = new ChartSeries(vlanName, list);
				chartSeries.add(series);
			}
		}
		List<String> chartLabels = new ArrayList<String>();
		for (String appId : labels) {
			String appName = appDao.getNameById(appId);
			chartLabels.add(appName);
		}
		ChartCategory category = new ChartCategory(chartLabels);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		Chart chart = new Chart(categories, chartSeries);
		ChartProperties props = new ChartProperties();
		props.setyAxisName("Traffic(GB)");
		props.setCaption("Hottest App");
		props.setDecimals("3");
		props.setShowLegend("0");
		chart.setChart(props);
		return chart.drawColchart();
	}
}
