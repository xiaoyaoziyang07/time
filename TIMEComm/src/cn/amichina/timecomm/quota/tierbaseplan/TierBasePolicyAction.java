package cn.amichina.timecomm.quota.tierbaseplan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;

public class TierBasePolicyAction extends ActionSupport {

	@Resource
	private TierBasePolicyService tierPolicyService;
	
	private String policyId;
	private String policyName;
	private long upload;
	private long download;
	private String msg;
	private int pageNum;
	private int pageSize;
	private Map<String, Object> map;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String list() throws Exception {
		map = new HashMap<String, Object>();
		List<TierPolicy> tierPolicys = tierPolicyService.list(pageNum,pageSize);
		long total = tierPolicyService.getTotalNum();
		map.put("tierPolicys", tierPolicys);
		map.put("total", total);
		return SUCCESS;
	}
	
	public String add() throws Exception {
		tierPolicyService.addNull();
		return SUCCESS;
	}
	public String delete() throws Exception {
		tierPolicyService.delete(policyId);
		return SUCCESS;
	}
	public String edit() throws Exception{
		msg = tierPolicyService.edit(policyId,policyName,upload,download);
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

	public long getUpload() {
		return upload;
	}

	public void setUpload(long upload) {
		this.upload = upload;
	}

	public long getDownload() {
		return download;
	}

	public void setDownload(long download) {
		this.download = download;
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

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
