package cn.amichina.timecomm.sys.model;
/**
 * 
 * Create by 石磊 on 2015年7月28日 下午11:15:23 
 * 权限实体类
 */
public class RoleInfo {
	/**
	 * 权限ID
	 */
	private Integer id;
	/**
	 * 权限名称
	 */
	private String rolename;
	/**
	 * 描述
	 */
	private String des;
	/**
	 * 权限可见菜单
	 */
	private String groupright;

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public String toString() {
		return "RoleInfo [id=" + id + ", rolename=" + rolename + ", des=" + des
				+ ", groupright=" + groupright + "]";
	}

	public String getGroupright() {
		return groupright;
	}

	public void setGroupright(String groupright) {
		this.groupright = groupright;
	}
}
