package banksys.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banksys.account.OrdinaryAccount;
import banksys.persistence.exception.AccountCreationException;

public class AccountVectorTest {
	
	private AccountVector accountVector;
	
	@Before
	public void setUp() {
		accountVector = new AccountVector();
	}
	
	@Test
	public void testNumberOfAccounts() throws AccountCreationException {
		
		accountVector.create(new OrdinaryAccount("123A"));
		
		assertEquals("Uma conta deveria ter sido criada", 1,
				accountVector.numberOfAccounts());
		
		accountVector.create(new OrdinaryAccount("123B"));
		
		assertEquals("Duas conta deveriam ter sido criadas",
				2, accountVector.numberOfAccounts());
		
	}
	
}