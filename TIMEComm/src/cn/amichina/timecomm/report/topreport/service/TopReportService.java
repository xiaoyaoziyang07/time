package cn.amichina.timecomm.report.topreport.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.amichina.timecomm.report.model.TopReport;
import cn.amichina.timecomm.report.topreport.dao.TopReportDao;
import cn.amichina.timecomm.report.trendingreport.dao.BandwidthDao;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
@Service
public class TopReportService {
	@Resource
	private TopReportDao applicationTopReportDaoImpl; 
	@Resource
	private TopReportDao serviceNameTopReportDaoImpl; 
	@Resource
	private TopReportDao URLTopReportDaoImpl; 
	@Resource
	private TopReportDao userTopReportDaoImpl; 
	@Resource
	private TopReportDao vlanTopReportDaoImpl;
	@Resource
	private BandwidthDao bandwidthDao;	
	private static final String []TOPREPORT_TARGETS={
							"url",
							"user",
							"servicename",
							"application",
							"vlan"
							};
	/**
	 *	根据应用名分页查询TopReport  
	 * @param queryInfo
	 * @param startDate
	 * @param endDate
	 * @param target
	 * @return
	 * @throws Exception 
	 */
	public PageBean<TopReport> pageQueryReport(QueryInfo queryInfo,Date startDate,Date endDate,final String target) throws Exception{
		PageBean<TopReport> pageBean =null;
		if(target.equals(TOPREPORT_TARGETS[0])){
			pageBean=new PageBean<TopReport>(queryInfo,URLTopReportDaoImpl.pageQueryTopReport(queryInfo.getStartIndex(), queryInfo.getPageSize(), startDate, endDate));
		}else if(target.equals(TOPREPORT_TARGETS[1])){
			pageBean=new PageBean<TopReport>(queryInfo,userTopReportDaoImpl.pageQueryTopReport(queryInfo.getStartIndex(), queryInfo.getPageSize(), startDate, endDate));
		}else if(target.equals(TOPREPORT_TARGETS[2])){
			pageBean=new PageBean<TopReport>(queryInfo,serviceNameTopReportDaoImpl.pageQueryTopReport(queryInfo.getStartIndex(), queryInfo.getPageSize(), startDate, endDate));
		}else if(target.equals(TOPREPORT_TARGETS[3])){
			pageBean=new PageBean<TopReport>(queryInfo,applicationTopReportDaoImpl.pageQueryTopReport(queryInfo.getStartIndex(), queryInfo.getPageSize(), startDate, endDate));
		}else if(target.equals(TOPREPORT_TARGETS[4])){
			pageBean=new PageBean<TopReport>(queryInfo,vlanTopReportDaoImpl.pageQueryTopReport(queryInfo.getStartIndex(), queryInfo.getPageSize(), startDate, endDate));
		}
		return pageBean;
	}
	private static final String []TOPREPORT_HEADERS={
		"ui.report.topreport.no",
		"ui.report.topreport.download",
		"ui.report.topreport.upLoad"
	};
	private static final String [] TOPREPORT_HEAD_TOPS={
		"ui.report.topreport.url.tops",
		"ui.report.topreport.user.tops",
		"ui.report.topreport.servicename.tops",
		"ui.report.topreport.application.tops",
		"ui.report.topreport.vlan.tops"
	};
	private static final String [] TOPREPORT_HEAD_TOTALS={
		"ui.report.topreport.url.total",
		"ui.report.topreport.user.total",
		"ui.report.topreport.servicename.total",
		"ui.report.topreport.application.total",
		"ui.report.topreport.vlan.total",
	};
	public String[] getTopReportHeaders(final String target){
		String []topReportHeaders={TOPREPORT_HEADERS[0],null,TOPREPORT_HEADERS[1],TOPREPORT_HEADERS[2],null};
		if (target.equals(TOPREPORT_TARGETS[0])) {
			topReportHeaders[1]=TOPREPORT_HEAD_TOPS[0];
			topReportHeaders[4]=TOPREPORT_HEAD_TOTALS[0];
		} else if (target.equals(TOPREPORT_TARGETS[1])) {
			topReportHeaders[1]=TOPREPORT_HEAD_TOPS[1];
			topReportHeaders[2]="ui.report.topreport.mbdownload";
			topReportHeaders[3]="ui.report.topreport.mbupLoad";
			topReportHeaders[4]=TOPREPORT_HEAD_TOTALS[1];
		} else if (target.equals(TOPREPORT_TARGETS[2])) {
			topReportHeaders[1]=TOPREPORT_HEAD_TOPS[2];
			topReportHeaders[4]=TOPREPORT_HEAD_TOTALS[2];
		} else if (target.equals(TOPREPORT_TARGETS[3])) {
			topReportHeaders[1]=TOPREPORT_HEAD_TOPS[3];
			topReportHeaders[4]=TOPREPORT_HEAD_TOTALS[3];
		}else if(target.equals(TOPREPORT_TARGETS[4])){
			topReportHeaders[1]=TOPREPORT_HEAD_TOPS[4];
			topReportHeaders[4]=TOPREPORT_HEAD_TOTALS[4];
		}
		return topReportHeaders;
	}
	private static final String []TOPREPORT_TITLES={
		"ui.report.topreport.url.title",
		"ui.report.topreport.user.title",
		"ui.report.topreport.servicename.title",
		"ui.report.topreport.application.title",
		"ui.report.topreport.vlan.title"
	};
	public String getTopReportTitle(final String target){
		String title=StringUtils.EMPTY;
		if (target.equals(TOPREPORT_TARGETS[0])) {
			title=TOPREPORT_TITLES[0];
		} else if (target.equals(TOPREPORT_TARGETS[1])) {
			title=TOPREPORT_TITLES[1];
		} else if (target.equals(TOPREPORT_TARGETS[2])) {
			title=TOPREPORT_TITLES[2];
		} else if (target.equals(TOPREPORT_TARGETS[3])) {
			title=TOPREPORT_TITLES[3];
		}else if(target.equals(TOPREPORT_TARGETS[4])){
			title=TOPREPORT_TITLES[4];
		}
		return title;
	}
}
