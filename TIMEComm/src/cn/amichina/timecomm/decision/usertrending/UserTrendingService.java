package cn.amichina.timecomm.decision.usertrending;

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
import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.LabelUtil;

@Service
public class UserTrendingService {

	@Resource
	private UserTrendingDao userTrendingDao;

	public String toJson(Date startDate, Date endDate,String userId) throws SQLException {
		
		List<String> labels = null;
		List<String> users = null;
		
		List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();
		
		int betDays = DateUtil.daysBetween(startDate, endDate);
		if (betDays > 7) {
			labels = LabelUtil.labelsByDay(startDate, endDate);
			if(userId.equals("")){
				users = userTrendingDao.top10User(DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate));
				for(String user:users){
					Map<Long, Map<String, Object>> userData = userTrendingDao.getTrafficByDay(DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate),user);
					List<String> date = DataSetUtils.labelvalueMB(true, labels, userData, "sums");
					ChartSeries series = new ChartSeries(user, date);
					chartSeries.add(series);
				}
			}else{
				Map<Long, Map<String, Object>> upData = userTrendingDao.getUploadByDay(DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate), userId);
				List<String> upDate = DataSetUtils.labelvalueMB(true,labels, upData, "outnum");
				ChartSeries series1 = new ChartSeries("UpLoad", upDate);
				chartSeries.add(series1);
				
				Map<Long, Map<String, Object>> downData = userTrendingDao.getDownloadByDay(DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate), userId);
				List<String> downDate = DataSetUtils.labelvalueMB(true,labels, downData, "innum");
				ChartSeries series2 = new ChartSeries("DownLoad", downDate);
				chartSeries.add(series2);
			}
		//小于等于天程序段
		}else{
			labels = LabelUtil.labelsByHour(startDate, endDate);
			if(userId.equals("")){
				users = userTrendingDao.top10User(DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate));
				for(String user:users){
					Map<Long, Map<String, Object>> userData = userTrendingDao.getTrafficByHour(DateUtil.toDBDateTime(startDate)-120000,DateUtil.toDBDateTime(endDate)+110000,user);
					List<String> date = DataSetUtils.labelvalueMB(false, labels, userData, "sums");
					ChartSeries series = new ChartSeries(user, date);
					chartSeries.add(series);
				}
			}else{
				Map<Long, Map<String, Object>> upData = userTrendingDao.getUploadByHour(DateUtil.toDBDateTime(startDate)-120000,DateUtil.toDBDateTime(endDate)+110000, userId);
				List<String> upDate = DataSetUtils.labelvalueMB(false,labels, upData, "outnum");
				ChartSeries series1 = new ChartSeries("UpLoad", upDate);
				chartSeries.add(series1);
				
				Map<Long, Map<String, Object>> downData = userTrendingDao.getDownloadByHour(DateUtil.toDBDateTime(startDate)-120000,DateUtil.toDBDateTime(endDate)+110000, userId);
				List<String> downDate = DataSetUtils.labelvalueMB(false,labels, downData, "innum");
				ChartSeries series2 = new ChartSeries("DownLoad", downDate);
				chartSeries.add(series2);
			}
		}
		ChartCategory category = new ChartCategory(labels);
		List<ChartCategory> categories = new ArrayList<ChartCategory>(1);
		categories.add(category);
		Chart chart = new Chart(categories, chartSeries);
		ChartProperties props = new ChartProperties();
		if(betDays<=7){
			props.setLabelStep(String.valueOf(labels.size()/12));
		}
		props.setyAxisName("Traffic(MB)");
		props.setDecimals("3");
		chart.setChart(props);
		return chart.drawChart();
	}
}
