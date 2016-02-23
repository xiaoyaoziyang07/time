package cn.amichina.timecomm.quota.timebaseplan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;

public class TimeBasePlanAction extends ActionSupport {

	@Resource
	private TimeBasePolicyService timePolicyService;
	
	private String policyId;
	private String policyName;
	private String startTime1;
	private String endTime1;
	private String startTime2;
	private String endTime2;
	private long upload1;
	private long download1;
	private long upload2;
	private long download2;
	private int mon;
	private int tues;
	private int wed;
	private int thur;
	private int fri;
	private int sat;
	private int sun;
	private int isactived;
	private int ruleType;
	private int pageNum;
	private int pageSize;
	private Map<String, Object> map;
	private String msg;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String list() throws Exception {
		map = new HashMap<String, Object>();
		List<Map<String, Object>> timePolicys = timePolicyService.list(pageNum,pageSize);
		for(int i=0;i<timePolicys.size();i++){
			timePolicys.get(i).remove("createtime");
		}
		long total = timePolicyService.getTotalNum();
		map.put("timePolicys", timePolicys);
		map.put("total", total);
		return SUCCESS;
	}
	
	public String add() throws Exception {
		msg = timePolicyService.addNull();
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		msg = timePolicyService.delete(policyId);
		return SUCCESS;
	}
	public String edit() throws Exception{
		if(ruleType==1){
			timePolicyService.edit(policyId,policyName,ruleType,startTime1,startTime2,endTime1,endTime2,upload1,upload2,download1,download2);
		}
		if(ruleType==2){
			timePolicyService.edit(policyId,policyName,ruleType,mon,tues,wed,thur,fri,sat,sun,upload1,download1);
		}
		if(ruleType==3){
			timePolicyService.edit(policyId,policyName,ruleType,startTime1,endTime1,upload1,download1);
		}
		return SUCCESS;
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

	public String getStartTime1() {
		return startTime1;
	}

	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}

	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

	public String getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(String startTime2) {
		this.startTime2 = startTime2;
	}

	public String getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}

	public double getUpload1() {
		return upload1;
	}

	public void setUpload1(long upload1) {
		this.upload1 = upload1;
	}

	public long getDownload1() {
		return download1;
	}

	public void setDownload1(long download1) {
		this.download1 = download1;
	}

	public long getUpload2() {
		return upload2;
	}

	public void setUpload2(long upload2) {
		this.upload2 = upload2;
	}

	public long getDownload2() {
		return download2;
	}

	public void setDownload2(long download2) {
		this.download2 = download2;
	}

	public int isMon() {
		return mon;
	}

	public void setMon(int mon) {
		this.mon = mon;
	}

	public int isTues() {
		return tues;
	}

	public void setTues(int tues) {
		this.tues = tues;
	}

	public int isWed() {
		return wed;
	}

	public void setWed(int wed) {
		this.wed = wed;
	}

	public int isThur() {
		return thur;
	}

	public void setThur(int thur) {
		this.thur = thur;
	}

	public int isFri() {
		return fri;
	}

	public void setFri(int fri) {
		this.fri = fri;
	}

	public int isSat() {
		return sat;
	}

	public void setSat(int sat) {
		this.sat = sat;
	}

	public int isSun() {
		return sun;
	}

	public void setSun(int sun) {
		this.sun = sun;
	}

	public int isIsactived() {
		return isactived;
	}

	public void setIsactived(int isactived) {
		this.isactived = isactived;
	}

	public int getRuleType() {
		return ruleType;
	}

	public void setRuleType(int ruleType) {
		this.ruleType = ruleType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}