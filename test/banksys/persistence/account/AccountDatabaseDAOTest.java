package banksys.persistence.account;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.persistence.ResetSQLiteDataBase;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountDeletionException;
import banksys.persistence.account.exception.AccountNotFoundException;

public class AccountDatabaseDAOTest {

	@Before
	public void setUp() throws Exception {
		ResetSQLiteDataBase.resetDataBase();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		String number = "123";
		double balance = 9000;
		AccountType accountType = AccountType.ORDINARY;
		double clientId = 7;
		double bonus = 0.01;
		
		Account account = new Account(number, balance, accountType, clientId);
		account.setBonus(bonus);
		
		AccountDatabaseDAO dao = new AccountDatabaseDAO();
		try {
			dao.create(account);
			
			Account retrieved = dao.retrieve(number);
			
			Assert.assertNotNull(retrieved);
		} catch (AccountCreationException e) {
			System.err.println("Could not create account on database: " + e.getMessage());
		} catch (AccountNotFoundException e) {
			System.err.println("Could not find account on database: " + e.getMessage());
		}
	}

	@Test
	public void testDelete() {
		String number = "456";
		double balance = 800;
		AccountType accountType = AccountType.SAVINGS;
		double clientId = 1;
		double bonus = 0.02;
		
		Account account = new Account(number, balance, accountType, clientId);
		account.setBonus(bonus);
		
		AccountDatabaseDAO dao = new AccountDatabaseDAO();
		try {
			dao.create(account);
			
			dao.delete(number);
			
			Account retrieved = dao.retrieve(number);
			
			Assert.assertNull(retrieved);
		} catch (AccountCreationException e) {
			System.err.println("Could not create account on database: " + e.getMessage());
		} catch (AccountNotFoundException e) {
			System.err.println("Could not find account on database: " + e.getMessage());
		} catch (AccountDeletionException e) {
			System.err.println("Could not delete account from database: " + e.getMessage());
		}
	}

	@Test
	public void testRetrieve() {
		String number = "897SH";
		double balance = 100;
		AccountType accountType = AccountType.SPECIAL;
		double clientId = 4;
		double bonus = 0.05;
		
		Account account = new Account(number, balance, accountType, clientId);
		account.setBonus(bonus);
		
		AccountDatabaseDAO dao = new AccountDatabaseDAO();
		try {
			dao.create(account);
			
			Account retrieved = dao.retrieve(number);
			
			Assert.assertEquals(number, retrieved.getNumber());
			Assert.assertEquals(balance, retrieved.getBalance(), 0);
			Assert.assertEquals(accountType, retrieved.getType());
			Assert.assertEquals(clientId, retrieved.getClientId(), 0);
			Assert.assertEquals(bonus, retrieved.getBonus(), 0);
		} catch (AccountCreationException e) {
			System.err.println("Could not create account on database: " + e.getMessage());
		} catch (AccountNotFoundException e) {
			System.err.println("Could not find account on database: " + e.getMessage());
		}
	}

	@Test
	public void testUpdate() {
		String number = "897SH";
		double balance = 100;
		AccountType accountType = AccountType.SPECIAL;
		double clientId = 4;
		double bonus = 0.05;
		
		Account account = new Account(number, balance, accountType, clientId);
		account.setBonus(bonus);
		
		double newBalance = 36000;
		AccountType newAccountType = AccountType.TAX;
		double newClientId = 5;
		double newBonus = 0.03;
		
		AccountDatabaseDAO dao = new AccountDatabaseDAO();
		try {
			dao.create(account);
			
			account.setBalance(newBalance);
			account.setBonus(newBonus);
			account.setClientId(newClientId);
			account.setType(newAccountType);
			
			dao.update(account);
			
			Account retrieved = dao.retrieve(number);
			
			Assert.assertEquals(number, retrieved.getNumber());
			Assert.assertEquals(newBalance, retrieved.getBalance(), 0);
			Assert.assertEquals(newAccountType, retrieved.getType());
			Assert.assertEquals(newClientId, retrieved.getClientId(), 0);
			Assert.assertEquals(newBonus, retrieved.getBonus(), 0);
		} catch (AccountCreationException e) {
			System.err.println("Could not create account on database: " + e.getMessage());
		} catch (AccountNotFoundException e) {
			System.err.println("Could not find account on database: " + e.getMessage());
		}
	}

	@Test
	public void testList() {
	}

	@Test
	public void testFindByClientId() {
	}

	@Test
	public void testNumberOfAccounts() {
	}

}
