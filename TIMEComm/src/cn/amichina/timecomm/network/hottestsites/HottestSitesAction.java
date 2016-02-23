package cn.amichina.timecomm.network.hottestsites;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.vlan.model.Vlan;
import cn.amichina.timecomm.vlan.service.VlanService;

import com.opensymphony.xwork2.ActionSupport;

public class HottestSitesAction extends ActionSupport {

	@Resource
	private VlanService vlanService;
	@Resource
	private HottestSitesService hottestSitesService;

	private Date startDate;
	private Date endDate;
	private List<Vlan> vlanList;
	private String vlanId;
	private String data;

	@Override
	public String execute() throws Exception {
		vlanList = vlanService.listVlan();
		return SUCCESS;
	}

	public String data() throws SQLException {
		data = hottestSitesService.toJson(DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate),vlanId);
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

	public List<Vlan> getVlanList() {
		return vlanList;
	}

	public void setVlanList(List<Vlan> vlanList) {
		this.vlanList = vlanList;
	}

	public String getVlanId() {
		return vlanId;
	}

	public void setVlanId(String vlanId) {
		this.vlanId = vlanId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
