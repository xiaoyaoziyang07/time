package cn.amichina.timecomm.report.plusrservice.plusrstatistics.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.report.plusrservice.plusrstatistics.model.PlusrServiceStatistics;
import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class PlusrServiceStatisticsDaoTest  {
	@Resource
	private PlusrServiceStatisticsService service ;
	@Test
	public void testGetPlusrServiceStatisticesByStar1() throws Exception{
		List<PlusrServiceStatistics>  list  =service.getPlusrServiceStatisticesByStartDateAndEndDate(DateUtil.parseDate("08/12/2015"), DateUtil.parseDate("08/18/2015"));
		System.out.println(list.size());
		for (PlusrServiceStatistics plusrServiceStatistics : list) {
			System.out.println(plusrServiceStatistics);
		}
	}
}
