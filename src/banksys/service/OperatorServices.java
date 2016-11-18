package banksys.service;

import java.util.List;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.model.Client;
import banksys.model.Operator;
import banksys.service.exception.OperationServiceException;

public interface OperatorServices {

	public Client doNewClient(Operator operator, String fullName, String username, String password, String confirmationPassword) throws OperationServiceException;

	public void doDeleteClient(Operator operator, Double clientId) throws OperationServiceException;

	public Client doRetrieveClient(Operator operator, Double clientId) throws OperationServiceException;
	
	public Operator doLogin(String username, String password) throws OperationServiceException;

	public void doEarnInterest(Operator operator, String accountNumber) throws OperationServiceException;

	public void doEarnBonus(Operator operator, String accountNumber) throws OperationServiceException;
	
	public Account doNewAccount(Operator operator, Double clientId, AccountType accountType) throws OperationServiceException;

	public void doCloseAccount(Operator operator, String accountNumber) throws OperationServiceException;

	public Account doRetrieveAccount(Operator operator, String accountNumber) throws OperationServiceException;

	public List<Account> findAccountByClientId(Operator operator, Double clientId) throws OperationServiceException;

}
