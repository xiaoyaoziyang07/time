package cn.amichina.timecomm.userthroughput.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.common.exception.BusinessException;
import cn.amichina.timecomm.userthroughput.model.MonitorUser;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserThroughputServiceTest  {
	@Resource
	private UserThroughputService service;
	@Test
	public void testGetMonitorUserListByStatus() throws BusinessException {
		//MonitorUser [userId=userid2, status=0, isShow=0, 
		//startTime=2, endTime=2, timeStamp=null, monitorId=null]
		MonitorUser monitorUser =new MonitorUser();
		monitorUser.setUserId(null);
		monitorUser.setStatus(0);
		monitorUser.setIsShow(0);
		monitorUser.setStartTime(0l);
		monitorUser.setEndTime(0l);
		monitorUser.setTimeStamp(null);
		monitorUser.setMonitorId(99l);;
		service.updateMonitorUser(monitorUser);
	}
	@Test
	public void testGetThisHourTopUserApps(){
		//service.getThisHourTopUserApps("userid1");
	}
	@Test
	public void testGetUserAppUsageQuantityByDate(){
		String result;
		try {
			result = service.getUserAppUsageQuantityByDate("userid1", new String[]{"0","1"});
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
