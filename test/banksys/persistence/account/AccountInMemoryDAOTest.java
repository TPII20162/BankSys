package banksys.persistence.account;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountDeletionException;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.exception.PersistenceException;

public class AccountInMemoryDAOTest {

	private AccountInMemoryDAO aim;
	private Account ac, ac2, ac3;
	private ArrayList<Account> client1;
	
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
	
	@Test(expected = AccountCreationException.class)
	public void testCreateAlreadyExists() throws AccountCreationException{
		aim.create(ac);
		aim.create(ac);
	}

	@Test
	public void testDelete() {		
		try {
			aim.create(ac);
			aim.create(ac2);
			aim.create(ac3);
		} catch (AccountCreationException e) {}
		try {
			aim.delete("2");
		} catch (AccountDeletionException e) {}
		try {
			assertEquals(2, aim.numberOfAccounts());
		} catch (PersistenceException e) {}
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
	public void testFindByClientId() {
		client1 = new ArrayList<Account>();
		try {
			aim.create(ac);
			aim.create(ac2);
			aim.create(ac3);
		} catch (AccountCreationException e) {}
		client1.add(ac);
		client1.add(ac2);		
		try {
			assertEquals(client1, aim.findByClientId(1.0));
		} catch (PersistenceException e) {}
	}
	
	@Test(expected = PersistenceException.class)
	public void testFindByClientIdIsNull() throws PersistenceException{
		aim.findByClientId(1.0);
	}
}