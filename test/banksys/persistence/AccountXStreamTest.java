package banksys.persistence;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountXStreamTest {

	private AccountXStream aXStream;
	private OrdinaryAccount account1, account2;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		aXStream = new AccountXStream();
		account1 = new OrdinaryAccount("123A");
		account2 = new OrdinaryAccount("123B");
	}

	@After
	public void tearDown() throws Exception {
		try{
			aXStream.delete("123A");
		}catch(AccountDeletionException e){}
		try{
			aXStream.delete("123B");
		}catch(AccountDeletionException e){}
	}

	@Test
	public void testDelete() {
		try {
			aXStream.create(account1);
		} catch (AccountCreationException e) {}
		assertEquals("Uma conta deve ter sido criada", 1, aXStream.numberOfAccounts());
		try {
			aXStream.delete("123A");
		} catch (AccountDeletionException e) {}
		assertEquals("Uma conta deve ter sido apagada", 0, aXStream.numberOfAccounts());
	}

	@Test
	public void testCreate() {
		try {
			aXStream.create(account1);
		} catch (AccountCreationException e) {}
		assertEquals("Uma conta deve ter sido criada", 1, aXStream.numberOfAccounts());
		try {
			aXStream.create(account2);
		} catch (AccountCreationException e) {}
		assertEquals("Duas contas devem ter sido criadas", 2, aXStream.numberOfAccounts());
	}

	@Test
	public void testList() {
		try {
			aXStream.create(account1);
		} catch (AccountCreationException e) {}
		try {
			aXStream.create(account2);
		} catch (AccountCreationException e) {}
		
		AbstractAccount[] list = aXStream.list();
		try {
			assertEquals("Era pra o primeiro elemento da lista ser a conta 123A", aXStream.retrieve("123A"), list[0]);
		} catch (AccountNotFoundException e) {}
		try {
			assertEquals("Era pra o segundo elemento da lista ser a conta 123B", aXStream.retrieve("123B"), list[1]);
		} catch (AccountNotFoundException e) {}
	}

	@Test
	public void testNumberOfAccounts() {
		assertEquals("Devem existir 0 contas no repositório", 0, aXStream.numberOfAccounts());
		try {
			aXStream.create(account1);
		} catch (AccountCreationException e) {}
		assertEquals("Deve existir 1 conta no repositório", 1, aXStream.numberOfAccounts());
		try {
			aXStream.create(account2);
		} catch (AccountCreationException e) {}
		assertEquals("Devem existir 2 contas no repositório", 2, aXStream.numberOfAccounts());
	}

	@Test
	public void testRetrieve() {
		try {
			aXStream.create(account1);
		} catch (AccountCreationException e) {}
		try {
			assertEquals("Era para o número da conta ser 123A", account1, aXStream.retrieve("123A"));
		} catch (AccountNotFoundException e) {}
	}

	@Test(expected = AccountCreationException.class)
	public void testCreateDuplicatedAccounts() throws AccountCreationException {
		
		aXStream.create(account1);
		
		aXStream.create(account1);
		
	}
	
	@Test(expected = AccountDeletionException.class)
	public void testDeleteWithNonexistentAccount() throws AccountDeletionException {
		
		aXStream.delete("123A");
		
	}
}
