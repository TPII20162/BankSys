package banksys.control;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.control.exception.BankTransactionException;
import banksys.persistence.AccountVector;
import banksys.persistence.exception.AccountNotFoundException;

public class BankControllerTest {
	private AccountVector ac;
	private OrdinaryAccount oc,oc2;
	private BankController bc;
	
	@Before
	public void setUp() {
		ac = new AccountVector();
		bc = new BankController(ac);
		oc = new OrdinaryAccount("123A");
		oc2 = new OrdinaryAccount("123B");
	}
	
	@Test
	public void testAddAccount() {
		try {
			bc.addAccount(oc);
		} catch (BankTransactionException e) {}
		assertEquals("Era para ter adicionado 1 conta ",1,ac.numberOfAccounts());
		try {
			bc.addAccount(oc2);
		} catch (BankTransactionException e) {}
		assertEquals("Era para ter adicionado 2 contas ",2,ac.numberOfAccounts());
		
	}

	@Test
	public void testRemoveAccount() {
		try {
			bc.addAccount(oc);
			bc.removeAccount(oc.getNumber());
		} catch (BankTransactionException e) {}
		try{
			assertNull("Era para ser nulo", ac.retrieve(oc.getNumber()));
		}catch(Exception e){
			
		}
	}

	@Test
	public void testDoCredit() {
		try {
			bc.addAccount(oc);
			bc.doCredit("123A", 50);
		} catch (BankTransactionException e) {}
		try {
			assertEquals("Era para ser igual",50, ac.retrieve("123A").getBalance(), 0.01);
		} catch (AccountNotFoundException e) {}
	}

	@Test
	public void testDoDebit() {
		try {
			bc.addAccount(oc);
			bc.doCredit("123A", 50);
		} catch (BankTransactionException e) {}
		try {
			assertEquals("Era para ser igual",50, ac.retrieve("123A").getBalance(),0.001);
		} catch (AccountNotFoundException e) {}
		try {
			bc.doDebit("123A", 30);
		} catch (BankTransactionException e) {}
		try {
			assertEquals("Era para ser igual", 20, ac.retrieve("123A").getBalance(),0.001);
		} catch (AccountNotFoundException e) {}
	}

	@Test
	public void testGetBalance() {
		oc = new OrdinaryAccount("123");
		try {
			bc.addAccount(oc);
			bc.doCredit("123", 50);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		try {
			assertEquals("Era para ser igual",bc.getBalance("123"),50,0.001);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDoTransfer() {
		oc = new OrdinaryAccount("123");
		oc2 = new OrdinaryAccount("456");
		try {
			bc.addAccount(oc);
			bc.doCredit("123", 50);
			bc.addAccount(oc2);
			bc.doTransfer("123", "456", 20);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		try {
			assertEquals("Era para ser igual",bc.getBalance("123"),30,0.001);
			assertEquals("Era para ser igual",bc.getBalance("456"),20,0.001);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDoEarnInterest() {
		SavingsAccount sc = new SavingsAccount("1234");
		try {
			bc.addAccount(sc);
			bc.doCredit("1234", 10);
			bc.doEarnInterest("1234");
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		try{
		assertEquals("Era para ser Igual",ac.retrieve("1234").getBalance(),10.01,0.001);
		}catch(AccountNotFoundException e){}
	}

	@Test
	public void testDoEarnBonus() {
		SpecialAccount sc = new SpecialAccount("1223");
		try{
			bc.addAccount(sc);
			bc.doCredit("1223", 50);
			bc.doEarnBonus("1223");
		}catch(Exception e){}
		try {
			assertEquals("Era para ser igual",ac.retrieve("1223").getBalance(),50.5,0.001);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
	}

}
