package cn.amichina.timecomm.userthroughput.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.chart.Chart;
import cn.amichina.common.chart.category.ChartCategory;
import cn.amichina.common.chart.property.ChartProperties;
import cn.amichina.common.chart.series.ChartSeries;
import cn.amichina.common.exception.BusinessException;
import cn.amichina.timecomm.userthroughput.dao.UserThroughputDao;
import cn.amichina.timecomm.userthroughput.model.MonitorUser;
import cn.amichina.timecomm.util.DateUtil;

@Service
public class UserThroughputService {
	@Resource
	private UserThroughputDao userThroughputDao;

	public List<MonitorUser> getMonitorUserList() {
		return userThroughputDao.getMonitorUserListByStatus(1);
	}
	public synchronized void updateMonitorUser(MonitorUser monitorUser)
			throws BusinessException {
		MonitorUser dbMonitorUser = userThroughputDao.getMonitorUserByMonitorIdAndStatus(monitorUser.getMonitorId(), 1);
		try {
			if (dbMonitorUser == null) {
				throw new BusinessException("Update failed! The user does not exist.");
			}
			if (dbMonitorUser.getIsShow() != monitorUser.getIsShow()) {
				int count = userThroughputDao.getMonitorUserCountByIsShow(1);
				if (count > 6)
					throw new BusinessException("Show the number of users can not exceed 6 people!");
			}
			if (dbMonitorUser.getStatus() != monitorUser.getStatus()) {
				int count = userThroughputDao.getMonitorUserCountByStatus(1);
				if (count > 50)
					throw new BusinessException("Monitor the number of users can not exceed 50 people!");
			}
			if (monitorUser.getIsShow() == 1 && monitorUser.getStatus() != 1) {
				throw new BusinessException("Can not monitor the user real-time display!");
			}
			userThroughputDao.updateMonitorUser(monitorUser);
		} catch (BusinessException e) {
			throw e;
		}
	}
	public void addMonitorUser() throws BusinessException {
			userThroughputDao.addMoitorUser();
	}
/*	public void addMonitorUser(MonitorUser monitorUser) throws BusinessException {
		if (userThroughputDao.getMonitorUserByUserIdAndStatus(monitorUser.getUserId(), 1) != null) {
			throw new BusinessException("该用户已在监控列表中!");
		}
		MonitorUser dbmonitorUser=userThroughputDao.getMonitorUserByUserIdAndStatus(monitorUser.getUserId(), 0);
		if (dbmonitorUser!= null) {
			int count = userThroughputDao.getMonitorUserCountByIsShow(1);
			if (count > 6)
				throw new BusinessException("显示用户数量不能超过6人!");
			count = userThroughputDao.getMonitorUserCountByStatus(1);
			if (count > 50)
				throw new BusinessException("监控用户数量不能超过50人!");
			this.updateMonitorUser(monitorUser);
		} else {
			userThroughputDao.addMoitorUser(monitorUser);
		}
	}
*/
	public Map<String,Chart> getThisHourTopUserApps(String userId ,Integer hourCount,String [] appids) {
		List<String> showUsres = null;
		if(userId==null){
			showUsres = userThroughputDao.getMonitorUserListByIsShow(1);
		}else{
			showUsres= new ArrayList<String>();
			showUsres.add(userId); 
		}
		Map<String,Chart> topUserAppsMap =new LinkedHashMap<String, Chart>(showUsres.size());
		Date searchDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(searchDate);
		calendar.add(Calendar.HOUR, unAbs(hourCount));
		calendar.add(Calendar.MINUTE, unAbs(calendar.get(Calendar.MINUTE) % 5));
		calendar.set(Calendar.MILLISECOND,0);
		calendar.set(Calendar.SECOND,0);
		searchDate=calendar.getTime();
		calendar.setTime(searchDate);
		Date lastTime = DateUtil.add(searchDate, Calendar.HOUR,hourCount);
		List<String> lableList =new ArrayList<String>();
		for (Date tmpDate =new Date(searchDate.getTime()); tmpDate.getTime() <=lastTime.getTime() ;tmpDate=DateUtil.add(tmpDate, Calendar.MINUTE,5)) {
			lableList.add(DateUtil.toStr(tmpDate,DateUtil.EN_DATA_FORMAT_HH_MM));
		}
		/*for (int i = 0; i <forCount; i++,calendar.add(Calendar.MINUTE, 5)) {
			lableList.add(DateUtil.toStr(calendar.getTime(),DateUtil.EN_DATA_FORMAT_HH_MM));
		}*/
		//String lableSetp =String.valueOf((hourCount*13)/(hourCount*12));
		String lableSetp ="0";
		if(hourCount==1){
			lableSetp="0";
		}else if(hourCount==3) {
			lableSetp="1";
		}
		ChartCategory category = new ChartCategory(lableList);
		List<ChartCategory> categories = new ArrayList<ChartCategory>();
		categories.add(category);
		for (int i = 0; i < showUsres.size(); i++) {
			List<ChartSeries> chartSeriesList =new ArrayList<ChartSeries>(); 
			List<Map<String, Object>> topApps =userThroughputDao.getTopUserAppsByUserId(searchDate, showUsres.get(i));
			List<String> servcieList =new ArrayList<String>();
			for (Map<String, Object> topAppMap : topApps) {
				String serviceId=topAppMap.get("service").toString();
				List<Map<String,Object>> appValMaps  = userThroughputDao.getUserAppUsageQuantity(searchDate,serviceId,showUsres.get(i));
				List<String> valsList =new ArrayList<String>();
				for (Date tmpDate =new Date(searchDate.getTime()); tmpDate.getTime() <=lastTime.getTime() ;tmpDate=DateUtil.add(tmpDate, Calendar.MINUTE,5)) {
					 int index =-1;
					 String labelVal="0.00";
					 String dateTime =String.valueOf(DateUtil.toDBDateTime(tmpDate));
					for (int j = 0; j < appValMaps.size(); j++) {
						if(dateTime.equals(appValMaps.get(j).get("sj").toString())){
							index=j;
							break;
						}
					}
					if(index!=-1){
						labelVal =  String.valueOf(Long.valueOf(appValMaps.get(index).get("speed").toString()).doubleValue()/1024/1024);
						appValMaps.remove(index);
					}
					valsList.add(labelVal);
				}
				chartSeriesList.add(new ChartSeries(topAppMap.get("appname").toString(),valsList));
				servcieList.add(serviceId);
			}
			Chart chart =new Chart(categories, chartSeriesList);
			ChartProperties charproperties =new ChartProperties();
			StringBuilder sb = new StringBuilder();
			///report/realTimeMonitorUserData
			sb.append("/report/realTimeMonitorData?userId=");
			StringBuilder appSB =new StringBuilder();
			appSB.append("/report/realTimeMonitorUserData?hourCount=3&userId=");
			appSB.append(showUsres.get(i));
			sb.append(showUsres.get(i));
			for (String serviceId : servcieList) {
				sb.append("&serviceId=");
				sb.append(serviceId);
				appSB.append("&serviceId=");
				appSB.append(serviceId);
			}
			charproperties.setAppids(appSB.toString());
			charproperties.setLabelStep(lableSetp);
			charproperties.setClickURL("javascript:;");
			charproperties.setDataStreamURL(sb.toString());
			charproperties.setRefreshInterval("300");;
			chart.setChart(charproperties);
			topUserAppsMap.put(showUsres.get(i),chart);
		}
		return topUserAppsMap;
		// userThroughputDao.getTopUserAppsByUserId(searchDate, userId);
	}

	public String getUserAppUsageQuantityByDate(String userId,
			String[] serviceIds) throws BusinessException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
	//	if ((calendar.get(Calendar.MINUTE) % 5) == 0) {
			calendar.set(Calendar.MILLISECOND,0);
			calendar.set(Calendar.SECOND,0);
			calendar.set(Calendar.MINUTE,(calendar.get(Calendar.MINUTE)-(calendar.get(Calendar.MINUTE)%5)));
			List<Map<String, Object>> appValMaps = userThroughputDao.getUserAppsUsageQuantity(calendar.getTime(), serviceIds, userId);
			StringBuilder sb = new StringBuilder();
			sb.append("&label=");
			sb.append(DateUtil.toStr(calendar.getTime(), DateUtil.EN_DATA_FORMAT_HH_MM));
			sb.append("&value=");
			for (int i = 0; i < serviceIds.length; i++) {
				String defaultVal="0.00";
				int index =-1;
				for (int j = 0; j < appValMaps.size(); j++) {
					if (serviceIds[i].trim().equals(appValMaps.get(j).get("service").toString().trim())) {
						index =j;
						defaultVal=String.valueOf(Long.valueOf(appValMaps.get(j).get("sum").toString()).doubleValue()/1024/1024);
						break;
					}
				}
				if(index!=-1){
					appValMaps.remove(index);
				}
				if (i != 0) {
					sb.append("|");
				}
				sb.append(defaultVal);
			}
			return sb.toString();
	/*	}else{
			throw new BusinessException("");
		}*/
	}
	public static void main(String[] args) {
		int mon =59;
		int result =mon-(mon%5);
		System.out.println(result);
		//System.out.println(60*5);
	}
	public static int unAbs(int a) {
        return (a > 0) ? -a : a;
    }
}
