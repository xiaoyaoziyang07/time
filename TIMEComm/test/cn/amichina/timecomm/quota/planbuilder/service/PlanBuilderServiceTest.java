package cn.amichina.timecomm.quota.planbuilder.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.quota.planbuilder.model.PlanBuilder;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class PlanBuilderServiceTest  {
	@Resource
	private  PlanBuilderService service ;
	@Test
	public void testAddBlankPlanBuilder() throws Exception{
		service.addBlankPlanBuilder();
	}
	@Test
	public void testPageQueryPlanBuilder() throws Exception {
		PageBean<PlanBuilder> pageBean =service.pageQueryPlanBuilder(new QueryInfo(2, 2));
		System.out.println(pageBean);
	}
	@Test
	public void testRemove() throws Exception {
		String planid = "s";
		service.remove(planid);
	}
	@Test
	public void testUpdate() throws Exception {
		PlanBuilder planBuilder = new PlanBuilder();
		planBuilder.setPlanid("TIMELI26");
		planBuilder.setPlanname("planname");
		//planBuilder.setPaymenttype("ss");
		planBuilder.setPolicyid("haha");
		planBuilder.setSource((short) 1);
		planBuilder.setPlantype((short) 2);
		planBuilder.setPackageid("packageid");
		service.update(planBuilder);
	}
}
