package cn.amichina.timecomm.user.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import cn.amichina.common.constant.SystemConstant;
import cn.amichina.common.user.service.UserService;
import cn.amichina.timecomm.sys.model.MenuInfo;
import cn.amichina.timecomm.sys.service.MenuInfoService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HomeAction extends ActionSupport {
	@Resource
	private MenuInfoService menuInfoService;
	@Resource
	private UserService userService;
	private List<MenuInfo> userMenuInfos;
	private Long service_time;
	private String commonDateMonthsMessage;
	private String commonDateWeeksMessage;
	private String username;
	public String getUsername() {
		return username;
	}
	@Override
	public String execute() throws Exception {
		Object userId =ActionContext.getContext().getSession().get(SystemConstant.SESSION_USER_KEY);
		service_time=new Date().getTime();
		userMenuInfos =menuInfoService.getMenuListByUid(userId.toString());
		for (MenuInfo menuInfo : userMenuInfos) {
			menuInfo.setTitle(getText("ui.nav."+menuInfo.getId()+".title"));
			List<MenuInfo> listSubMenus=menuInfo.getSubMenus();
			if(listSubMenus!=null)
			for (MenuInfo menuInfo2 : listSubMenus) {
				menuInfo2.setTitle(getText("ui.nav."+menuInfo2.getId()+".title"));
			}
		}
		this.commonDateMonthsMessage=getText("ui.common.date.months");
		this.commonDateWeeksMessage=getText("ui.common.date.weeks");
		this.username=userService.getUserInfoByUid(userId.toString()).getAccount();
		return super.execute();
	}
	public List<MenuInfo> getUserMenuInfos() {
		return userMenuInfos;
	}
	public Long getService_time() {
		return service_time;
	}
	public String getCommonDateMonthsMessage() {
		return commonDateMonthsMessage;
	}
	public String getCommonDateWeeksMessage() {
		return commonDateWeeksMessage;
	}
}
