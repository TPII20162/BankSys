package banksys.account;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.account.exception.NegativeAmountException;

public class SpecialAccountTest {

	@Test
	public void testCredit() {
		SpecialAccount account = account();
		assertEquals("Special account test fail", 100, account.getBalance(), 0);
	}

	@Test
	public void testEarnBonus() {
		SpecialAccount account = account();
		account.earnBonus();
		assertEquals("Special account test fail", 101, account.getBalance(), 0);
	}


	@Test
	public void testGetBonus() {
		SpecialAccount account = account();
		assertEquals("Special account test fail", 1, account.getBonus(), 0);
	}
	
	private SpecialAccount account() {
		SpecialAccount account = new SpecialAccount("123SP");
		try {
			account.credit(100);
		} catch (NegativeAmountException e) {
			fail(e.getMessage());
		}
		return account;
	}

}
