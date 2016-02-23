package cn.amichina.timecomm.topvlan.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.chart.Chart;
import cn.amichina.common.chart.category.ChartCategory;
import cn.amichina.common.chart.property.ChartProperties;
import cn.amichina.common.chart.series.ChartSeries;
import cn.amichina.common.chart.utils.DataSetUtils;
import cn.amichina.timecomm.topvlan.dao.UserByVlanDao;
import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.LabelUtil;
import cn.amichina.timecomm.vlan.dao.VlanDao;

@Service
public class UserByVlanService {

	@Resource
	private VlanDao vlanDao;
	@Resource
	private UserByVlanDao userByVlanDao;

	public String toJson(Date startDate, Date endDate, String vlanId)throws SQLException {
		int betDays = DateUtil.daysBetween(startDate, endDate);
		// 大于7天程序段
		if (betDays > 7) {
			List<String> labels = LabelUtil.labelsByDay(startDate, endDate);
			ChartCategory category = new ChartCategory(labels);
			List<ChartCategory> categories = new ArrayList<ChartCategory>(1);
			categories.add(category);
			
			List<ChartSeries> chartSeries = new ArrayList<ChartSeries>(5);
			if (vlanId.equals("all")) {
				List<String> vlans = userByVlanDao.vlanTop5listByDay(startDate,endDate);
				for (String s : vlans) {
					Map<Long, Map<String, Object>> dbData = userByVlanDao.getUserCountByDay(startDate,endDate,s);
					List<String> date = DataSetUtils.labelvalue(true,labels,dbData,"stat3");
					String vlanName = vlanDao.getVlanNameByvId(s);
					if(vlanName==null){
						vlanName=s;
					}
					ChartSeries series = new ChartSeries(vlanName, date);
					chartSeries.add(series);
				}
			} else {
				Map<Long, Map<String, Object>> dbData = userByVlanDao.getUserCountByDay(startDate,endDate, vlanId);
				List<String> date = DataSetUtils.labelvalue(true,labels, dbData, "stat3");
				String vlanName = vlanDao.getVlanNameByvId(vlanId);
				ChartSeries series = new ChartSeries(vlanName, date);
				chartSeries.add(series);
			}
			Chart chart = new Chart(categories, chartSeries);
			ChartProperties props = new ChartProperties();
			if(chartSeries.size()!=0){
				String[] dx = new String[2];
				dx[0]=chartSeries.get(0).getMaxMinValue()[0];
				dx[1]=chartSeries.get(0).getMaxMinValue()[1];
				try {
					for(int i=0;i<chartSeries.size();i++){
						String[] result = chartSeries.get(i).getMaxMinValue();
						if(Long.parseLong(result[0])>Long.parseLong(dx[0])){
							dx[0]=result[0];
						}
						if(Long.parseLong(result[0])>Long.parseLong(dx[0])){
							dx[1]=result[1];
						}
					}
				} catch (NumberFormatException e) {
					return "";
				}
				if(dx[1].equals("0")){
					props.setYAxisMinValue("0");
					props.setYAxisMaxValue(String.valueOf(Math.ceil(Long.parseLong(dx[0])*1.2)));
				}else if((Long.parseLong(dx[0])-Long.parseLong(dx[1]))/Long.parseLong(dx[1])<2){
					props.setYAxisMinValue(String.valueOf(Math.floor(Long.parseLong(dx[1])*0.4)));
					props.setYAxisMaxValue(String.valueOf(Math.ceil(Long.parseLong(dx[0])*1.2)));
				}
			}else{
				props.setYAxisMaxValue("20");
				props.setYAxisMinValue("0");
			}
			props.setyAxisName("Count");
			props.setAnchorradius("3");
			props.setConnectNullData("1");
			props.setLinethickness("1");
			chart.setChart(props);
//			Line line = new Line();
//			List<Line> lines = new ArrayList<Line>();
//			lines.add(line);
//			return chart.drawChart(lines);
			return chart.drawLineChart();
			//小于等于7天程序段
		} else {
			List<String> labels = LabelUtil.labelsByHour(startDate, endDate);
			ChartCategory category = new ChartCategory(labels);
			List<ChartCategory> categories = new ArrayList<ChartCategory>();
			categories.add(category);

			List<ChartSeries> chartSeries = new ArrayList<ChartSeries>(5);
			if (vlanId.equals("all")) {
				List<String> vlans = userByVlanDao.vlanTop5listByHour(startDate, endDate);
				for (String s : vlans) {
					Map<Long, Map<String, Object>> dbData = userByVlanDao.getUserCountByHour(startDate, endDate, s);
					List<String> date = DataSetUtils.labelvalue(false, labels, dbData, "stat3");
					String vlanName = vlanDao.getVlanNameByvId(s);
					if(vlanName==null){
						vlanName=s;
					}
					ChartSeries series = new ChartSeries(vlanName, date);
					chartSeries.add(series);
				}
			} else {
				Map<Long, Map<String, Object>> dbData = userByVlanDao.getUserCountByHour(startDate,endDate, vlanId);
				List<String> date = DataSetUtils.labelvalue(false, labels, dbData, "stat3");
				String vlanName = vlanDao.getVlanNameByvId(vlanId);
				ChartSeries series = new ChartSeries(vlanName, date);
				chartSeries.add(series);
			}
			Chart chart = new Chart(categories, chartSeries);
			ChartProperties props = new ChartProperties();
			props.setLabelStep(labels.size() / 12+"");
			if(chartSeries.size()!=0){
				String[] dx = new String[2];
				dx[0]=chartSeries.get(0).getMaxMinValue()[0];
				dx[1]=chartSeries.get(0).getMaxMinValue()[1];
				try {
					for(int i=0;i<chartSeries.size();i++){
						String[] result = chartSeries.get(i).getMaxMinValue();
						if(Long.parseLong(result[0])>Long.parseLong(dx[0])){
							dx[0]=result[0];
						}
						if(Long.parseLong(result[0])>Long.parseLong(dx[0])){
							dx[1]=result[1];
						}
					}
				} catch (NumberFormatException e) {
					return "";
				}
				if(dx[1].equals("0")){
					props.setYAxisMinValue("0");
					props.setYAxisMaxValue(String.valueOf(Math.ceil(Long.parseLong(dx[0])*1.2)));
				}else if((Long.parseLong(dx[0])-Long.parseLong(dx[1]))/Long.parseLong(dx[1])<2){
					props.setYAxisMinValue(String.valueOf(Math.floor(Long.parseLong(dx[1])*0.4)));
					props.setYAxisMaxValue(String.valueOf(Math.ceil(Long.parseLong(dx[0])*1.2)));
				}
			}else{
				props.setYAxisMaxValue("20");
				props.setYAxisMinValue("0");
			}
			props.setyAxisName("Count");
			props.setAnchorradius("0");
			props.setLinethickness("1");
			props.setCanvasPadding("10");
			chart.setChart(props);
//			Line line = new Line();
//			List<Line> lines = new ArrayList<Line>();
//			lines.add(line);
//			return chart.drawChart(lines);
//			return chart.drawChart();
			return chart.drawLineChart();
		}
	}
}
