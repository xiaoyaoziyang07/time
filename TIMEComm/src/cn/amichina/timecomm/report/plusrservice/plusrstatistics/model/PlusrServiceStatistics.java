package cn.amichina.timecomm.report.plusrservice.plusrstatistics.model;
/**
 * 
 * Create by 石磊  on 2015年8月17日 上午11:56:32
 *
 *各种用户状态统计,每日进行统计
 */
public class PlusrServiceStatistics {
	/**
	 * 日期
	 */
	private String stat1;
	/**
	 * active激活数量
	 */
	private Long stat2;
	/**
	 * active激活数量最大
	 */
	private Long inServiceMax;
	/**
	 * active激活数量最小
	 */
	private Long inServiceMin;
	/**
	 * suspend数量
	 */
	private Long stat3;
	/**
	 * suspend数量
	 */
	private Long thottlingMax;
	/**
	 * suspend数量
	 */
	private Long thottlingMin;
	/**
	 * un-suspend数量
	 */
	private Long onlineNumMax;
	/**
	 * un-suspend数量
	 */
	private Long onlineNumMin;
	/**
	 * 退网用户
	 */
	private Long stat5;
	/**
	 * 退网用户
	 */
	private Long freeboostMax;
	/**
	 * 退网用户
	 */
	private Long freeboostMin;
	
	/**
	 * 退网用户
	 */
	private Long grossAdd;
	
	public Long getInServiceMax() {
		return inServiceMax;
	}
	public void setInServiceMax(Long inServiceMax) {
		this.inServiceMax = inServiceMax;
	}
	public Long getInServiceMin() {
		return inServiceMin;
	}
	public void setInServiceMin(Long inServiceMin) {
		this.inServiceMin = inServiceMin;
	}
	public Long getThottlingMax() {
		return thottlingMax;
	}
	public void setThottlingMax(Long thottlingMax) {
		this.thottlingMax = thottlingMax;
	}
	public Long getThottlingMin() {
		return thottlingMin;
	}
	public void setThottlingMin(Long thottlingMin) {
		this.thottlingMin = thottlingMin;
	}
	public Long getOnlineNumMax() {
		return onlineNumMax;
	}
	public void setOnlineNumMax(Long onlineNumMax) {
		this.onlineNumMax = onlineNumMax;
	}
	public Long getOnlineNumMin() {
		return onlineNumMin;
	}
	public void setOnlineNumMin(Long onlineNumMin) {
		this.onlineNumMin = onlineNumMin;
	}
	public Long getFreeboostMax() {
		return freeboostMax;
	}
	public void setFreeboostMax(Long freeboostMax) {
		this.freeboostMax = freeboostMax;
	}
	public Long getFreeboostMin() {
		return freeboostMin;
	}
	public void setFreeboostMin(Long freeboostMin) {
		this.freeboostMin = freeboostMin;
	}
	public Long getPayboostMax() {
		return payboostMax;
	}
	public void setPayboostMax(Long payboostMax) {
		this.payboostMax = payboostMax;
	}
	public Long getPayboostMin() {
		return payboostMin;
	}
	public void setPayboostMin(Long payboostMin) {
		this.payboostMin = payboostMin;
	}
	public Long getTopupMax() {
		return topupMax;
	}
	public void setTopupMax(Long topupMax) {
		this.topupMax = topupMax;
	}
	public Long getTopupMin() {
		return topupMin;
	}
	public void setTopupMin(Long topupMin) {
		this.topupMin = topupMin;
	}
	private Long payboostMax;
	private Long payboostMin;
	private Long topupMax;
	private Long topupMin;
	private Long stat6;
	private Long stat7;
	private Long stat8;
	private Long stat9;
	private Long stat10;
	private Long stat11;
	/**
	 * un-suspend数量
	 */
	private Long stat4;	
	/**
	 * 退网用户
	 * 
	 */
	private Long netGross;
	public Long getStat6() {
		return stat6;
	}
	public void setStat6(Long stat6) {
		this.stat6 = stat6;
	}
	public Long getStat7() {
		return stat7;
	}
	public void setStat7(Long stat7) {
		this.stat7 = stat7;
	}
	public Long getStat8() {
		return stat8;
	}
	public void setStat8(Long stat8) {
		this.stat8 = stat8;
	}
	public Long getStat9() {
		return stat9;
	}
	public void setStat9(Long stat9) {
		this.stat9 = stat9;
	}
	public Long getStat10() {
		return stat10;
	}
	public void setStat10(Long stat10) {
		this.stat10 = stat10;
	}
	public Long getStat11() {
		return stat11;
	}
	public void setStat11(Long stat11) {
		this.stat11 = stat11;
	}
	
	public Long getGrossAdd() {
		return grossAdd;
	}
	public void setGrossAdd(Long grossAdd) {
		this.grossAdd = grossAdd;
	}
	public Long getNetGross() {
		return netGross;
	}
	public void setNetGross(Long netGross) {
		this.netGross = netGross;
	}
	public String getStat1() {
		return stat1;
	}
	public void setStat1(String stat1) {
		this.stat1 = stat1;
	}
	public Long getStat2() {
		return stat2;
	}
	public void setStat2(Long stat2) {
		this.stat2 = stat2;
	}
	public Long getStat3() {
		return stat3;
	}
	public void setStat3(Long stat3) {
		this.stat3 = stat3;
	}
	public Long getStat4() {
		return stat4;
	}
	public void setStat4(Long stat4) {
		this.stat4 = stat4;
	}
	public Long getStat5() {
		return stat5;
	}
	public void setStat5(Long stat5) {
		this.stat5 = stat5;
	}
	public void iniMaxAndMin(){
		if (this.stat6 != null) {
			this.inServiceMax = stat6;
			this.inServiceMin = stat6;
		} else {
			this.inServiceMax = 0l;
			this.inServiceMin = 0l;
		}

		if (this.stat7 != null) {
			this.thottlingMax = stat7;
			this.thottlingMin = stat7;
		} else {
			this.thottlingMax = 0l;
			this.thottlingMin = 0l;
		}

		if (this.stat8 != null) {
			this.onlineNumMax = stat8;
			this.onlineNumMin = stat8;
		} else {
			this.onlineNumMax = 0l;
			this.onlineNumMin = 0l;
		}

		if (this.stat9 != null) {
			this.freeboostMax = stat9;
			this.freeboostMin = stat9;
		} else {
			this.freeboostMax = 0l;
			this.freeboostMin = 0l;
		}

		if (this.stat10 != null) {
			this.payboostMax = stat10;
			this.payboostMin = stat10;
		} else {
			this.payboostMax = 0l;
			this.payboostMin = 0l;
		}

		if (this.stat11 != null) {
			this.topupMax = stat11;
			this.topupMin = stat11;
		} else {
			this.topupMax = 0l;
			this.topupMin = 0l;
		}
	}

	@Override
	public String toString() {
		return "PlusrServiceStatistics [stat1=" + stat1 + ", stat2=" + stat2
				+ ", inServiceMax=" + inServiceMax + ", inServiceMin="
				+ inServiceMin + ", stat3=" + stat3 + ", thottlingMax="
				+ thottlingMax + ", thottlingMin=" + thottlingMin
				+ ", onlineNumMax=" + onlineNumMax + ", onlineNumMin="
				+ onlineNumMin + ", stat5=" + stat5 + ", freeboostMax="
				+ freeboostMax + ", freeboostMin=" + freeboostMin
				+ ", grossAdd=" + grossAdd + ", payboostMax=" + payboostMax
				+ ", payboostMin=" + payboostMin + ", topupMax=" + topupMax
				+ ", topupMin=" + topupMin + ", stat6=" + stat6 + ", stat7="
				+ stat7 + ", stat8=" + stat8 + ", stat9=" + stat9 + ", stat10="
				+ stat10 + ", stat11=" + stat11 + ", stat4=" + stat4
				+ ", netGross=" + netGross + "]";
	}
	public void compare(PlusrServiceStatistics pss) {
			if(pss==null)return;
			if (pss.getStat6() != null) {
				if (pss.getStat6() > this.inServiceMax) {
					this.inServiceMax = pss.getStat6();
				}
				if (pss.getStat6() < this.inServiceMin) {
					this.inServiceMin = pss.getStat6();
				}
			}else{
				this.inServiceMin = 0l;
			}
			//
			if (pss.getStat7() != null) {
				if (pss.getStat7() > this.thottlingMax) {
					this.thottlingMax = pss.getStat7();
				}
				if (pss.getStat7() < this.thottlingMin) {
					this.thottlingMin = pss.getStat7();
				}
			}else{
				this.thottlingMin =0l;
			}
			//
			if (pss.getStat8() != null) {
				if (pss.getStat8() > this.onlineNumMax) {
					this.onlineNumMax = pss.getStat8();
				}
				if (pss.getStat8() < this.onlineNumMin) {
					this.onlineNumMin = pss.getStat8();
				}
			}else{
				this.onlineNumMin =0l;
			}
			//
			if (pss.getStat9() != null) {
				if (pss.getStat9() > this.freeboostMax) {
					this.freeboostMax = pss.getStat9();
				}
				if (pss.getStat9() < this.freeboostMin) {
					this.freeboostMin = pss.getStat9();
				}
			}else{
				this.freeboostMin =0l;
			}
			//
			if (pss.getStat10() != null) {
				if (pss.getStat10() > this.payboostMax) {
					this.payboostMax = pss.getStat10();
				}
				if (pss.getStat10() < this.payboostMin) {
					this.payboostMin = pss.getStat10();
				}
			}else{
				this.payboostMin =0l;
			}
			//
			if (pss.getStat10() != null) {
				if (pss.getStat11() > this.topupMax) {
					this.topupMax = pss.getStat11();
				}
				if (pss.getStat11() < this.topupMin) {
					this.topupMin = pss.getStat11();
				}
			}else{
				this.topupMin =0l;
			}
	}
}
