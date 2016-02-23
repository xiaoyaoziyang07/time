package cn.amichina.timecomm.report.plusrservice.plusrstatistics.dao;

import java.util.Date;
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
	private PlusrServiceStatisticsDao dao ;
	@Test
	public void testGetPlusrServiceStatisticesByStar1(){
		Date [] star1s ={DateUtil.parseDate("08/14/2015"),DateUtil.parseDate("08/15/2015"),DateUtil.parseDate("08/16/2015"),DateUtil.parseDate("08/17/2015")};
	}
}
