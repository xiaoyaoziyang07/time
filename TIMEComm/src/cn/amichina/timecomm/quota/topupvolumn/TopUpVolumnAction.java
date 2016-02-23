package cn.amichina.timecomm.quota.topupvolumn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;

public class TopUpVolumnAction extends ActionSupport {
	
	@Resource
	private TopUpVolumnService topUpVolumnService;

	private String policyId;
	private String policyName;
	private long traffic;
	private Map<String, Object> map;
	private String msg;
	private int pageNum;
	private int pageSize;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String list() throws Exception {
		map = new HashMap<String, Object>();
		List<TopUpVolumn> topUpPolicys = topUpVolumnService.list(pageNum,pageSize);
		long total = topUpVolumnService.getTotalNum();
		map.put("topUpvolumns", topUpPolicys);
		map.put("total", total);
		return SUCCESS;
	}
	
	public String add() throws Exception {
		topUpVolumnService.addNull();
		return SUCCESS;
	}
	public String delete() throws Exception {
		topUpVolumnService.delete(policyId);
		return SUCCESS;
	}
	public String edit() throws Exception{
		msg = topUpVolumnService.edit(policyId,policyName,traffic);
		return SUCCESS;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public long getTraffic() {
		return traffic;
	}

	public void setTraffic(long traffic) {
		this.traffic = traffic;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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
