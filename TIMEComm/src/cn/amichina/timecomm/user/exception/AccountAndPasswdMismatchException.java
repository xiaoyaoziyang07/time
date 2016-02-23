package cn.amichina.timecomm.user.exception;

@SuppressWarnings("serial")
public class AccountAndPasswdMismatchException extends Exception {

	public AccountAndPasswdMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountAndPasswdMismatchException(String message) {
		super(message);
	}

	public AccountAndPasswdMismatchException(Throwable cause) {
		super(cause);
	}

}
