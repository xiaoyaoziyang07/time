package cn.amichina.common.util;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.amichina.timecomm.decision.boosttype.BoostPlan;
import cn.amichina.timecomm.decision.boosttype.BoostPlanService;

public class TIMEInitListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		WebApplicationContextUtils.getWebApplicationContext(application);
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
		BoostPlanService boostPlanService = (BoostPlanService) ac.getBean("boostPlanService");
		List<BoostPlan> boostPlans = null;
		try {
			boostPlans = boostPlanService.getAllBoostPlan();
		} catch (SQLException e) {
				throw new RuntimeException("初始化错误");
		}
		application.setAttribute("boostPlans", boostPlans);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
