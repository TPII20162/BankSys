package banksys.persistence.account;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.model.Client;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountDeletionException;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.client.ClientInMemoryDAO;
import banksys.persistence.exception.PersistenceException;

public class AccountInMemoryDAOTest {

	private AccountInMemoryDAO aim;
	private Account ac, ac2, ac3;
	
	@Before
	public void setUp() throws Exception {
		
		aim = new AccountInMemoryDAO();
		
		ac = new Account(AccountType.ORDINARY, 1.0);
		ac2 = new Account(AccountType.SAVINGS, 1.0);
		ac3 = new Account(AccountType.ORDINARY, 2.0);
		
	}

	@After
	public void tearDown() throws Exception {
		
		int numberOfAccounts = aim.numberOfAccounts();
		
		if (numberOfAccounts > 0) {
		
			String[] accountNumbers = new String[numberOfAccounts];
			
			List<Account> createdAccounts = aim.list();
			
			for (int i = 0; i < numberOfAccounts; i++) {
				accountNumbers[i] = createdAccounts.get(i).getNumber();
			}
			
			for (String accountNumber : accountNumbers) {
				aim.delete(accountNumber);
			}
		
		}
		
	}

	@Test
	public void testCreate() {
		try {
			aim.create(ac);
			aim.create(ac2);
			aim.create(ac3);
		} catch (AccountCreationException e) {}
		try {
			assertEquals(ac, aim.retrieve("1"));
			assertEquals(ac2, aim.retrieve("2"));
			assertEquals(ac3, aim.retrieve("3"));
		} catch (AccountNotFoundException e) {}
	}

	@Test
	public void testDelete() throws PersistenceException {
		
		aim.create(ac);
		aim.create(ac2);
		aim.create(ac3);
		
		assertEquals("Deveriam existir três contas", 3, aim.numberOfAccounts());
		
		aim.delete(ac.getNumber());
		
		assertEquals("Deveriam existir duas contas", 2, aim.numberOfAccounts());
		
	}
	
	@Test(expected = AccountDeletionException.class)
	public void testDeleteIsNull() throws AccountDeletionException{
		aim.delete("2");
	}

	@Test
	public void testRetrieve() {
		
	}

	@Test
	public void testUpdate() {
		
	}

	@Test
	public void testList() throws PersistenceException {
		
		aim.create(ac);
		aim.create(ac2);
		aim.create(ac3);
		
		assertEquals(aim.list().get(0), ac);
		assertEquals(aim.list().get(1), ac2);
		assertEquals(aim.list().get(2), ac3);
		
	}
	
	@Test(expected = PersistenceException.class)
	public void testListIsNull() throws PersistenceException{
		aim.list();
	}
	
	@Test
	public void testNumberOfAccounts() {
		
	}
	
	@Test
	public void testFindByClientId() throws PersistenceException {
		
		Client client = new Client("José da Silva", "jsilva", "12345");
		
		ClientInMemoryDAO cim = new ClientInMemoryDAO();
		cim.create(client);
		
		aim.create(ac);
		aim.create(ac2);
		aim.create(ac3);
		
		ac.setClientId(client.getId());
		ac2.setClientId(client.getId());
		
		List<Account> clientAccounts = aim.findByClientId(client.getId());
		
		assertEquals(ac, clientAccounts.get(0));
		assertEquals(ac2, clientAccounts.get(0));
		
	}
	
	public void testFindByClientIdWhenClientDontExists()
			throws PersistenceException{
		
		List<Account> clientAccounts = aim.findByClientId(1.0);
		
		assertTrue("Não deveria existir nenhuma conta para um cliente" +
				" inexistente", clientAccounts.isEmpty());
		
	}
}