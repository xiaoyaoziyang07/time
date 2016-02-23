package cn.amichina.timecomm.userthroughput.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.userthroughput.model.MonitorUser;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserThroughputDaoTest  {
	@Resource
	private UserThroughputDao dao ;
	@Test
	public void testGetMonitorUserListByStatus() throws SQLException{
		List<MonitorUser> list = dao.getMonitorUserListByStatus(1);
		for (MonitorUser monitorUser : list) {
			System.out.println(monitorUser.toString());
		}
	}
}
