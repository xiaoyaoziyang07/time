package cn.amichina.timecomm.bandwidth.action;

import java.util.Date;

import javax.annotation.Resource;

import cn.amichina.timecomm.report.trendingreport.model.StackedAreaDateSet;
import cn.amichina.timecomm.report.trendingreport.service.BandwidthService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BandwidthAction extends ActionSupport {
	private static final String JSONDATA = "toJson";
	@Resource
	private BandwidthService bandwidthService;
	private Date startDate;
	private Date endDate;
	private Integer type;
	private StackedAreaDateSet dateSet;

	@Override
	public String execute() throws Exception {
		if (startDate != null && endDate != null) {
			try {
				dateSet = bandwidthService.getBandwidthStackedAreaDateSet(startDate, endDate,type);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JSONDATA;
		}
		return SUCCESS;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public StackedAreaDateSet getDateSet() {
		return dateSet;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
