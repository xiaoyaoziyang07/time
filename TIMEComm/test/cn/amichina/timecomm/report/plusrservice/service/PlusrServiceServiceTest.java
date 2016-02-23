package cn.amichina.timecomm.report.plusrservice.service;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.report.plusrservice.model.PlusrService;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class PlusrServiceServiceTest {
	@Resource
	private PlusrServiceService service ;
	@Test
	public void testPageQueryPlusrServiceRestoration() throws Exception{
		long createTime =1436505694348l;
		System.out.println(DateUtil.toStr(new Date(createTime)));
		QueryInfo queryInfo = new QueryInfo(1, 3);
		PageBean<PlusrService> pageBean =service.pageQueryPlusrServiceSuspend(new Date(createTime), new Date(createTime), queryInfo);
		for (PlusrService ps : pageBean.getList()) {
			System.out.println(ps);
		}
		//System.out.println(pageBean.getList().size());
	}
}
