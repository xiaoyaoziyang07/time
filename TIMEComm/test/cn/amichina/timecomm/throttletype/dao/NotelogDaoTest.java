package cn.amichina.timecomm.throttletype.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class NotelogDaoTest  {
	@Resource
	private ThrottleTypeDao dao ;
	@Test
	public void testGetCountNotelogsByStartDateAndEndDateNot0() throws SQLException{
		Date startDate= DateUtil.parseDate("2015-11-01", "yyyy-MM-dd");
		Date endDate= DateUtil.parseDate("2015-11-13", "yyyy-MM-dd");
		List<Map<String, Object>>  map =dao.getTopThrottleTypeList(startDate, endDate);
		System.out.println(map);
	}
}
