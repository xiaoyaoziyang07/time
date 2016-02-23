package cn.amichina.timecomm.quota.qfupplan.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.quota.qfupplan.entity.QFUPPlan;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class QFUPPlanServiceTest  {
	@Resource
	private QFUPPlanService service ;
	@Test
	public void testPageQueryQFUPPlan() throws Exception{
		PageBean<QFUPPlan> pageBean =service.pageQueryQFUPPlan(new QueryInfo(2,2));
		System.out.println(pageBean.getList());
	}
	@Test
	public void testAddBlank() throws Exception{
		service.addBlank("TIME");
	}
	@Test
	public void testDelete() throws Exception{
		service.delete("TIME0L0Y");
	}
	@Test
	public void testUpdate() throws Exception{
		QFUPPlan qfupPlan= new QFUPPlan();
		qfupPlan.setPolicyid("TIME2HKJ");
		qfupPlan.setPolicyname("amitest");
		qfupPlan.setQuota(9999999l);
		qfupPlan.setFup1_percent(100);
		qfupPlan.setFup1_dl(99999l);
		qfupPlan.setFup1_ul(99999l);
		qfupPlan.setFup2_percent(99);
		qfupPlan.setFup2_dl(999999l);
		qfupPlan.setFup2_ul(999999l);
		qfupPlan.setUnit(0);
		
		service.update(qfupPlan);
	}
}
