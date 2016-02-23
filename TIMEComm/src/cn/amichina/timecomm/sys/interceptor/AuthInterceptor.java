package cn.amichina.timecomm.sys.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.amichina.common.constant.SystemConstant;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class AuthInterceptor implements Interceptor {
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	private String allowAction;
	public String getAllowAction() {
		return allowAction;
	}
	public void setAllowAction(String allowAction) {
		this.allowAction = allowAction;
	}
	/* 
	 * 每次进入Action 之前都会回调该方法
	 * 该方法的ActionInvocation参数代表当前Action的执行环境，
	 * 通过该参数，就可以获取被调用Action的实例、被拦截的方法等。
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		Object userId = ctx.getSession().get(SystemConstant.SESSION_USER_KEY);
		String currentName = ctx.getName();
		for (String actName : allowAction.split(",")) {
			if (currentName.equals(actName)){
				return invocation.invoke();
			}
		}
		if(logger.isInfoEnabled()){
			if (userId == null) {
				logger.debug("未登录拦截!");
				return Action.LOGIN;
			}
		}
		return invocation.invoke();
	}
	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}
}
