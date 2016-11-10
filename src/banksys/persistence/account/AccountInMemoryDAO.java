package banksys.persistence.account;

import java.util.ArrayList;
import java.util.List;

import banksys.model.Account;
import banksys.model.Client;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountDeletionException;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.exception.PersistenceException;

public class AccountInMemoryDAO implements AccountDAO {

	private static List<Account> accounts = new ArrayList<Account>();

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
		return accounts.size();
	}

	@Override
	public List<Account> findByClientId(Double clientId) throws PersistenceException {
		ArrayList<Account> accountsById = new ArrayList<Account>();
		for(Account aux : accounts){
			if(aux.getClientId() == clientId)
				accountsById.add(aux);
		}
		return accountsById;
	}

}
