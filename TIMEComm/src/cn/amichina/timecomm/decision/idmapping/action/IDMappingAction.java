package cn.amichina.timecomm.decision.idmapping.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.amichina.timecomm.decision.idmapping.MappingRecordBean;
import cn.amichina.timecomm.decision.idmapping.service.IDMappingService;

import com.opensymphony.xwork2.ActionSupport;

public class IDMappingAction extends ActionSupport {

	@Resource
	private IDMappingService mappingService;

	private Map<String, Object> data;
	private String userId;
	private String ip;
	private String planId;
	private int pageNum;
	private int pageSize;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String data(){
		Long totalNum = mappingService.getTotalNum(userId, ip, planId);
		List<MappingRecordBean> recordList = mappingService.toJson(userId, ip,planId, pageNum,pageSize);
		data = new HashMap<String, Object>();
		data.put("totalNum", totalNum);
		data.put("recordList", recordList);
//		this.data = "{totalNum:" + totalNum + ",recordList:" + recordList.toString() + "}";
		return SUCCESS;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
