package banksys.service;

import java.util.List;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.model.Client;
import banksys.persistence.account.AccountDAO;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.client.ClientInMemoryDAO;
import banksys.persistence.client.exception.ClientNotFoundException;
import banksys.persistence.exception.PersistenceException;
import banksys.service.exception.ClientServiceException;

public class ClientServicesImpl implements ClientServices {

	private AccountDAO accountDAO;

	public ClientServicesImpl(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	@Override
	public void doCredit(Account account, Double amount) throws ClientServiceException {
		if(amount <= 0) {
			throw new ClientServiceException("Error: The amount must be 0 or higher.");
		}
			
		if(account.getType() == AccountType.SPECIAL) {
			double currentBonus = account.getBonus();
			account.setBonus(currentBonus + (amount * 0.01));
		}
		
		double currentBalance = account.getBalance();
		
		account.setBalance(currentBalance + amount);
		
		try {
			this.accountDAO.update(account);
		} catch (AccountNotFoundException e) {
			throw new ClientServiceException("Error: Account of number " + account.getNumber() + " not found.");
		}
	}

	@Override
	public void doDebit(Account account, Double amount) throws ClientServiceException {
		if(amount <= 0) {
			throw new ClientServiceException("Error: The amount must be 0 or higher.");
		}
		
		try {
			double currentBalance = account.getBalance();
			
			if(account.getType() == AccountType.TAX) {
				amount += (amount * 0.001);
			}
				
			if(currentBalance - amount >= 0 ) {					
				account.setBalance(currentBalance - amount);
					
				this.accountDAO.update(account);					
			} else {
				throw new ClientServiceException("Error: The balance after the Debit Operation must be 0 or higher.");
			}
		} catch (AccountNotFoundException e) {
			throw new ClientServiceException("Error: Account of number " + account.getNumber() + " not found.");
		}
	}

	@Override
	public void doTransfer(Account sourceAccount, Account targetAccount, Double amount) throws ClientServiceException {
		try {
			// Verificar se a conta alvo existe antes de fazer o debito na conta fonte
			this.accountDAO.retrieve(targetAccount.getNumber());
			
			this.doDebit(sourceAccount, amount);
			this.doCredit(targetAccount, amount);
		} catch (AccountNotFoundException e) {
			throw new ClientServiceException("Error: Account of number " + targetAccount.getNumber() + " not found.");
		} catch (ClientServiceException e) {
			throw e;			
		}
	}

	@Override
	public Client doLogin(String username, String password) throws ClientServiceException {
		

		ClientInMemoryDAO clientInMemoryDAO = new ClientInMemoryDAO();
		
		try {
			return clientInMemoryDAO.retrieveByUsernameAndPassword(username, password);
		} catch (ClientNotFoundException onf) {
			throw new ClientServiceException("Error: Username/password invalid!", onf);
		}

	}

	@Override
	public Double doRetrieveBalance(Client client, String accountNumber) throws ClientServiceException {
		Double balance = null;
		Account account;
		try {
			account = accountDAO.retrieve(accountNumber);
			balance = account.getBalance();
			return balance;
		} catch (AccountNotFoundException e) {
			throw new ClientServiceException(e);
		}
	}

	@Override
	public List<Account> doRetrieveAllClientAccounts(Client client) throws ClientServiceException {
		try {
			return accountDAO.findByClientId(client.getId());
		} catch (PersistenceException e) {
			throw new ClientServiceException(e);
		}		
	}

	@Override
	public Account retriveAccount(String accountNumber) throws
			ClientServiceException {
		
		Account account = null;
		
		try {
			account = accountDAO.retrieve(accountNumber);
		} catch (AccountNotFoundException e) {
			throw new ClientServiceException(e);
		}
		
		return account;
		
	}
	
}
