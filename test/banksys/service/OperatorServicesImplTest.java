package banksys.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.persistence.account.AccountDAO;
import banksys.persistence.account.AccountInMemoryDAO;
import banksys.persistence.client.ClientDAO;
import banksys.persistence.client.ClientInMemoryDAO;
import banksys.persistence.operator.OperatorDAO;
import banksys.persistence.operator.OperatorInMemoryDAO;
import banksys.service.exception.OperationServiceException;
import banksys.model.AccountType;
import banksys.model.Operator;

public class OperatorServicesImplTest {
	private ClientDAO clientDAO;
	private AccountDAO accountDAO;
	private OperatorDAO operatorDAO;
	private OperatorServicesImpl operatorServices;
	@Before
	public void setUp() throws Exception {
		clientDAO = new ClientInMemoryDAO();
		accountDAO = new AccountInMemoryDAO();
		operatorDAO = new OperatorInMemoryDAO();
		operatorServices = new OperatorServicesImpl(clientDAO, accountDAO, operatorDAO);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoNewClient() {
		
	}

	@Test
	public void testDoDeleteClient() {
		
	}

	@Test
	public void testDoRetrieveClient() {
		
	}

	@Test
	public void testDoLogin() {
		
	}

	@Test
	public void testDoEarnInterest() {
		AccountType act = AccountType.SPECIAL;
		Operator op = new Operator("Operador","operador","operador");
		try {
			operatorServices.doNewClient(op, "Client", "Client", "client");
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
	public void testDoEarnBonus() {
		
	}

	@Test
	public void testDoNewAccount() {
		
	}

	@Test
	public void testDoCloseAccount() {
		
	}

	@Test
	public void testDoRetrieveAccount() {
		
	}

	@Test
	public void testFindAccountByClientId() {
		
	}

}
