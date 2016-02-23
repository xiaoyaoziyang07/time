package cn.amichina.timecomm.report.trendingreport.model;

import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.report.trendingreport.service.BandwidthService;
import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class BandwidthServiceTest  {
	@Resource
	private BandwidthService service ;
	@Test
	public void testGetBandwidthStackedAreaDateSet() throws NumberFormatException, SQLException{
		Date startDate= DateUtil.parseDate("2015-09-19", "yyyy-MM-dd");
		Date endDate= DateUtil.parseDate("2015-09-20", "yyyy-MM-dd");
		
		System.out.println(service.getBandwidthStackedAreaDateSet(startDate, endDate,5));
	}
}
