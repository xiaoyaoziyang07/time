package cn.amichina.timecomm.locationreport.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.amichina.common.chart.Chart;
import cn.amichina.timecomm.locationreport.service.LocationReportService;
import cn.amichina.timecomm.vlan.model.Vlan;
import cn.amichina.timecomm.vlan.service.VlanService;

import com.opensymphony.xwork2.ActionSupport;

public class LocationReportAction extends ActionSupport {
	@Resource
	private LocationReportService locationReportService;
	@Resource
	private VlanService vlanService;
	private Date startDate;
	private Date endDate;
	private String data[]=new String[2];
	private List<Vlan> vlanlist;
	private int flowType;
	private String vlanId;
	@Override
	public String execute() throws Exception {
		vlanlist=vlanService.listVlan();
		return SUCCESS;
	}
	public String data() throws Exception {
		Chart[] chart =locationReportService.getLocationReport(startDate, endDate, flowType,vlanId);
		if(chart[0]!=null){
			data[0] =chart[0].drawChart();
		}
		if(chart[1]!=null){
			data[1] =chart[1].drawChart();
		}
		return "toJson";
	}
	public String[] getData() {
		return data;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<Vlan> getVlanlist() {
		return vlanlist;
	}
	public void setFlowType(int flowType) {
		this.flowType = flowType;
	}
	public void setVlanId(String vlanId) {
		this.vlanId = vlanId;
	}
}
 