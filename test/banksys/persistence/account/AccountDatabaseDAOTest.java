package banksys.persistence.account;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountLockedException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.model.Client;
import banksys.persistence.ResetSQLiteDataBase;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountDeletionException;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.client.ClientInMemoryDAO;
import banksys.persistence.exception.PersistenceException;

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
		String number = "1011";
		double balance = 100;
		AccountType accountType = AccountType.SPECIAL;
		double clientId = 4;
		double bonus = 0.05;
		
		Account account = new Account(number, balance, accountType, clientId);
		account.setBonus(bonus);
		
		String number2 = "789";
		double balance2 = 36000;
		AccountType accountType2 = AccountType.TAX;
		double clientId2 = 5;
		double bonus2 = 0.03;

		Account account2 = new Account(number2, balance2, accountType2, clientId2);
		account2.setBonus(bonus2);
		
		List<Account> accountList = new ArrayList<Account>();
		AccountDatabaseDAO dao = new AccountDatabaseDAO();
		
		try {
			dao.create(account);
			dao.create(account2);
			accountList = dao.list();
			
			assertEquals(2, accountList.size());
			
			Account account3 = accountList.get(0);
			Account account4 = accountList.get(1);
			
			Assert.assertEquals(number, account3.getNumber());
			Assert.assertEquals(balance, account3.getBalance(), 0);
			Assert.assertEquals(accountType, account3.getType());
			Assert.assertEquals(clientId, account3.getClientId(), 0);
			Assert.assertEquals(bonus, account3.getBonus(), 0);
			
			Assert.assertEquals(number2, account4.getNumber());
			Assert.assertEquals(balance2, account4.getBalance(), 0);
			Assert.assertEquals(accountType2, account4.getType());
			Assert.assertEquals(clientId2, account4.getClientId(), 0);
			Assert.assertEquals(bonus2, account4.getBonus(), 0);
			
		}catch (AccountCreationException e) {
			System.err.println("Could not create account on database: " + e.getMessage());
		}catch (AccountNotFoundException e) {
			System.err.println("Could not find account on database: " + e.getMessage());
		} catch (PersistenceException e) {
			System.err.println("Could not list accounts: " + e.getMessage());
		}
	}

	@Test
	public void testFindByClientId() {

		String number = "1011";
		double balance = 100;
		AccountType accountType = AccountType.SPECIAL;
		double clientId = 5.0;
		double bonus = 0.05;
		
		Account account = new Account(number, balance, accountType, clientId);
		account.setBonus(bonus);
		
		String number2 = "789";
		double balance2 = 36000;
		AccountType accountType2 = AccountType.TAX;
		double clientId2 = 4.0;
		double bonus2 = 0.03;

		Account account2 = new Account(number2, balance2, accountType2, clientId2);
		account2.setBonus(bonus2);
		
		String number3 = "54625";
		double balance3 = 3156;
		AccountType accountType3 = AccountType.TAX;
		double clientId3 = 5.0;
		double bonus3 = 0.10;

		Account account3 = new Account(number3, balance3, accountType3, clientId3);
		account3.setBonus(bonus3);
		
		List<Account> accountList = new ArrayList<Account>();
		AccountDatabaseDAO dao = new AccountDatabaseDAO();		

		try {
			dao.create(account);
			dao.create(account2);
			dao.create(account3);
	
			accountList = dao.findByClientId(5.0);
			
			assertEquals(2, accountList.size());
			
			Account account4 = accountList.get(0);
			Account account5 = accountList.get(1);
			
			Assert.assertEquals(number, account4.getNumber());
			Assert.assertEquals(balance, account4.getBalance(), 0);
			Assert.assertEquals(accountType, account4.getType());
			Assert.assertEquals(clientId, account4.getClientId(), 0);
			Assert.assertEquals(bonus, account4.getBonus(), 0);
			
			Assert.assertEquals(number3, account5.getNumber());
			Assert.assertEquals(balance3, account5.getBalance(), 0);
			Assert.assertEquals(accountType3, account5.getType());
			Assert.assertEquals(clientId3, account5.getClientId(), 0);
			Assert.assertEquals(bonus3, account5.getBonus(), 0);
			
		}catch (AccountCreationException e) {
			System.err.println("Could not create account on database: " + e.getMessage());
		}catch (AccountNotFoundException e) {
			System.err.println("Could not find account on database: " + e.getMessage());
		} catch (PersistenceException e) {
			System.err.println("Could not list accounts: " + e.getMessage());
		}
					
	}

	@Test
	public void testNumberOfAccounts() {

		String number = "1011";
		double balance = 100;
		AccountType accountType = AccountType.SPECIAL;
		double clientId = 5.0;
		double bonus = 0.05;
		
		Account account = new Account(number, balance, accountType, clientId);
		account.setBonus(bonus);
		
		String number2 = "789";
		double balance2 = 36000;
		AccountType accountType2 = AccountType.TAX;
		double clientId2 = 4.0;
		double bonus2 = 0.03;

		Account account2 = new Account(number2, balance2, accountType2, clientId2);
		account2.setBonus(bonus2);
		
		String number3 = "54625";
		double balance3 = 3156;
		AccountType accountType3 = AccountType.TAX;
		double clientId3 = 5.0;
		double bonus3 = 0.10;

		Account account3 = new Account(number3, balance3, accountType3, clientId3);
		account3.setBonus(bonus3);
		
		AccountDatabaseDAO dao = new AccountDatabaseDAO();		

		try {
			dao.create(account);
			dao.create(account2);
			dao.create(account3);
			
			assertEquals(3, dao.numberOfAccounts());
			
		}catch (AccountCreationException e) {
			System.err.println("Could not create account on database: " + e.getMessage());
		}catch (AccountNotFoundException e) {
			System.err.println("Could not find account on database: " + e.getMessage());
		} catch (PersistenceException e) {
			System.err.println("Could not list accounts: " + e.getMessage());
		}
					
	}

}
