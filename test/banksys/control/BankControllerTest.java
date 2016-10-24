package banksys.control;

import static org.junit.Assert.*;

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
	@Test
	public void testAddAccount() {
		ac = new AccountVector();
		oc = new OrdinaryAccount("123");
		bc = new BankController(ac);
		try {
			bc.addAccount(oc);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		
		assertEquals("Era para ter adicionado ",ac.numberOfAccounts(),1,0.001);
	}

	@Test
	public void testRemoveAccount() {
		ac = new AccountVector();
		oc = new OrdinaryAccount("123");
		bc = new BankController(ac);
		try {
			bc.addAccount(oc);
			bc.removeAccount(oc.getNumber());
		} catch (BankTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			assertNull("Era para ser nulo",ac.retrieve(oc.getNumber()));
		}catch(Exception e){
			
		}
	}

	@Test
	public void testDoCredit() {
		ac = new AccountVector();
		oc = new OrdinaryAccount("123");
		bc = new BankController(ac);
		try {
			bc.addAccount(oc);
			bc.doCredit("123", 50);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		try {
			assertEquals("Era para ser igual",ac.retrieve("123").getBalance(),50,0.001);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDoDebit() {
		ac = new AccountVector();
		oc = new OrdinaryAccount("123");
		bc = new BankController(ac);
		try {
			bc.addAccount(oc);
			bc.doCredit("123", 50);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		try {
			assertEquals("Era para ser igual",ac.retrieve("123").getBalance(),50,0.001);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetBalance() {
		ac = new AccountVector();
		oc = new OrdinaryAccount("123");
		bc = new BankController(ac);
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
		ac = new AccountVector();
		oc = new OrdinaryAccount("123");
		oc2 = new OrdinaryAccount("456");
		bc = new BankController(ac);
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
		ac = new AccountVector();
		bc = new BankController(ac);
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
		ac = new AccountVector();
		bc = new BankController(ac);
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
