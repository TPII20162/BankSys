package banksys.account;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;


public class OrdinaryAccountTest{


	@Test
	public void testCredit() {
		OrdinaryAccount account = new OrdinaryAccount("123A");
		try {
			account.credit(100);
		} catch (NegativeAmountException e) {
			fail(e.getMessage());
		}
		assertEquals("Ordinary account test fail", 100, account.getBalance(), 0);
	}

	@Test (expected = NegativeAmountException.class)
	public void testCreditNegative()  throws NegativeAmountException { 
		OrdinaryAccount account = new OrdinaryAccount("123A");
		account.credit(-100);
	}
	
	@Test
	public void testDebit() {
		OrdinaryAccount account = new OrdinaryAccount("123A");
			try {
				account.credit(100);
			} catch (NegativeAmountException e) {
				fail(e.getMessage());
			}
			try {
				account.debit(50);
			} catch (NegativeAmountException e) {
				fail(e.getMessage());
			} catch (InsufficientFundsException e) {
				fail(e.getMessage());
			}
			assertEquals("Ordinary account test fail", 50, account.getBalance(), 0);
	}
	@Test (expected = NegativeAmountException.class)
	public void testDebitWithNegativeAmount() throws NegativeAmountException {
		OrdinaryAccount account = new OrdinaryAccount("123A");
		try{
			account.debit(-10);
		} catch (InsufficientFundsException e) {
			fail(e.getMessage());
		}
	}

}
