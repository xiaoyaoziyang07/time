package cn.amichina.timecomm.apistatus.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.apistatus.model.Failured;
import cn.amichina.timecomm.apistatus.model.Notelog;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class NotelogServiceTest  {
	@Resource
	private NotelogService service ;
	@Test
	public void testPageQueryNotelogsByStartDateAndEndDateAndResultcodeNot0() throws Exception{
		QueryInfo queryInfo = new QueryInfo(2,5);
		PageBean<Notelog> pageBean =service.pageQueryNotelogsByStartDateAndEndDateAndResultcodeNot0(queryInfo, DateUtil.parseDate("20140810", "yyyyMMdd"), DateUtil.parseDate("20150819", "yyyyMMdd"),"T1005");
		System.out.println(pageBean);
		List<Notelog> list =pageBean.getList();
		for (Notelog notelog : list) {
			System.out.println(notelog);
		}
		System.out.println(list.size());
		System.out.println(pageBean.getTotalRecord());
	}
	@Test
	public void testGetGourp() throws Exception {
		List<Failured> list =service.getCountFailuredByStartDateAndEndDateAndResultcodeNot0(DateUtil.parseDate("20150601", "yyyyMMdd"), DateUtil.parseDate("20150703", "yyyyMMdd"));
		System.out.println(list);
	}
}
