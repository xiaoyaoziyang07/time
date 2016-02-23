package cn.amichina.timecomm.group.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.group.dao.ServiceGroupDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class BaseTest  {
	@Resource
	private ServiceGroupDao dao ;
	@Test
	public void get(){
		System.out.println(dao);
	}
}
