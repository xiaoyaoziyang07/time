package cn.amichina.timecomm.sysservices.model;


/**
 * 
 * Create by 王鑫  on 2015年9月8日 下午3:20
 *
 */

public class SysServices {
	
	private String id;
	private String name;
	private String content;
	private Integer type;
	private Integer serversign;
	private Long time_stamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getServersign() {
		return serversign;
	}
	public void setServersign(Integer serversign) {
		this.serversign = serversign;
	}
	public Long getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(Long time_stamp) {
		this.time_stamp = time_stamp;
	}
	
	public SysServices(){}
	
	public SysServices(String id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "SysServices [id=" + id + ", name=" + name + ", content="
				+ content + ", type=" + type + ", serversign=" + serversign
				+ ", time_stamp=" + time_stamp + "]";
	}

}
