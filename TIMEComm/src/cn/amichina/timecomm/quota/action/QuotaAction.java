package cn.amichina.timecomm.quota.action;

import javax.annotation.Resource;

import cn.amichina.timecomm.quota.model.Quota;
import cn.amichina.timecomm.quota.service.QuotaService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class QuotaAction extends ActionSupport {
	@Resource
	private QuotaService quotaService;
	private Quota quota;
	private String userId;
	@Override
	public String execute() throws Exception {
		if(userId==null){
			userId="";
		}else{
			this.quota=quotaService.getQuotaByUserId(userId);
		}
		return SUCCESS;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Quota getQuota() {
		return quota;
	}
}
