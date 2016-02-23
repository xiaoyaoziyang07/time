package cn.amichina.timecomm.userthroughput.action;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.amichina.common.chart.Chart;
import cn.amichina.common.exception.BusinessException;
import cn.amichina.timecomm.userthroughput.model.MonitorUser;
import cn.amichina.timecomm.userthroughput.service.UserThroughputService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserThroughputAction extends ActionSupport {
	@Resource
	private UserThroughputService userThroughputService;
	private List<MonitorUser> monitorUserList;
	private MonitorUser monitorUser;
	private String status;
	private String message;
	private String monitorUser_Status;
	private String monitorUser_IsShow;
	private String monitorUser_StartTime;
	private String monitorUser_EndTime;
	private String monitorUser_MonitorId;
	private String userId;
	private String [] serviceId;
	private Map<String,String> realTimeMonitorUserData;
	private int hourCount;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String updateMonitorUser() throws Exception {
		if(monitorUser!=null){
			try {
				monitorUser.setStatus(Integer.valueOf(monitorUser_Status));
				monitorUser.setIsShow(Integer.valueOf(monitorUser_IsShow));
				monitorUser.setStartTime(Long.valueOf(monitorUser_StartTime));
				monitorUser.setEndTime(Long.valueOf(monitorUser_EndTime));
				monitorUser.setMonitorId(Long.valueOf(monitorUser_MonitorId));
				userThroughputService.updateMonitorUser(monitorUser);
				this.status="success";
			} catch (BusinessException e) {
				this.status="fail";
				this.message=e.getMessage();
			}
		}
		return "toJson";
	}
	public String addMonitorUser() throws Exception {
			try {
				userThroughputService.addMonitorUser();
				this.status="success";
			} catch (BusinessException e) {
				this.status="fail";
				this.message=e.getMessage();
			}
		return "toJson";
	}
	public String realTimeMonitorUserData() throws Exception {
		Map<String, Chart> dataMap = userThroughputService.getThisHourTopUserApps(userId, hourCount, serviceId);
		realTimeMonitorUserData =new LinkedHashMap<String, String>(dataMap.size());
		for (Map.Entry<String, Chart> entry : dataMap.entrySet()) {
			realTimeMonitorUserData.put(entry.getKey(), entry.getValue().drawChart());
		}
		return "toJson";
	}
	public String monitorUserList() throws Exception {
		this.monitorUserList =userThroughputService.getMonitorUserList();
		return "toJson";
	}
	public void realTimeData()throws Exception{
		//指定输出内容类型和编码  
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");   
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        String result ="";
        try {
        	result =userThroughputService.getUserAppUsageQuantityByDate(userId, serviceId);
		} catch (Exception e) {
			 ServletActionContext.getResponse().setStatus(500);
		}
        out.print(result); 
            out.flush();  
            out.close();  
        //说明:因函数返回类型为void类型,即可不用返回，直接输出；
	}
	public List<MonitorUser> getMonitorUserList() {
		return monitorUserList;
	}
	public void setMonitorUser(MonitorUser monitorUser) {
		this.monitorUser = monitorUser;
	}
	public String getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public void setMonitorUser_MonitorId(String monitorUser_MonitorId) {
		this.monitorUser_MonitorId = monitorUser_MonitorId;
	}
	public void setMonitorUser_Status(String monitorUser_Status) {
		this.monitorUser_Status = monitorUser_Status;
	}
	public void setMonitorUser_IsShow(String monitorUser_IsShow) {
		this.monitorUser_IsShow = monitorUser_IsShow;
	}
	public void setMonitorUser_StartTime(String monitorUser_StartTime) {
		this.monitorUser_StartTime = monitorUser_StartTime;
	}
	public void setMonitorUser_EndTime(String monitorUser_EndTime) {
		this.monitorUser_EndTime = monitorUser_EndTime;
	}
	public Map<String, String> getRealTimeMonitorUserData() {
		return realTimeMonitorUserData;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setServiceId(String[] serviceId) {
		this.serviceId = serviceId;
	}
	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}
	public static int unAbs(int a) {
        return (a > 0) ? -a : a;
    }
}
