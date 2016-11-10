package banksys.persistence.operator.exception;

import banksys.persistence.exception.PersistenceException;

public class OperatorCreationException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public OperatorCreationException(String message) {
		super(message);
	}
}
