package cn.amichina.timecomm.network.activeduser;

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

@Service
public class ActivedUserService {

	@Resource
	private ActivedUserDao activedUserDao;
	@Resource
	private AppDao appDao;

	public String toJson(Long startDate,Long endDate,int type) throws SQLException{

		List<String> labels = null;
		List<String> serviceList = null;
		List<List<Object[]>> table = new ArrayList<List<Object[]>>();

		List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();
		//total程序段
		if (type == 0) {
			labels = activedUserDao.userTop10ListByAll(startDate, endDate);
			if (labels.size() == 0) {
				return null;
			}
			serviceList = activedUserDao.serviceListByAll(startDate, endDate);
			serviceList.add("@@@");
			for (int i=0;i<labels.size();i++) {
				List<Object[]> dbData = activedUserDao.getServiceByUserByAll(startDate,endDate, labels.get(i));
				List<Object[]> values = DataSetUtils.labelvalue(serviceList,dbData);
				table.add(values);
				Object[] otherDbData = activedUserDao.getOtherByUserByAll(startDate,endDate,labels.get(i));
				if(otherDbData.length==0){
					table.get(i).get(serviceList.size()-1)[1] = null;
				}else{
					table.get(i).get(serviceList.size()-1)[1] = otherDbData[1];
				}
			}
		}

		//下载程序段
		if (type == 1) {
			labels = activedUserDao.userTop10ListByIn(startDate, endDate);
			if (labels.size() == 0) {
				return null;
			}
			serviceList = activedUserDao.serviceListByIn(startDate, endDate);
			serviceList.add("@@@");
			for (int i=0;i<labels.size();i++) {
				List<Object[]> dbData = activedUserDao.getServiceByUserByIn(startDate,endDate, labels.get(i));
				List<Object[]> values = DataSetUtils.labelvalue(serviceList,dbData);
				table.add(values);
				Object[] otherDbData = activedUserDao.getOtherByUserByIn(startDate,endDate,labels.get(i));
				if(otherDbData.length==0){
					table.get(i).get(serviceList.size()-1)[1] = null;
				}else{
					table.get(i).get(serviceList.size()-1)[1] = otherDbData[1];
				}
			}
		}
		//上传程序段
		if (type == 2) {
			labels = activedUserDao.userTop10ListByOut(startDate, endDate);
			if (labels.size() == 0) {
				return null;
			}
			serviceList = activedUserDao.serviceListByOut(startDate, endDate);
			serviceList.add("@@@");
			for (int i=0;i<labels.size();i++) {
				List<Object[]> dbData = activedUserDao.getServiceByUserByOut(startDate,
						endDate, labels.get(i));
				List<Object[]> values = DataSetUtils.labelvalue(serviceList,dbData);
				table.add(values);
				Object[] otherDbData = activedUserDao.getOtherByUserByOut(startDate,endDate,labels.get(i));
				if(otherDbData.length==0){
					table.get(i).get(serviceList.size()-1)[1] = null;
				}else{
					table.get(i).get(serviceList.size()-1)[1] = otherDbData[1];
				}
			}
		}
		for (int j = 0; j < table.get(0).size(); j++) {
			List<String> chartData = new ArrayList<String>();
			for (int i = 0; i < table.size(); i++) {
				if (table.get(i).get(j)[1] == null) {
					chartData.add(null);
				} else {
					chartData.add(String.valueOf((Long.parseLong(table.get(i).get(j)[1].toString()))/8.0/1024/1024));
				}
			}
			String aName = null;
			String t = (String)table.get(0).get(j)[0];
			if(t.equals("@@@")){
				aName = "Other";
			}else{
				String v = appDao.getNameById(t);
				if(v==null){
					aName = "Undefine";
				}else{
					aName = v;
				}
			}
			ChartSeries series = new ChartSeries(aName, chartData);
			chartSeries.add(series);
		}

		ChartCategory category = new ChartCategory(labels);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		ChartProperties props = new ChartProperties();
		props.setyAxisName("Traffic(MB)");
		props.setCaption("Actived User");
		props.setDecimals("3");
		props.setShowLegend("0");
		Chart chart = new Chart(props,categories, chartSeries);
		return chart.drawColchart();
	}
}
//	@Resource
//	private ActivedUserDao activedUserDao;
//	@Resource
//	private AppDao appDao;
//
//	public String toJson(Long startDate, Long endDate, int type)throws SQLException {
//
//		List<String> labels = null;
//		List<List<Object[]>> table = new ArrayList<List<Object[]>>();
//
//		List<ColChartSeries> chartSeries = new ArrayList<ColChartSeries>();
//		// total程序段
//		if (type == 0) {
//			labels = activedUserDao.userTop10ListByAll(startDate, endDate);
//			if (labels.size() == 0) {
//				return null;
//			}
//			for (int i = 0; i < labels.size(); i++) {
//				List<Object[]> dbData = activedUserDao.getServiceByUserByAll(startDate, endDate, labels.get(i));
//				for (int a = dbData.size(); a < 5; a++) {
//					dbData.add(new Object[] { null, null });
//				}
//				table.add(dbData);
//				Object[] otherDbData = activedUserDao.getOtherByUserByAll(startDate, endDate, labels.get(i));
//				if (otherDbData.length == 0) {
//					Object[] o = { null, null };
//					table.get(i).add(o);
//				} else {
//					Object[] o = { "Other", otherDbData[1] };
//					table.get(i).add(o);
//				}
//			}
//			for (int j = 0; j < 6; j++) {
//				List<String[]> chartData = new ArrayList<String[]>();
//				for (int i = 0; i < table.size(); i++) {
//					if (table.get(i).get(j)[1] == null) {
//						String[] e = { "", "" };
//						chartData.add(e);
//					} else {
//						String v1 = String.valueOf((Long.parseLong(table.get(i).get(j)[1].toString())) / 8.0 / 1024 / 1024);
//						String v2 = String.format("%,.3f",(Long.parseLong(table.get(i).get(j)[1].toString())) / 8.0 / 1024 / 1024);
//						String[] s = {table.get(i).get(j)[0] + ", " + labels.get(i)+ ", " + v2, v1 };
//						chartData.add(s);
//						
//					}
//				}
//				ColChartSeries series = new ColChartSeries(chartData);
//				chartSeries.add(series);
//			}
//		}
//		if (type == 1) {
//			labels = activedUserDao.userTop10ListByIn(startDate, endDate);
//			if (labels.size() == 0) {
//				return null;
//			}
//			for (int i = 0; i < labels.size(); i++) {
//				List<Object[]> dbData = activedUserDao.getServiceByUserByIn(startDate, endDate, labels.get(i));
//				for (int a = dbData.size(); a < 5; a++) {
//					dbData.add(new Object[] { null, null });
//				}
//				table.add(dbData);
//				Object[] otherDbData = activedUserDao.getOtherByUserByIn(startDate, endDate, labels.get(i));
//				if (otherDbData.length == 0) {
//					Object[] o = { null, null };
//					table.get(i).add(o);
//				} else {
//					Object[] o = { "Other", otherDbData[1] };
//					table.get(i).add(o);
//				}
//			}
//			for (int j = 0; j < 6; j++) {
//				List<String[]> chartData = new ArrayList<String[]>();
//				for (int i = 0; i < table.size(); i++) {
//					if (table.get(i).get(j)[1] == null) {
//						String[] e = { "", "" };
//						chartData.add(e);
//					} else {
//						String v1 = String.valueOf((Long.parseLong(table.get(i).get(j)[1].toString())) / 8.0 / 1024 / 1024);
//						String v2 = String.format("%,.3f",(Long.parseLong(table.get(i).get(j)[1].toString())) / 8.0 / 1024 / 1024);
//						String[] s = {table.get(i).get(j)[0] + ", " + labels.get(i)+ ", " + v2, v1 };
//						chartData.add(s);
//					}
//				}
//				ColChartSeries series = new ColChartSeries(chartData);
//				chartSeries.add(series);
//			}
//		}
//		if (type == 2) {
//			labels = activedUserDao.userTop10ListByOut(startDate, endDate);
//			if (labels.size() == 0) {
//				return null;
//			}
//			for (int i = 0; i < labels.size(); i++) {
//				List<Object[]> dbData = activedUserDao.getServiceByUserByOut(startDate, endDate, labels.get(i));
//				for (int a = dbData.size(); a < 5; a++) {
//					dbData.add(new Object[] { null, null });
//				}
//				table.add(dbData);
//				Object[] otherDbData = activedUserDao.getOtherByUserByOut(startDate, endDate, labels.get(i));
//				if (otherDbData.length == 0) {
//					Object[] o = { null, null };
//					table.get(i).add(o);
//				} else {
//					Object[] o = { "Other", otherDbData[1] };
//					table.get(i).add(o);
//				}
//			}
//			for (int j = 0; j < 6; j++) {
//				List<String[]> chartData = new ArrayList<String[]>();
//				for (int i = 0; i < table.size(); i++) {
//					if (table.get(i).get(j)[1] == null) {
//						String[] e = { "", "" };
//						chartData.add(e);
//					} else {
//						String v1 = String.valueOf((Long.parseLong(table.get(i).get(j)[1].toString())) / 8.0 / 1024 / 1024);
//						String v2 = String.format("%,.3f",(Long.parseLong(table.get(i).get(j)[1].toString())) / 8.0 / 1024 / 1024);
//						String[] s = {table.get(i).get(j)[0] + ", " + labels.get(i)+ ", " + v2, v1 };
//						chartData.add(s);
//						
//					}
//				}
//				ColChartSeries series = new ColChartSeries(chartData);
//				chartSeries.add(series);
//			}
//		}
//		ChartCategory category = new ChartCategory(labels);
//		List<ChartCategory> categories = new ArrayList<ChartCategory>();
//		categories.add(category);
//		ColChart chart = new ColChart(categories, chartSeries);
//		ChartProperties props = new ChartProperties();
//		props.setyAxisName("Traffic(MB)");
//		props.setCaption("Actived User");
//		props.setDecimals("3");
//		props.setShowLegend("0");
//		chart.setChart(props);
//		return chart.drawChart();
//	}
//}
