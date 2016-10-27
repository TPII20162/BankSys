package banksys.control;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banksys.account.OrdinaryAccount;
import banksys.control.exception.BankTransactionException;
import banksys.persistence.AccountVector;

public class FinancialControllerTest {
	private AccountVector accountVector;
	private OrdinaryAccount ordinaryAccount1, ordinaryAccount2;

	
	
	@Before
	public void setUp() {
		accountVector = new AccountVector();
		ordinaryAccount1 = new OrdinaryAccount("123A");
		ordinaryAccount2 = new OrdinaryAccount("123B");
		
	}
	

	@Test
	public void testAddAccount(){
		
		FinancialController financialController = new FinancialController(accountVector);
		
		try {
			financialController.addAccount(ordinaryAccount1);
			assertEquals("Era para ter adicionado 1 conta ",1,accountVector.numberOfAccounts());
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
			
		try {
			financialController.addAccount(ordinaryAccount2);
			assertEquals("Era para ter adicionado 2 contas ",2,accountVector.numberOfAccounts());
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		
	}

	
	@Test
	public void testRemoveAccount(){
		FinancialController financialController = new FinancialController(accountVector);
		try {
			financialController.addAccount(ordinaryAccount1);
			financialController.addAccount(ordinaryAccount2);
		} catch (BankTransactionException e1) {
		
			e1.printStackTrace();
		}
		
		try {
			financialController.removeAccount("123A");
			assertEquals("Era para ter removido uma conta restando 1",1, accountVector.numberOfAccounts());
			
			financialController.removeAccount("123B");
			assertEquals("Era para ter removido uma conta restando 0", 0, accountVector.numberOfAccounts());
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
	}
}
