package liyang;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.amichina.timecomm.quota.tierbaseplan.TierBasePolicyService;
import cn.amichina.timecomm.quota.tierbaseplan.TierPolicy;
import cn.amichina.timecomm.quota.timebaseplan.TimeBasePolicy;
import cn.amichina.timecomm.quota.timebaseplan.TimeBasePolicyService;
import cn.amichina.timecomm.quota.topupvolumn.TopUpVolumn;
import cn.amichina.timecomm.quota.topupvolumn.TopUpVolumnService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class StringTest {
	@Resource
	private TierBasePolicyService service1;
	@Resource
	private TimeBasePolicyService service2;
	@Resource
	private TopUpVolumnService service3;

	@Test
	public void test01() throws Exception {
		TierPolicy bean = service1.getById("TrBPKWHD");
		System.out.println(bean.getDownload());
	}

	@Test
	public void test02() throws Exception {
		TimeBasePolicy bean = service2.getById("TmBPV1K2");
		System.out.println(bean.getDownload1());
	}

	@Test
	public void test03() throws Exception {
		TopUpVolumn bean = service3.getById("TPVA5Q1Y");
		System.out.println(bean.getTraffic());
	}
	
	@Test
	public void test04() throws Exception {
		People p = new People();
		p.setAge("18");
		p.setName("liyang");
		List<People> l = new ArrayList<People>();
		l.add(p);
		List<List<People>> a = new ArrayList<List<People>>();
		a.add(l);
		System.out.println(a);
	}
}
