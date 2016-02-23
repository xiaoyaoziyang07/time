package cn.amichina.timecomm.apistatus.model;

/**
 * 
 * Create by 石磊 on 2015年8月20日 下午3:16:03 日志类型
 */
public class Notelog {
	/**
	 * id
	 */
	private Integer num;
	/**
	 * 用户id
	 */
	private String user_id;
	/**
	 *  原因
	 */
	private String reason;
	/**
	 * 生成时间
	 */
	private Long time_stamp;
	/**
	 * 错误代码
	 */
	private String resultcode;
	/**
	 * 详细信息
	 */
	private String details;

	@Override
	public String toString() {
		return "Notelog [num=" + num + ", user_id=" + user_id + ", reason="
				+ reason + ", time_stamp=" + time_stamp + ", resultcode="
				+ resultcode + ", details=" + details + "]";
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Long time_stamp) {
		this.time_stamp = time_stamp;
	}

}
