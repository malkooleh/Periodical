package ua.nure.malko.Periodical.exception;

public class DBException extends AppException {

	private static final long serialVersionUID = 1111L;

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DBException(String message) {
		super(message);
	}

}
