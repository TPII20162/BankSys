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

	private AccountInMemoryDAO accountInMemory;
	
	@Before
	public void setUp() throws Exception {
		accountInMemory = new AccountInMemoryDAO();
	}

	@After
	public void tearDown() throws Exception {
		
		int numberOfAccounts = accountInMemory.numberOfAccounts();
		
		if (numberOfAccounts > 0) {
		
			List<Account> createdAccounts = accountInMemory.list();
			
			for (Account account : createdAccounts) {
				accountInMemory.delete(account.getNumber());
			}
		
		}
		
	}

	@Test
	public void testCreate() throws PersistenceException {
		
		Account account1 = new Account(AccountType.ORDINARY);
		Account account2 = new Account(AccountType.ORDINARY);
		Account account3 = new Account(AccountType.ORDINARY);
		
		accountInMemory.create(account1);
		accountInMemory.create(account2);
		accountInMemory.create(account3);
		
		assertTrue("account1 deveria estar contida na lista de contas",
				accountInMemory.list().contains(account1));
		
		assertTrue("account2 deveria estar contida na lista de contas",
				accountInMemory.list().contains(account2));
		
		assertTrue("account3 deveria estar contida na lista de contas",
				accountInMemory.list().contains(account3));
		
	}

	@Test
	public void testDeleteAndVerifyNumberOfAccounts()
			throws PersistenceException {
		
		Account account1 = new Account(AccountType.ORDINARY);
		Account account2 = new Account(AccountType.ORDINARY);
		
		accountInMemory.create(account1);
		accountInMemory.create(account2);
		
		assertEquals("Deveriam existir duas contas", 2,
				accountInMemory.numberOfAccounts());
		
		accountInMemory.delete(account1.getNumber());
		
		assertEquals("Deveria existir apenas uma conta", 1,
				accountInMemory.numberOfAccounts());
		
	}
	
	@Test(expected = AccountDeletionException.class)
	public void testDeleteIsNull() throws AccountDeletionException{
		accountInMemory.delete("2");
	}

	@Test
	public void testRetrieve() throws AccountCreationException,
			AccountNotFoundException {
		
		Account account = new Account(AccountType.ORDINARY);
		
		accountInMemory.create(account);
		
		String accountNumber = account.getNumber();
		
		assertEquals("A conta recuperada é diferente da conta criada",
				account, accountInMemory.retrieve(accountNumber));
		
	}
	
	@Test
	public void testRetrieveSpecialAccount() throws AccountCreationException,
			AccountNotFoundException {
		
		Account account = new Account(AccountType.SPECIAL);
		
		accountInMemory.create(account);
		
		String accountNumber = account.getNumber();
		
		assertEquals("A conta recuperada é diferente da conta criada",
				account, accountInMemory.retrieve(accountNumber));
		
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void testRetrieveWithNonExistentAccountNumber() throws
			AccountNotFoundException {
		
		accountInMemory.retrieve("-1");
		
	}
	
	@Test
	public void testUpdate() {
		// TODO
	}

	@Test
	public void testList() throws PersistenceException {
		
		Account account1 = new Account(AccountType.ORDINARY);
		Account account2 = new Account(AccountType.ORDINARY);
		Account account3 = new Account(AccountType.ORDINARY);
		
		accountInMemory.create(account1);
		accountInMemory.create(account2);
		accountInMemory.create(account3);
		
		assertEquals("As contas não são a mesma", account1,
				accountInMemory.list().get(0));
		
		assertEquals("As contas não são a mesma", account2,
				accountInMemory.list().get(1));
		
		assertEquals("As contas não são a mesma", account3,
				accountInMemory.list().get(2));
		
	}
	
	@Test(expected = PersistenceException.class)
	public void testListIsNull() throws PersistenceException{
		accountInMemory.list();
	}
	
	@Test
	public void testFindByClientId() throws PersistenceException {
		
		Client client = new Client("José da Silva", "jsilva", "12345");
		
		ClientInMemoryDAO cim = new ClientInMemoryDAO();
		
		cim.create(client);
		
		Account account1 = new Account(AccountType.ORDINARY);
		Account account2 = new Account(AccountType.ORDINARY);
		
		accountInMemory.create(account1);
		accountInMemory.create(account2);
		
		account1.setClientId(client.getId());
		account2.setClientId(client.getId());
		
		List<Account> clientAccounts =
				accountInMemory.findByClientId(client.getId());
		
		assertEquals("As contas deveriam ser a mesma", account1,
				clientAccounts.get(0));
		
		assertEquals("As contas deveriam ser a mesma", account2,
				clientAccounts.get(1));
		
		// Deleta o cliente da lista para não afetar os testes de clients
		cim.delete(client.getId());
		
	}
	
	public void testFindByClientIdWhenClientDontExists()
			throws PersistenceException{
		
		List<Account> clientAccounts = accountInMemory.findByClientId(1.0);
		
		assertTrue("Não deveria existir nenhuma conta para um cliente" +
				" inexistente", clientAccounts.isEmpty());
		
	}
	
}