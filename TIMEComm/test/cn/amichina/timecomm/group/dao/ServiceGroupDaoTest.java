package cn.amichina.timecomm.group.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.group.dao.ServiceGroupDao;
import cn.amichina.timecomm.group.model.ServiceGroup;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class ServiceGroupDaoTest  {
	@Resource
	private ServiceGroupDao dao ;
	@Test
	public void get() throws SQLException{
		List<ServiceGroup> list =dao.serviceGroupList();
		for (ServiceGroup serviceGroup : list) {
			System.out.println(serviceGroup);
		}
	}
}
