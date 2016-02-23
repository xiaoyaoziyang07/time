package cn.amichina.timecomm.report.sertj.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.group.model.ServiceGroup;
import cn.amichina.timecomm.report.model.SertjReport;
import cn.amichina.timecomm.report.sertj.dao.SertjDao;
import cn.amichina.timecomm.util.DateUtil;
/**
 * 
 * Create by 石磊 on 2015年8月4日 上午12:18:56
 *
 */
@Service
public class SertjService {
	@Resource
	private SertjDao sertjDao ;
	/**
	 * 根据用户Id，分组， 获取各个协议组的流量使用
	 * @param groupList
	 * @param internal_host
	 * @param date
	 * @return
	 */
	private  Map<ServiceGroup,SertjReport> getSertjReport(List<ServiceGroup> groupList,String internal_host,Date date){
		Map<ServiceGroup,SertjReport>  map =new HashMap<ServiceGroup,SertjReport>();
		for (ServiceGroup serviceGroup : groupList) {
			SertjReport  sertjReportList =null;
			if(serviceGroup.getGroupname().equals("Date")){
				sertjReportList =new SertjReport();
				sertjReportList.setDate(date);
			}else if(serviceGroup.getGroupname().equals("SubTotal")){
				Long total=0l;
				for (Map.Entry<ServiceGroup,SertjReport> entry : map.entrySet()) {
					SertjReport  sr =entry.getValue();
					if(sr.getInnum()==null){
						sr.setInnum(0l);
					}
					if(sr.getOutnum()==null){
						sr.setOutnum(0l);
					}
					total += sr.getInnum()+sr.getOutnum();
				}
				sertjReportList =new SertjReport();
				sertjReportList.setTotal(total);
			}else if(DateUtil.eqDate(date, new Date())){
				try {
					sertjReportList=sertjDao.getHourSertjBySJAndServiceAndInternal_host(date,serviceGroup.getContent().split(","), internal_host);
					} catch(Exception e){}
				}else{
					try {
					sertjReportList= sertjDao.getDaySertjBySJAndServiceAndInternal_host(date,serviceGroup.getContent().split(","), internal_host);
					} catch(Exception e){}
				}
			if(sertjReportList==null){
				sertjReportList=new SertjReport();
			}
				map.put(serviceGroup, sertjReportList);
			}
		return map;
	}
	/**
	 * 根据用户Id 获取各个协议组的流量使用
	 * @param groupList
	 * @param internal_host
	 * @param date
	 * @return
	 */
	public List<Map<ServiceGroup, SertjReport>> getSertjReports(List<ServiceGroup> groupList,String internal_host,Date startDate,Date endDate){
		List<Map<ServiceGroup,SertjReport>> rows =new ArrayList<Map<ServiceGroup,SertjReport>>();
		ServiceGroup dategroup =new ServiceGroup();
		dategroup.setGroupname("Date");
		if(!groupList.isEmpty()){
			groupList.set(0, dategroup);
		}
		ServiceGroup total = new ServiceGroup();
		total.setGroupname("SubTotal");
		groupList.add(total);
		for (;startDate.getTime()<=endDate.getTime(); startDate=DateUtil.add(startDate,Calendar.DATE,1)) {
			rows.add(getSertjReport(groupList,internal_host,startDate));
		}
		return rows;
	}
}


