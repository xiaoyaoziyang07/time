package cn.amichina.timecomm.report.plusrservice.plusrstatistics.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.report.plusrservice.plusrstatistics.dao.PlusrServiceStatisticsDao;
import cn.amichina.timecomm.report.plusrservice.plusrstatistics.model.PlusrServiceStatistics;
import cn.amichina.timecomm.util.DateUtil;

/**
 * 
 * Create by 石磊  on 2015年11月2日 下午4:31:37
 *
 */
@Service
public class PlusrServiceStatisticsService {
@Resource
private PlusrServiceStatisticsDao plusrServiceStatisticsDao;
private static final int MAX_DAYS_2_HOUR =7;
	/**
	 * 套餐报表
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<PlusrServiceStatistics> getPlusrServiceStatisticesByStartDateAndEndDate(Date startDate,Date endDate) throws Exception{
		Set<Date> dates =new LinkedHashSet<Date>(1);
		for (Date tmpDate=new Date(startDate.getTime()) ;tmpDate.getTime()<=endDate.getTime(); tmpDate=DateUtil.add(tmpDate,Calendar.DATE,1)) {
			dates.add(tmpDate);
		}
		List<PlusrServiceStatistics> list =null;
		if (dates.size() <= MAX_DAYS_2_HOUR) {
			list  = getHourData(dates);
		} else {
			list = getDayData(dates);
		}
		if(list!=null&&list.size()>0){
				list.get(0).iniMaxAndMin();
				for (int i = 1; i < list.size(); i++) {
					if(list.get(i)!=null){
						list.get(0).compare(list.get(i));
					}
				}
		}
		PlusrServiceStatistics pps =list.get(0);
		
		pps.setFreeboostMax(getRatioNum(pps.getFreeboostMax(),pps.getFreeboostMin()));
		
		pps.setInServiceMax(getRatioNum(pps.getInServiceMax(),pps.getInServiceMin()));
		
		pps.setThottlingMax(getRatioNum(pps.getThottlingMax(), pps.getThottlingMin()));
		
		pps.setOnlineNumMax(getRatioNum(pps.getOnlineNumMax(), pps.getOnlineNumMin()));
		
		pps.setPayboostMax(getRatioNum(pps.getPayboostMax(),pps.getPayboostMin()));
		
		pps.setThottlingMax(getRatioNum(pps.getTopupMax(),pps.getTopupMax()));
		
		return list;
	}

	private List<PlusrServiceStatistics> getDayData(
			Set<Date> dates) {
		List<PlusrServiceStatistics> list = new ArrayList<PlusrServiceStatistics>(1);
		for (Date date : dates) {
			PlusrServiceStatistics plusrServiceStatistics = plusrServiceStatisticsDao.getPlusrServiceStatisticesByStar1(date);
			if (plusrServiceStatistics != null) {
				plusrServiceStatistics.setGrossAdd(plusrServiceStatistics.getStat2()+ plusrServiceStatistics.getStat3()+ plusrServiceStatistics.getStat4()+ plusrServiceStatistics.getStat5());
				plusrServiceStatistics.setNetGross(plusrServiceStatistics.getStat2() + plusrServiceStatistics.getStat4());
			} else {
				plusrServiceStatistics = new PlusrServiceStatistics();
			}
			plusrServiceStatistics.setStat1(DateUtil.toStr(date,DateUtil.EN_DATA_FORMAT_MM_DD_YYYY));
			list.add(plusrServiceStatistics);
		}
		return list;
	}
	private List<PlusrServiceStatistics> getHourData(Set<Date> dates) {
		List<PlusrServiceStatistics> allList = new ArrayList<PlusrServiceStatistics>(0);
		for (Date date : dates) {
			List<PlusrServiceStatistics> list = plusrServiceStatisticsDao.getPlusrServiceStatisticesHourDataByStar1(date);
			for (int i = 0; i < 24; i++) {
				int index=-1;
				for (int x = 0; x < list.size(); x++) {
					if(list.get(x).getStat1().equals(DateUtil.toDBStr(date)+(String.format("%02d", i)+"0000"))){
						index=x;
					}
				}
				if (index == -1) {
					if(list.size()>i){
						list.set(i, new PlusrServiceStatistics());
					}else{
						list.add(new PlusrServiceStatistics());
					}
				}
				String lable = DateUtil.toStr(date,DateUtil.EN_DATA_FORMAT_MM_DD);
				list.get(i).setStat1(lable+String.format(" %02d:00", i+1));
			}
			for (PlusrServiceStatistics  pss: list) {
				long netgross =0;
				if(pss.getStat2()!=null)netgross+=pss.getStat2();
				if(pss.getStat4()!=null)netgross+=pss.getStat4();
				pss.setNetGross(netgross);
				 long grossadd = netgross;
				 if(pss.getStat3()!=null)grossadd+=pss.getStat3();
				 if(pss.getStat5()!=null)grossadd+=pss.getStat5();
				pss.setGrossAdd(grossadd);
			}
			allList.addAll(list);
		}
		return allList;
	}
	public static final double num =0.2;

	private Long getRatioNum(Long max, Long min) {
		if (max == null)
			max = 0l;
		if (min == null)
			min = 0l;
		Double tm = (max - min) * num;
		Long result = min - tm.longValue();
		return result < 0 ? 0 : result;
	}
}

