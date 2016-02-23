package cn.amichina.timecomm.customerplan.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class CustomerPlanServiceTest  {
	@Resource
	private CustomerPlanService service ;
	@Test
	public void testPageQueryNotelogsByStartDateAndEndDateAndResultcodeNot0() throws Exception{
		Date startDate=DateUtil.parseDate("20151012", "yyyyMMdd");
		Date endDate =DateUtil.parseDate("20151014", "yyyyMMdd");
		Long startTime =System.currentTimeMillis();
		System.out.println(service.getCustomerPlanUsageQuantityStackedAreaDateSet(startDate, endDate, 0,null));
		Long endTime =System.currentTimeMillis();
		System.out.println("耗时:"+((endTime-startTime)/1000));
	}
	@Test
	public void testGetTopCustomerPlanByStartDateAndEndDateAndFlowType() throws Exception{
		Date startDate=DateUtil.parseDate("20151012", "yyyyMMdd");
		Date endDate =DateUtil.parseDate("20151015", "yyyyMMdd");
		Long startTime =System.currentTimeMillis();
		service.getTopCustomerPlanByStartDateAndEndDateAndFlowType(startDate, endDate, 0);
		Long endTime =System.currentTimeMillis();
		System.out.println("耗时:"+((endTime-startTime)/1000));
	}
}
