package banksys.persistence.operator.exception;

import banksys.persistence.exception.PersistenceException;

public class OperatorNotFoundException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public OperatorNotFoundException(String message) {
		super(message);
	}

}
