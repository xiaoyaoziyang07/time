package cn.amichina.timecomm.decision.boosttype;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.amichina.timecomm.util.DateUtil;

import com.opensymphony.xwork2.ActionSupport;

public class BoostTypeAction extends ActionSupport {

	@Resource
	private BoostPlanService boostPlanService;
	
	private Date startDate;
	private Date endDate;
	private String boostPlanId;
	private String data;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String data() throws SQLException{
		data = boostPlanService.toJson(DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate),boostPlanId);
		return SUCCESS;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public String getBoostPlanId() {
		return boostPlanId;
	}

	public void setBoostPlanId(String boostPlanId) {
		this.boostPlanId = boostPlanId;
	}
}
