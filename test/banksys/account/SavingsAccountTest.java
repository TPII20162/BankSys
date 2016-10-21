package banksys.account;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.account.exception.NegativeAmountException;

public class SavingsAccountTest {

	@Test
	public void earnInterest() {
		SavingsAccount account = new SavingsAccount("123S");
		try {
			account.credit(100);
		} catch (NegativeAmountException e) {
			fail(e.getMessage());
		}
		account.earnInterest();
		assertEquals("Savings account test fail", 100.1, account.getBalance(), 0);
	}

}