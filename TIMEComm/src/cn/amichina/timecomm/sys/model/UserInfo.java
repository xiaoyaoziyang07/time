package cn.amichina.timecomm.sys.model;
/**
 * 
 * Create by 石磊 on 2015年7月28日 下午11:00:25
 * 用户实体
 */
public class UserInfo {
	/**
	 * 用户ID
	 */
	private String id;
	/**
	 * 用户名
	 */
	private String account;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 用户名称
	 */
	private String username;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * TODO unit 不理解
	 */
	private String unit;
	/**
	 * 用户手机号
	 */
	private String phone;
	/**
	 * 所属集团列表
	 */
	private String groupList;
	/**
	 * 所属部门
	 */
	private String department;
	/**
	 * TODO 不懂
	 */
	private String time_stamp;
	/**
	 * TODO 不懂
	 */
	private String blocid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGroupList() {
		return groupList;
	}
	public void setGroupList(String groupList) {
		this.groupList = groupList;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	public String getBlocid() {
		return blocid;
	}
	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", account=" + account + ", pwd=" + pwd
				+ ", username=" + username + ", email=" + email + ", unit="
				+ unit + ", phone=" + phone + ", groupList=" + groupList
				+ ", department=" + department + ", time_stamp=" + time_stamp
				+ ", blocid=" + blocid + "]";
	}
}
