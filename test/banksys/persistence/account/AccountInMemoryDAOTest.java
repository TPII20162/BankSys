package banksys.persistence.account;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.persistence.account.exception.AccountCreationException;
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
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieve() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testList() {
		try {
			aim.create(ac);
			aim.create(ac2);
			aim.create(ac3);
		} catch (AccountCreationException e) {}
		try {
			assertEquals(aim.list().get(0), ac);
			assertEquals(aim.list().get(1), ac2);
			assertEquals(aim.list().get(2), ac3);
		} catch (PersistenceException e) {}
	}

	@Test(expected = PersistenceException.class)
	public void testListIsNull() throws PersistenceException{
		aim.list();
	}
	
	@Test
	public void testNumberOfAccounts() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByClientId() {
		fail("Not yet implemented");
	}

}