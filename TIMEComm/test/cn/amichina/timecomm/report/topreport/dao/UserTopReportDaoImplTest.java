package cn.amichina.timecomm.report.topreport.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserTopReportDaoImplTest  {
	@Resource
	private TopReportDao userTopReportDaoImpl ;
	@Test
	public void test(){
	}
}
