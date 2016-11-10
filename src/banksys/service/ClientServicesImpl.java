package banksys.service;

import java.util.List;

import banksys.model.Account;
import banksys.model.Client;
import banksys.persistence.account.AccountDAO;
<<<<<<< HEAD
import banksys.persistence.account.exception.AccountNotFoundException;
=======
import banksys.persistence.exception.PersistenceException;
>>>>>>> 2397a1b91de0049355f25aa8944f11a059f2a3d2
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double doRetrieveBalance(Client client, String accountNumber) throws ClientServiceException {
		return null;
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
