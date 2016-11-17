package banksys.persistence.account;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import banksys.model.Account;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountDeletionException;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.exception.PersistenceException;

public class AccountInMemoryDAO implements AccountDAO {
	
	private static String ACCOUNT_IDS = "1";
	private static List<Account> accounts = new ArrayList<Account>();

	private static String nextId() {
		return String.valueOf(Integer.parseInt(ACCOUNT_IDS) + 1);
	}
	
	@Override
	public Account create(Account account) throws AccountCreationException{
		
		if (accounts.contains(account)) {
			
			throw new AccountCreationException("Account already exists: " +
					account.getNumber());
			
		}
		
		account.setNumber(nextId());
		AccountInMemoryDAO.accounts.add(account);
		
		return account;
		
	}

	@Override
	public void delete(String number) throws AccountDeletionException {
		Account acc = findByNumber(number);
		if (acc != null) {
			AccountInMemoryDAO.accounts.remove(acc);
		} else {
			throw new AccountDeletionException("Account number " + number + " not found!");
		}
	}

	private Account findByNumber(String number) {
		for (Account acc : AccountInMemoryDAO.accounts) {
			if (acc.getNumber() == number) {
				return acc;
			}
		}
		return null;
	}

	@Override
	public Account retrieve(String number) throws AccountNotFoundException {
		for(Iterator<Account> iterator = accounts.iterator(); iterator.hasNext(); ) {
			Account account = iterator.next();
			
			if (account.getNumber().equals(number)) {
				return account;
			}
		}
		
		throw new AccountNotFoundException("Account " + number + " not found.");
	}
	
	@Override
	public void update(Account account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Account> list() throws PersistenceException {
		if(AccountInMemoryDAO.accounts.size()==0){
			throw new PersistenceException("Not existing account list!");
		}
		List<Account> listAccounts = AccountInMemoryDAO.accounts;
		return listAccounts;
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
