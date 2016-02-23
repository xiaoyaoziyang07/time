package cn.amichina.timecomm.sys.interceptor;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.amichina.timecomm.util.JdbcTools;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class OpenSessionInViewInterceptor implements Interceptor {
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			if(logger.isDebugEnabled()){
			    //在Action中使用ActionContext得到parameterMap获取参数:
				StringBuilder sb_parms=new StringBuilder();
		        Map<String,String[]> parmsMap =ServletActionContext.getRequest().getParameterMap();
		        for (Map.Entry<String, String[]> entry : parmsMap.entrySet()) {
		        	sb_parms.append(entry.getKey().toString()+"=");
		        	sb_parms.append(Arrays.toString(entry.getValue()));
				}
		        if(parmsMap.isEmpty()){
		        	logger.debug("该请求参数列表为空.");
		        }else{
		        	logger.debug("请求参数列表:"+sb_parms);
		        }
			}
			return invocation.invoke();
		} finally {
			JdbcTools.closeConnection();
			if(logger.isDebugEnabled()){
				logger.debug("请求主机:"+ServletActionContext.getRequest().getRemoteAddr());
				logger.debug("回收数据库资源");
			}
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}
}
