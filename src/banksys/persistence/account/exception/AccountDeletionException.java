package banksys.persistence.account.exception;

import banksys.persistence.exception.PersistenceException;

public class AccountDeletionException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public AccountDeletionException(String message) {
		super(message);
	}
}
