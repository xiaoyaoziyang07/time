package cn.amichina.timecomm.vlan.model;

/**
 * 
 * Create by 石磊  on 2015年10月12日 下午5:18:37
 *
 * Vlan 实体
 */
public class Vlan {
	/**
	 * 唯一主键
	 */
	private String vId;
	/**
	 * 名称
	 */
	private String vName;
	
	public String getvId() {
		return vId;
	}

	public void setvId(String vId) {
		this.vId = vId;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	@Override
	public String toString() {
		return "Vlan [vId=" + vId + ", vName=" + vName + "]";
	}
	
}
