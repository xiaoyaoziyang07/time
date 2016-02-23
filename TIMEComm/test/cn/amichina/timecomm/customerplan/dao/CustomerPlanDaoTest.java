package cn.amichina.timecomm.customerplan.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class CustomerPlanDaoTest  {
	@Resource
	private CustomerPlanDao dao ;
	@Test
	public void testPageQueryNotelogsByStartDateAndEndDateAndResultcodeNot0() throws Exception{
		System.out.println(dao.getCustomerPlanHourFiles());
	}
	@Test
	public void tesGetTopCustomerPlanByDay() throws Exception{
		Date startDate= DateUtil.parseDate("2015-08-13", "yyyy-MM-dd");
		Date endDate= DateUtil.parseDate("2015-08-15", "yyyy-MM-dd");
		List<Map<String,Object>> list =dao.getTopCustomerPlan(startDate, endDate, 0);
		System.out.println(list);
	}
}
