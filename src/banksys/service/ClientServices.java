package banksys.service;

import java.util.List;

import banksys.model.Account;
import banksys.model.Client;
import banksys.service.exception.ClientServiceException;

public interface ClientServices {
	
	public void doCredit(Account account, Double amount) throws ClientServiceException;

	public void doDebit(Account account, Double amount) throws ClientServiceException;

	public void doTransfer(Account sourceAccount, Account targetAccount, Double amount) throws ClientServiceException;
	
	public Double doRetrieveBalance(Client client, String accountNumber) throws ClientServiceException;
	
	public List<Account> doRetrieveAllClientAccounts(Client client) throws ClientServiceException;

	public Client doLogin(String username, String password) throws ClientServiceException;
	
	public Account retriveAccount(String accountNumer) throws ClientServiceException;
}
