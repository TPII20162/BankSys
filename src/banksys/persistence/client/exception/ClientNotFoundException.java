package banksys.persistence.client.exception;

import banksys.persistence.exception.PersistenceException;

public class ClientNotFoundException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public ClientNotFoundException(String message) {
		super(message);
	}

}
