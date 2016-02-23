package cn.amichina.timecomm.apistatus.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.apistatus.model.Failured;
import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class NotelogDaoTest  {
	@Resource
	private NotelogDao dao ;
	@Test
	public void testGetCountNotelogsByStartDateAndEndDateNot0() throws SQLException{
		Date startDate= DateUtil.parseDate("2015-08-19", "yyyy-MM-dd");
		Date endDate= DateUtil.parseDate("2015-08-19", "yyyy-MM-dd");
		List<Failured> list =dao.getCountFailuredByStartDateAndEndDateAndResultcodeNot0(startDate, endDate);
		for (Failured notelog : list) {
			System.out.println(notelog);
		}
	}
}
