package banksys.persistence.account;

import java.util.ArrayList;
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
		
		int currentId = Integer.parseInt(ACCOUNT_IDS);
		
		int nextId = currentId + 1;
		
		ACCOUNT_IDS = String.valueOf(nextId);
		
		return String.valueOf(currentId);
		
	}
	
	@Override
	public Account create(Account account) throws AccountCreationException{
		
		account.setNumber(nextId());
		
		accounts.add(account);
		
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
		
		for (Account account : accounts) {
			
			if (account.getNumber().equals(number)) {
				return account;
			}
			
		}
		
		throw new AccountNotFoundException("Account " + number + " not found.");
		
	}
	
	@Override
	public void update(Account account) throws AccountNotFoundException {
		
		for (Account acc : accounts) {
			
			if (acc.equals(account)) {
				
				acc.setBalance(account.getBalance());
				acc.setBonus(account.getBonus());
				acc.setClientId(account.getClientId());
				acc.setNumber(account.getNumber());
				acc.setType(account.getType());
				
				break;
				
			}
			
		}
		
	}

	@Override
	public List<Account> list() throws PersistenceException {
		
		if (AccountInMemoryDAO.accounts.size() == 0) {
			throw new PersistenceException("Not existing account list!");
		}
		
		List<Account> listAccounts = new ArrayList<Account>();
		
		listAccounts.addAll(accounts);
		
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
			if(aux.getClientId() == clientId.doubleValue())
				accountsById.add(aux);
		}
		return accountsById;
	}

}
