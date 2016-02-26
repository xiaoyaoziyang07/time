package cn.amichina.timecomm.network.hottestsites;

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
import cn.amichina.timecomm.vlan.dao.VlanDao;

@Service
public class HottestSitesService {

	@Resource
	private VlanDao vlanDao;
	@Resource
	private HottestSitesDao hottestSitesDao;

	public String toJson(Long startDate, Long endDate, String vlanId)throws SQLException {

		List<String> labels = null;
		List<String> vlanList = null;
		
		List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();
		if (vlanId.equals("all")) {
			labels = hottestSitesDao.top10Sites(startDate, endDate);
			if(labels.size()==0){
				return null;
			}
			vlanList = hottestSitesDao.vlanList(startDate, endDate);
			vlanList.add("@@@");
			List<List<Object[]>> table = new ArrayList<List<Object[]>>();
			for (int i = 0; i < labels.size(); i++) {
				List<Object[]> dbData = hottestSitesDao.getVlanData(startDate,endDate, labels.get(i));
				List<Object[]> values = DataSetUtils.labelvalue(vlanList, dbData);
				table.add(values);
				Object[] otherDbData = hottestSitesDao.getOtherVlanData(startDate, endDate, labels.get(i));
				if (otherDbData.length == 0) {
					table.get(i).get(vlanList.size() - 1)[1] = null;
				} else {
					table.get(i).get(vlanList.size() - 1)[1] = otherDbData[1];
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
				ChartSeries series = new ChartSeries(vName,chartData);
				chartSeries.add(series);
			}
		//指定vlanId查询程序段
		} else {
			labels = hottestSitesDao.top10SitesByVlan(startDate, endDate,vlanId);
			if (labels.size() == 0) {
				return null;
			}
			List<Object[]> dbData = hottestSitesDao.getDataByVlanId(startDate,endDate,vlanId);
			List<Object[]> chartData = DataSetUtils.labelvalue(labels, dbData);
			
			List<String> list = new ArrayList<String>();
			for(int i=0;i<chartData.size();i++){
				if(chartData.get(i)[1]==null){
					list.add(null);
				}else{
					list.add(chartData.get(i)[1].toString());
				}
			}
			ChartSeries series = new ChartSeries(vlanId, list);
			chartSeries.add(series);
		}
		ChartCategory category = new ChartCategory(labels);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		ChartProperties props = new ChartProperties();
		props.setyAxisName("Count");
		props.setCaption("Hottest Sites");
		props.setShowLegend("0");
		Chart chart = new Chart(props,categories, chartSeries);
		return chart.drawColchart();
	}
}