package cn.amichina.timecomm.report.plusrservice.dao;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.report.plusrservice.model.PlusrService;
import cn.amichina.timecomm.sys.model.QueryResult;
import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class PlusrServiceDaoTest {
	@Resource
	private PlusrServiceDao dao ;
	@Test
	public void testPageQueryPlusrServicesByStartDateEndAndDateAndProvtypes() throws Exception{
		String [] provtypes =new String[]{"T1001","T1002"};
		Date startDate= DateUtil.parseDate("2015-07-05", "yyyy-MM-dd");
		Date endDate= DateUtil.parseDate("2015-07-10", "yyyy-MM-dd");
		QueryResult<PlusrService>  queryResult =dao.pageQueryPlusrServicesByStartDateEndAndDateAndProvtypes(0,5,startDate,endDate,provtypes);
		for (PlusrService plusrService : queryResult.getList()) {
			System.out.println(plusrService);
		}
		System.out.println(queryResult.getTotalRecord());
	}
	@Test
	public void testGetPlusrServiceTotal() throws Exception{
		String [] provtypes =new String[]{"T1001","T1002"};
		Date startDate= DateUtil.parseDate("2015-07-05", "yyyy-MM-dd");
		Date endDate= DateUtil.parseDate("2015-07-10", "yyyy-MM-dd");
		Long total =dao.getPlusrServiceTotal(startDate,endDate,provtypes);
		System.out.println(total);
	}
}
