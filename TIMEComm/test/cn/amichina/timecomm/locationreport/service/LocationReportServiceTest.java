package cn.amichina.timecomm.locationreport.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class LocationReportServiceTest  {
	@Resource
	private LocationReportService service ;
	@Test
	public void test() throws Exception{
		Date startDate=DateUtil.parseDate("20151101", "yyyyMMdd");
		Date endDate =DateUtil.parseDate("20151101", "yyyyMMdd");
		Long startTime =System.currentTimeMillis();
		//System.out.println(service.test(startDate, endDate, 0)[0].drawChart());
		Long endTime =System.currentTimeMillis();
		System.out.println("耗时:"+((endTime-startTime)/1000));
	}
}
