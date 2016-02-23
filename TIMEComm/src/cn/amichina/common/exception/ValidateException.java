package cn.amichina.common.exception;

/**
 * 
 * Create by 石磊  on 2015年9月16日 上午11:29:32
 *
 * 数据校验异常
 */
public class ValidateException extends Exception {
	private static final long serialVersionUID = 5956115431745736580L;
	public ValidateException(String message) {
		super(message);
	}
	public ValidateException(Throwable throwable) {
		super(throwable);
	}

	public ValidateException(Throwable throwable, String message) {
		super(message, throwable);
	}
}
