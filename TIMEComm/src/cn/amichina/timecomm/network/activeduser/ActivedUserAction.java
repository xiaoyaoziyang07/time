package cn.amichina.timecomm.network.activeduser;

import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import cn.amichina.timecomm.util.DateUtil;

import com.opensymphony.xwork2.ActionSupport;

public class ActivedUserAction extends ActionSupport{

	@Resource
	private ActivedUserService activedUserService;
	
	private Date startDate;
	private Date endDate;
	private int type;
	private String data;

	@Override
	public String execute(){
		return SUCCESS;
	}
	
	public String data() throws SQLException{
		data = activedUserService.toJson(DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate),type);
		System.out.println(data);
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
