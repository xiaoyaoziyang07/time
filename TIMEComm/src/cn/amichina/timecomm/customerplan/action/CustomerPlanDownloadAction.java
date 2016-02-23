package cn.amichina.timecomm.customerplan.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import cn.amichina.timecomm.customerplan.service.CustomerPlanService;
import cn.amichina.timecomm.report.sertj.dao.DownloadService;
import cn.amichina.timecomm.util.DateUtil;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CustomerPlanDownloadAction extends ActionSupport {
	private File file; //下载的资源
	private String resType;//下载的文件类型
	private String resName; //下载的文件名 
	private String date;
	public void setDate(String date) {
		this.date = date;
	}
	@Resource
	private CustomerPlanService customerPlanService;
	@Resource
	private DownloadService downloadService;
	@Override
	public String execute() throws Exception {
		date =DateUtil.toStr(DateUtil.parseDate(date), DateUtil.EN_DATA_FORMAT_YYYYMMDD);
		this.file=new File(customerPlanService.getCustomerPlanHourPathByDate(date)+"/package_"+date+".xls");
		this.resName="report.xls";
		this.resType="application/vnd.ms-excel";
		return SUCCESS;
	}
	//该方法代表了文件下载的入口,他就是我们要让客户下载的文件的输入流
	public InputStream getTarget() throws IOException{
		//返回res资源所对应的输入流
		//FileInputStream
		return new FileInputStream(file);
	}
	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
}
 