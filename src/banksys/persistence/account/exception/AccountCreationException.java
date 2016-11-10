package banksys.persistence.account.exception;

import banksys.persistence.exception.PersistenceException;

public class AccountCreationException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public AccountCreationException(String message) {
		super(message);
	}
}
