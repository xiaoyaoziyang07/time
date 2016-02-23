package cn.amichina.timecomm.decision.boosttype;

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

@Service
public class BoostPlanService {

	@Resource
	private BoostPlanDao boostPlanDao;
	
	public List<BoostPlan> getAllBoostPlan() throws SQLException {
		return boostPlanDao.getAllBoostPlan();
	}

	public String toJson(Long startDate, Long endDate, String boostPlanId)throws SQLException {

		List<String> labels = null;
		List<String> boostTypeList = null;

		List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();
		if (boostPlanId.equals("all")) {
			labels = boostPlanDao.planTop10List(startDate, endDate);
			if (labels.size() == 0) {
				return null;
			}
			boostTypeList = boostPlanDao.boostTypeList(startDate, endDate);
			boostTypeList.add("Other");
			List<List<Object[]>> table = new ArrayList<List<Object[]>>();
			for (int i=0;i<labels.size();i++) {
				List<Object[]> dbData = boostPlanDao.getTypeByPlan(startDate, endDate, labels.get(i));
				List<Object[]> values = DataSetUtils.labelvalue(boostTypeList,dbData);
				table.add(values);
				Object[] otherDbData = boostPlanDao.getOtherByPlan(startDate, endDate,labels.get(i));
				if(otherDbData.length==0){
					table.get(i).get(boostTypeList.size()-1)[1] = null;
				}else{
					table.get(i).get(boostTypeList.size()-1)[1] = otherDbData[1];
				}
			}
			for (int j = 0; j < table.get(0).size(); j++) {
				List<String> chartData = new ArrayList<String>();
				for (int i = 0; i < table.size(); i++) {
					if (table.get(i).get(j)[1] == null) {
						chartData.add(null);
					} else {
						chartData.add(table.get(i).get(j)[1].toString());
					}
				}
				ChartSeries series = new ChartSeries((String) table.get(0).get(j)[0], chartData);
				chartSeries.add(series);
			}
		}else{
			labels = boostPlanDao.appTop10List(startDate, endDate,boostPlanId);
			if (labels.size() == 0) {
				return null;
			}
			List<Object[]> dbData = boostPlanDao.getTypeByBoostPlanId(startDate,endDate,boostPlanId);
			List<Object[]> chartData = DataSetUtils.labelvalue(labels, dbData);
			List<String> list = new ArrayList<String>();
			for(int i=0;i<chartData.size();i++){
				if(chartData.get(i)[1]==null){
					list.add(null);
				}else{
					list.add(chartData.get(i)[1].toString());
				}
			}
			ChartSeries series = new ChartSeries(boostPlanId, list);
			chartSeries.add(series);
		}
		ChartCategory category = new ChartCategory(labels);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		Chart chart = new Chart(categories, chartSeries);
		ChartProperties props = new ChartProperties();
		props.setyAxisName("Count");
		props.setCaption("Boost Type");
		props.setShowLegend("0");
		chart.setChart(props);
		return chart.drawColchart();
//		return chart.drawChart();
	}
}
