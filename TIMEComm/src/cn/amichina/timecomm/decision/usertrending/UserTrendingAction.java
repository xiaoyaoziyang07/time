package cn.amichina.timecomm.decision.usertrending;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.amichina.common.user.service.UserService;
import cn.amichina.timecomm.util.DateUtil;

import com.opensymphony.xwork2.ActionSupport;

public class UserTrendingAction extends ActionSupport{

	@Resource
	private UserTrendingService userTrendingService;
	@Resource
	private UserService userService;
	
	private Date startDate;
	private Date endDate;
	private String userId;
	private String data;
	private List<String> users;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String data() throws SQLException{
		data = userTrendingService.toJson(startDate,endDate,userId);
		return SUCCESS;
	}
	
	public String findUser(){
		if(userId.trim().length()>=4){
			users = userService.userList(userId);
		}
		return SUCCESS;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
}
