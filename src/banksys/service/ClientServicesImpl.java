package banksys.service;

import java.util.List;

import banksys.model.Account;
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
		

	}

	@Override
	public void doDebit(Client client, String accountNumber, Double amount) throws ClientServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doTransfer(Client client, String sourceAccountNumber, String targetAccountNumber, Double amount)
			throws ClientServiceException {
		// TODO Auto-generated method stub

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
