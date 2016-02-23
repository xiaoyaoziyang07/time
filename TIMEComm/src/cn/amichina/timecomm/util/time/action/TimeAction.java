package cn.amichina.timecomm.util.time.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TimeAction extends ActionSupport {
	private String now;
	@Override
	public String execute() throws Exception {
		now=String.valueOf(new Date().getTime());
		return SUCCESS;
	}
	public String getNow() {
		return now;
	}
}