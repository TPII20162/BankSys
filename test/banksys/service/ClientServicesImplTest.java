package banksys.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

public class ClientServicesImplTest {
	
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
	}

	@Test
	public void testDoRetrieveAllAccounts() {

		try {
			Client createdClient = operatorServices.doNewClient(operator, "FullName", "username", "password", "password");
			Account createdOrdinaryAccount = operatorServices.doNewAccount(operator, createdClient.getId(),
					AccountType.ORDINARY);
			Account createdSpecialAccount = operatorServices.doNewAccount(operator, createdClient.getId(),
					AccountType.SPECIAL);

			List<Account> retrievedAccounts = clientServices.doRetrieveAllClientAccounts(createdClient);
			
			assertEquals(2, retrievedAccounts.size());
			
			assertEquals(true, retrievedAccounts.contains(createdOrdinaryAccount));
			assertEquals(true, retrievedAccounts.contains(createdSpecialAccount));
			
			
			
		} catch (OperationServiceException | ClientServiceException e) {
			fail("Error: Couldn'd find client accounts.");
		}

	}
	
	@Test
	public  void doCreditTest() throws OperationServiceException, ClientServiceException{
		
		Client createdClient = operatorServices.doNewClient(operator, "FullName", "username", "password", "password");
		Account createdOrdinaryAccount = operatorServices.doNewAccount(operator, createdClient.getId(),
				AccountType.ORDINARY);
		
		clientServices.doCredit(new Client("FullName", "username", "password"), createdOrdinaryAccount.getNumber(), 50.);
		
		assertEquals(00,50,createdOrdinaryAccount.getBalance());
		
	}
	
	
	@Test
	public void doDebitTest() throws OperationServiceException, ClientServiceException{
		Client createdClient = operatorServices.doNewClient(operator, "FullName", "username", "password", "password");
		Account createdOrdinaryAccount = operatorServices.doNewAccount(operator, createdClient.getId(),
				AccountType.ORDINARY);
	
		clientServices.doCredit(new Client("FullName", "username", "password"), createdOrdinaryAccount.getNumber(), 50.);
		
		clientServices.doDebit(createdClient, createdOrdinaryAccount.getNumber(), 30.);
		
		assertEquals(00,20.,createdOrdinaryAccount.getBalance());
		
	}
}
