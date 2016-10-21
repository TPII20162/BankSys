package banksys.account;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class TaxAccountTest {

	@Test
	public void testDebit()  {
		TaxAccount account = new TaxAccount("123T");
		try {
			account.credit(100);
		} catch (NegativeAmountException e1) {
			e1.printStackTrace();
		}
		try {
			account.debit(50);
		} catch (NegativeAmountException e) {
			fail(e.getMessage());
		} catch (InsufficientFundsException e) {
			fail(e.getMessage());
		}
		assertEquals("Ordinary account test fail", 49.95, account.getBalance(), 0);
}

}
