package banksys.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.model.Client;
import banksys.model.Operator;
import banksys.persistence.account.AccountDAO;
import banksys.persistence.account.AccountInMemoryDAO;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.client.ClientDAO;
import banksys.persistence.client.ClientInMemoryDAO;
import banksys.persistence.operator.OperatorDAO;
import banksys.persistence.operator.OperatorInMemoryDAO;
import banksys.service.exception.ClientServiceException;
import banksys.service.exception.OperationServiceException;

public class OperatorServicesImplTest {
	
	private ClientDAO clientDAO;
	private AccountDAO accountDAO;
	private OperatorDAO operatorDAO;
	private OperatorServicesImpl operatorServices;
	private ClientServicesImpl clientServices;
	private Operator operator;
	
	@Before
	public void setUp() throws Exception {
		clientDAO = new ClientInMemoryDAO();
		accountDAO = new AccountInMemoryDAO();
		operatorDAO = new OperatorInMemoryDAO();
		operatorServices = new OperatorServicesImpl(clientDAO, accountDAO, operatorDAO);
		clientServices = new ClientServicesImpl(accountDAO);
		
		operator = new Operator("Admin", "adm", "789");
		operatorDAO.create(operator);
	}

	@After
	public void tearDown() throws Exception {
		
		int numberOfClients = clientDAO.numberOfClients();
		
		if (numberOfClients > 0) {
			
			List<Client> createdClients = clientDAO.list();
			
			for (Client client : createdClients) {
				clientDAO.delete(client.getId());
			}
		
		}
		
		int numberOfAccounts = accountDAO.numberOfAccounts();
		
		if (numberOfAccounts > 0) {
		
			List<Account> createdAccounts = accountDAO.list();
			
			for (Account account : createdAccounts) {
				accountDAO.delete(account.getNumber());
			}
		
		}
		
	}

	@Test
	public void testDoNewClientAndDoRetrieveClient() throws OperationServiceException{
		Operator op = new Operator("Operator","operator","operator");

		Client cliExpected = operatorServices.doNewClient(op, "FullName", "username", "password", "password");
		Client cliRetrieve = operatorServices.doRetrieveClient(op, cliExpected.getId());
		assertEquals("Error: Client Not Find", cliExpected.getId(), cliRetrieve.getId());

	}

	@Test(expected=OperationServiceException.class)
	public void testDoDeleteClient() throws OperationServiceException{

		Operator op = new Operator("Operator","operator","operator");
		Client cli = operatorServices.doNewClient(op, "FullName", "username", "password", "password");
		operatorServices.doDeleteClient(op, cli.getId());
		operatorServices.doRetrieveClient(op, cli.getId());
	}

	@Test
	public void testDoLogin() throws OperationServiceException{
		
		Operator opTest = null;
		
		opTest = operatorServices.doLogin("adm", "789");
		
		assertEquals(opTest.getUsername(), "adm");
		assertEquals(opTest.getPassword(), "789");
		
	}

	@Test
	public void testDoEarnInterest() {
		AccountType act = AccountType.SPECIAL;
		Operator op = new Operator("Operador","operador","operador");
		try {
			operatorServices.doNewClient(op, "Client", "Client", "client", "client");
			operatorServices.doNewAccount(op,1.0,act);
			operatorServices.doEarnInterest(op,"");
		} catch (OperationServiceException e) {
		}
	try {
		assertEquals(0,operatorServices.doRetrieveAccount(op,"").getBalance(),00.1);
	} catch (OperationServiceException e) {
	}	
}

	@Test
	public void testDoEarnBonus() throws OperationServiceException, ClientServiceException {
		double creditValue = 200.00;
		
		Client client = operatorServices.doNewClient(operator, "fullname", "username", "password","password");
		Account specialAccount = operatorServices.doNewAccount(operator, client.getId(), AccountType.SPECIAL);
		
		clientServices.doCredit(specialAccount, creditValue);
		assertEquals("Incorrect bonus after credit", creditValue * 0.01, specialAccount.getBonus(), 0.00001);
	
		operatorServices.doEarnBonus(operator, specialAccount.getNumber());
		assertEquals("Incorrect value for Earn Bonus", creditValue+(creditValue*0.01), specialAccount.getBalance(), 0.00001);
	}

	@Test
	public void testDoNewAccountAndDoRetrieveAccount() throws OperationServiceException{

		AccountType act = AccountType.SPECIAL;
		Operator op = new Operator("Operator","operator","operator");

		Client cli = operatorServices.doNewClient(op, "fullName", "username", "password","password");
		Account accExpected = operatorServices.doNewAccount(op, cli.getId(), act);
		Account accActual = operatorServices.doRetrieveAccount(op, accExpected.getNumber());
		assertEquals("Error: Account Not Find", accExpected.getNumber(), accActual.getNumber());
	}

	@Test(expected=OperationServiceException.class)
	public void testDoRetrieveAccountWhenNotExistsClient() throws OperationServiceException{

		AccountType act = AccountType.SPECIAL;
		Operator op = new Operator("Operator","operator","operator");
		Client cli = operatorServices.doNewClient(op, "fullName", "username", "password","password");
		Account accExpected = operatorServices.doNewAccount(op, cli.getId(), act);
		operatorServices.doCloseAccount(op, accExpected.getNumber());
		Account accActual = operatorServices.doRetrieveAccount(op, accExpected.getNumber());
	}

	@Test(expected=OperationServiceException.class)
	public void testDoCloseAccount() throws OperationServiceException{

		AccountType act = AccountType.ORDINARY;
		Operator op = new Operator("Operator","operator","operator");
		
		Client cli = operatorServices.doNewClient(op, "fullName", "username", "password","password");
		Account acc = operatorServices.doNewAccount(op, cli.getId(), act);
		operatorServices.doCloseAccount(op, acc.getNumber());
		operatorServices.doRetrieveAccount(op, acc.getNumber());
	}

	@Test
	public void testFindAccountByClientId() throws OperationServiceException, AccountCreationException {
		
		Operator operator = new Operator("Operator","operator","operator");
		
		Client client = operatorServices.doNewClient(operator, "fullName", "username", "password","password");
		Account createdAccount = operatorServices.doNewAccount(operator, client.getId(), AccountType.ORDINARY);
		
		List<Account> listOfAccounts = operatorServices.findAccountByClientId(operator, client.getId());
		assertEquals("There is no account", 1, listOfAccounts.size());
		
		
		Account foundAccount = listOfAccounts.get(0);
		assertNotNull(foundAccount);
		
		assertEquals(foundAccount.getClientId(), createdAccount.getClientId());
		
	}

	@Test
	public void testFindAccountByClientIdWhenNotExistsClient() throws OperationServiceException {

		Operator operator = new Operator("Operator","operator","operator");

		Client client = operatorServices.doNewClient(operator, "fullName", "username", "password","password");
		operatorServices.doDeleteClient(operator, client.getId());

		List<Account> listOfAccounts = operatorServices.findAccountByClientId(operator, client.getId());
		assertEquals("Error: Should be 0", 0, listOfAccounts.size());

	}

}
