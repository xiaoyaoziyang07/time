package cn.amichina.timecomm.sertj.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.group.dao.ServiceGroupDao;
import cn.amichina.timecomm.group.model.ServiceGroup;
import cn.amichina.timecomm.report.sertj.service.SertjService;
import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class SertjServiceTest  {
	@Resource
	private SertjService service;
	@Resource
	private ServiceGroupDao dao ;
	@Test
	public void testGetDaySertjBySJAndServiceAndInternal_host() throws SQLException{
		List<ServiceGroup> grouplist =dao.serviceGroupList();
		System.out.println(service.getSertjReports(grouplist, "timeerbb@time.com", DateUtil.parseDate("20150728", "yyyyMMdd"), DateUtil.parseDate("20150730", "yyyyMMdd")));
	}
}
