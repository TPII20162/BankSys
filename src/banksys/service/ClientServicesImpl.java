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
	public void doCredit(Client client, String accountNumber, Double amount) throws ClientServiceException {

		if(amount<=0){
			throw new ClientServiceException("Error: The amount must be 0 or higher.");
		}
			
		try {
			Account account = this.accountDAO.retrieve(accountNumber);
			
			if(account.getType()==AccountType.SPECIAL){
				double currentBonus = account.getBonus();
				account.setBonus(currentBonus + (amount * 0.01));
			}
			
			double currentBalance = account.getBalance();
			
			account.setBalance(currentBalance + amount);
			
			this.accountDAO.update(account);
			
		} catch (AccountNotFoundException e) {
			throw new ClientServiceException("Error: Account of number "+accountNumber+ " not found.");
		}
		

	}

	@Override
	public void doDebit(Client client, String accountNumber, Double amount) throws ClientServiceException {
		if(amount<=0){
			throw new ClientServiceException("Error: The amount must be 0 or higher.");
		}
		
		try {
			
			Account account = this.accountDAO.retrieve(accountNumber);
			
			//No caso do credito, qualquer cliente pode creditar em qualquer conta.
			//Mas no debito apenas o dono da conta pode debitar
			if(client.getId()==account.getClientId()){
				
				double currentBalance = account.getBalance();
				
				if(account.getType()==AccountType.TAX){
					amount = amount + (amount * 0.001);
				}
				
				if(currentBalance - amount >= 0 ){					
					account.setBalance(currentBalance - amount);
					
					this.accountDAO.update(account);					
				}else{
					throw new ClientServiceException("Error: The balance after the Debit Operation must be 0 or higher.");
				}
				
			}else{
				throw new ClientServiceException("Error: Only the owner of the account can do Debits.");
			}
			
		} catch (AccountNotFoundException e) {
			throw new ClientServiceException("Error: Account of number "+accountNumber+ " not found.");
		}

	}

	@Override
	public void doTransfer(Client client, String sourceAccountNumber, String targetAccountNumber, Double amount)
			throws ClientServiceException {
		
		try {
			this.accountDAO.retrieve(targetAccountNumber);
			this.doDebit(client, sourceAccountNumber, amount);	
			this.doCredit(client, targetAccountNumber, amount);		
		}catch (AccountNotFoundException e) {
			throw new ClientServiceException("Error: Account of number "+targetAccountNumber+ " not found.");
		}catch (ClientServiceException e){
			throw e;			
		}
	}

	@Override
	public Client doLogin(String username, String password) throws ClientServiceException {
		

		ClientInMemoryDAO clientInMemoryDAO = new ClientInMemoryDAO();
		Client client = null;
		
		try {
			client = clientInMemoryDAO.retrieveByUsernameAndPassword(username, password);
		} catch (ClientNotFoundException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		
		if(client != null){
			return client;
		}else{
			return null;
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

}
