package cn.amichina.timecomm.user.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.amichina.common.constant.SystemConstant;
import cn.amichina.common.exception.BusinessException;
import cn.amichina.common.user.service.UserService;
import cn.amichina.timecomm.sys.model.UserInfo;
import cn.amichina.timecomm.user.exception.AccountAndPasswdMismatchException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	private String account;
	private String passwd;
	private String newpasswd;
	private String message;
	private String status;
	@Resource
	private UserService userService;

	public String login() throws Exception {
		String result = null;
		if (ServletActionContext.getRequest().getMethod().equals("GET")) {
			result = LOGIN;
		} else {
			UserInfo userInfo = null;
			try {
				userInfo = userService.login(account, passwd);
			}catch(AccountAndPasswdMismatchException e) {
				message=e.getMessage();
			}
			if (userInfo == null) {
				logger.debug("用户登录失败!");
				result = LOGIN;
			} else {
				logger.debug("用户ID:" + userInfo.getAccount() + " 登录成功!");
				ActionContext.getContext().getSession()
						.put(SystemConstant.SESSION_USER_KEY, userInfo.getId());
				result = SUCCESS;
			}
		}
		return result;
	}
	public String logout()throws Exception{
		ActionContext.getContext().getSession().remove(SystemConstant.SESSION_USER_KEY);
		return SUCCESS;
	}
	public String updatePwd()throws Exception{
		final String result ="toPage";
		String reqMethod =ServletActionContext.getRequest().getMethod();
		if(reqMethod.equals("GET")){
			return result;
		}else if(reqMethod.equals("POST")){
			try {
				userService.resetPasswordByUserIdAndOldPassword(ActionContext.getContext().getSession().get(SystemConstant.SESSION_USER_KEY).toString(), this.passwd, newpasswd);
				status=SUCCESS;
			} catch (BusinessException e) {
				message=getText(e.getLocalizedMessage());
				status=ERROR;
			} catch (RuntimeException e) {
			status=ERROR;
			message=getText(e.getMessage());
			}
			status=SUCCESS;
		}
		return SUCCESS;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getMessage() {
		return message;
	}
	public String getStatus() {
		return status;
	}
	
	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}
}
