package banksys.persistence.account.exception;

import banksys.persistence.exception.PersistenceException;

public class AccountNotFoundException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String message) {
		super(message);
	}

}
