package banksys.service.exception;

public class OperationServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public OperationServiceException() {
		super();
	}

	public OperationServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OperationServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationServiceException(String message) {
		super(message);
	}

	public OperationServiceException(Throwable cause) {
		super(cause);
	}

}
