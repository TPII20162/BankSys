package banksys.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banksys.account.OrdinaryAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;

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
	
	@Test
	public void testDelete() throws AccountCreationException,
			AccountDeletionException {
		
		OrdinaryAccount oAccount = new OrdinaryAccount("123A");
		
		accountVector.create(oAccount);
		
		assertEquals("Deveria existir uma conta", 1,
				accountVector.numberOfAccounts());
		
		accountVector.delete("123A");
		
		assertEquals("A conta deveria ter sido removida", 0,
				accountVector.numberOfAccounts());
		
	}

}