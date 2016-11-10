package banksys.persistence.account;

import java.util.List;

import banksys.model.Account;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountDeletionException;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.exception.PersistenceException;

public interface AccountDAO {

	public Account create(Account account) throws AccountCreationException;

	public void delete(String number) throws AccountDeletionException;

	public Account retrieve(String number) throws AccountNotFoundException;
	
	public void update(Account account) throws AccountNotFoundException;

	public List<Account> list() throws PersistenceException;
	
	public List<Account> findByClientId(Double clientId) throws PersistenceException;
	
	public int numberOfAccounts() throws PersistenceException;
}
