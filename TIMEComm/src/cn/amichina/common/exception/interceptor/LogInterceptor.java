package cn.amichina.common.exception.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
/**
 * 
 * Create by 石磊  on 2015年9月16日 上午10:53:24
 *
 * Spring 统一日志处理实现类
 */
//@Component
public class LogInterceptor implements MethodInterceptor
{
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	public Object invoke(MethodInvocation invocation) throws Throwable
	{
		logger.info("--Log By Colin -----------------------------------------------------------------------------");
		logger.info(invocation.getMethod() + ":BEGIN!--(Colin LOG)");// 方法前的操作
		Object obj = invocation.proceed();// 执行需要Log的方法
		logger.info(invocation.getMethod() + ":END!--(Colin LOG)");// 方法后的操作
		logger.info("-------------------------------------------------------------------------------------------------");
		return obj;
	}

}
