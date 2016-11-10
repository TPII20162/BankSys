package banksys.persistence.client.exception;

import banksys.persistence.exception.PersistenceException;

public class ClientCreationException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public ClientCreationException(String message) {
		super(message);
	}
}
