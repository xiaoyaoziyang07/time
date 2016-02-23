package cn.amichina.timecomm.report.topreport.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.report.model.TopReport;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class TopReportServiceTest  {
	@Resource
	private TopReportService topReportService ;
	@Test
	public void testPageQueryReport() throws Exception{
		String [] topReportTargets ={"url","user","servicename","application"};
		Date startDate=DateUtil.parseDate("20140810", "yyyyMMdd");
		Date endDate =DateUtil.parseDate("20150819", "yyyyMMdd");
		for (String target : topReportTargets) {
			QueryInfo queryInfo = new QueryInfo(1,10);
			PageBean<TopReport> pageBean =topReportService.pageQueryReport(queryInfo, startDate,endDate,target);
			System.out.println("--------------"+target+"----------------");
			System.out.println("list="+pageBean.getList());
			System.out.println("TotalRecord="+pageBean.getTotalRecord());
			System.out.println("----------------------------------");
		}
	}
}
