package banksys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountVectorTest {

	private static String ACCOUNT_NUMBER_A = "123A";
	private static String ACCOUNT_NUMBER_B = "123B";

	private AccountVector accountVector;

	@Before
	public void setUp() {
		accountVector = new AccountVector();
	}

	@Test
	public void testNumberOfAccounts() throws AccountCreationException {

		accountVector.create(new OrdinaryAccount(ACCOUNT_NUMBER_A));

		assertEquals("Uma conta deveria ter sido criada", 1, accountVector.numberOfAccounts());

		accountVector.create(new OrdinaryAccount(ACCOUNT_NUMBER_B));

		assertEquals("Duas conta deveriam ter sido criadas", 2, accountVector.numberOfAccounts());

	}

	@Test
	public void testDelete() throws AccountCreationException, AccountDeletionException {

		OrdinaryAccount oAccount = new OrdinaryAccount(ACCOUNT_NUMBER_A);

		accountVector.create(oAccount);

		assertEquals("Deveria existir uma conta", 1, accountVector.numberOfAccounts());

		accountVector.delete(ACCOUNT_NUMBER_A);

		assertEquals("A conta deveria ter sido removida", 0, accountVector.numberOfAccounts());

	}

	@Test(expected = AccountCreationException.class)
	public void testCreateDuplicatedAccounts() throws AccountCreationException {

		OrdinaryAccount oAccount = new OrdinaryAccount(ACCOUNT_NUMBER_A);

		accountVector.create(oAccount);

		accountVector.create(oAccount);

	}

	@Test(expected = AccountDeletionException.class)
	public void testDeleteWithNonexistentAccount() throws AccountDeletionException {

		accountVector.delete(ACCOUNT_NUMBER_A);

	}

	@Test
	public void testList() {
		OrdinaryAccount oAccount = new OrdinaryAccount(ACCOUNT_NUMBER_A);

		try {
			accountVector.create(oAccount);
		} catch (AccountCreationException e) {
			fail(e.getMessage());
		}
		
		AbstractAccount list[] = accountVector.list();
		
		assertTrue("Should be equals", (list.length == 1) && list[0].getNumber() == oAccount.getNumber());
	}

	@Test
	public void testRetrieve() {
		
		AbstractAccount a1 = new OrdinaryAccount(ACCOUNT_NUMBER_A);
		AbstractAccount a2 = new OrdinaryAccount(ACCOUNT_NUMBER_B);
		
		try {
			accountVector.create(a1);
			accountVector.create(a2);
		} catch (AccountCreationException e) {
			fail("Failed to create accounts");
		}
		
		try {
			
			assertEquals("Os números das contas deveriam ser iguais", a1.getNumber(),
					accountVector.retrieve(ACCOUNT_NUMBER_A).getNumber());
			
			assertEquals("Os números das contas deveriam ser iguais", a2.getNumber(),
					accountVector.retrieve(ACCOUNT_NUMBER_B).getNumber());
			
		} catch (AccountNotFoundException e) {
			fail("Failed to retrieve account");
		}
	}

}
