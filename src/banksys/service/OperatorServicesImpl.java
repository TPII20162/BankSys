package banksys.service;

import java.util.List;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.model.Client;
import banksys.model.Operator;
import banksys.persistence.account.AccountDAO;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountDeletionException;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.client.ClientDAO;
import banksys.persistence.client.exception.ClientCreationException;
import banksys.persistence.client.exception.ClientDeletionException;
import banksys.persistence.client.exception.ClientNotFoundException;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.OperatorDAO;
import banksys.persistence.operator.exception.OperatorNotFoundException;
import banksys.service.exception.OperationServiceException;

public class OperatorServicesImpl implements OperatorServices {

	private ClientDAO clientDAO;

	private AccountDAO accountDAO;

	private OperatorDAO operatorDAO;

	public OperatorServicesImpl(ClientDAO clientDAO, AccountDAO accountDAO, OperatorDAO operatorDAO) {
		super();
		this.clientDAO = clientDAO;
		this.accountDAO = accountDAO;
		this.operatorDAO = operatorDAO;
	}

	@Override
	public Client doNewClient(Operator operator, String fullName, String username, String password)
			throws OperationServiceException {
		Client client = new Client(fullName, username, password);
		try {
			return this.clientDAO.create(client);
		} catch (ClientCreationException cce) {
			throw new OperationServiceException(cce);
		}
	}

	@Override
	public void doDeleteClient(Operator operator, Double clientId) throws OperationServiceException {
		List<Account> accounts = this.findAccountByClientId(operator, clientId);

		for (Account account : accounts) {
			this.doCloseAccount(operator, account.getNumber());
		}

		try {
			this.clientDAO.delete(clientId);
		} catch (ClientDeletionException cde) {
			throw new OperationServiceException(cde);
		}
	}

	@Override
	public Client doRetrieveClient(Operator operator, Double clientId) throws OperationServiceException {
		try {
			return this.clientDAO.retrieve(clientId);
		} catch (ClientNotFoundException cnfe) {
			throw new OperationServiceException("Error: Client retrieve problem!", cnfe);
		}
	}

	@Override
	public Operator doLogin(String username, String password) throws OperationServiceException {
		try {
			return this.operatorDAO.retrieveByUsernameAndPassword(username, password);
		} catch (OperatorNotFoundException onf) {
			throw new OperationServiceException("Error: Username/password invalid!", onf);
		}
	}
	
	@Override
	public void doEarnInterest(Operator operator, String accountNumber) throws OperationServiceException {
		 try {
			this.accountDAO.retrieve(accountNumber).setBalance(this.accountDAO.retrieve(accountNumber).getBalance()*0.001);
		} catch (AccountNotFoundException e) {
		}
		
	}

	@Override
	public void doEarnBonus(Operator operator, String accountNumber) throws OperationServiceException {
		
		
	}

	@Override
	public Account doNewAccount(Operator operator, Double clientId, AccountType accountType)
			throws OperationServiceException {
		try {
			return this.accountDAO.create(new Account(accountType));
		} catch (AccountCreationException ace) {
			throw new OperationServiceException("Error: Account creation problem!", ace);
		}
	}

	@Override
	public void doCloseAccount(Operator operator, String accountNumber) throws OperationServiceException {
		Account account = this.doRetrieveAccount(operator, accountNumber);
		try {
			this.accountDAO.delete(account.getNumber());
		} catch (AccountDeletionException ade) {
			throw new OperationServiceException("Error: Account close problem!", ade);
		}
	}

	@Override
	public Account doRetrieveAccount(Operator operator, String accountNumber) throws OperationServiceException {
		try {
			return this.accountDAO.retrieve(accountNumber);
		} catch (AccountNotFoundException anfe) {
			throw new OperationServiceException("Error: Account retrieve problem!", anfe);
		}
	}

	@Override
	public List<Account> findAccountByClientId(Operator operator, Double clientId) throws OperationServiceException {
		try {
			return this.accountDAO.findByClientId(clientId);
		} catch (PersistenceException pe) {
			throw new OperationServiceException("Error: Account find problem!", pe);
		}
	}
}
