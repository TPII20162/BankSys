package banksys.persistence.account;

import java.util.List;

import banksys.model.Account;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountDeletionException;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.exception.PersistenceException;

public class AccountInMemoryDAO implements AccountDAO {

	@Override
	public Account create(Account account) throws AccountCreationException {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public void delete(String number) throws AccountDeletionException {
		// TODO Auto-generated method stub
	}

	@Override
	public Account retrieve(String number) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(Account account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Account> list() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfAccounts() throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Account> findByClientId(Double clientId) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}
