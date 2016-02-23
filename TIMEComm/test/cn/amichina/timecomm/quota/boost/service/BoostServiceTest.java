package cn.amichina.timecomm.quota.boost.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.quota.boost.entity.BoostInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class BoostServiceTest  {
	@Resource
	private BoostService service ;
	@Test
	public void testAddBlankBoost() throws SQLException{
		service.addBlankBoost("FREE", "FREEE");
	}
	@Test
	public void testDelBoost() throws SQLException{
		service.delBoost("FREEE4XLT");;
	}
	@Test
	public void testGetBoostByBoosttype() throws SQLException{
		List<BoostInfo> list =service.getBoostByBoosttype("FREE");;
		for (BoostInfo boostInfo : list) {
			System.out.println(boostInfo);
		}
	}
	@Test
	public void testUpdateBoost() throws SQLException{
		BoostInfo bi =new BoostInfo();
		bi.setPolicyid("dfd");
		bi.setPolicyname("aaaaa");
		bi.setBoosttype("aaaaaaaaaaa");
		bi.setUpload(99999l);
		bi.setDownload(9999l);
		bi.setDuration(99999l);
		bi.setClaimtime(99999l);
		bi.setValidation(99999l);
		bi.setIsactived(9);
		bi.setIsmodify(9);
		bi.setContent("aaaaaaa");
		service.updateBoost(bi);
	}
}
