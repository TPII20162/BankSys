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

public class FacadeTest {
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
		
		Facade financialController = new Facade(accountVector);
		
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
		Facade financialController = new Facade(accountVector);
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
	
	@Test
	public void testDoCredit(){
		Facade financialController = new Facade(accountVector);
		
		try {
			financialController.addAccount(ordinaryAccount1);
			financialController.doCredit("123A", 50);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		try {
			assertEquals("Deve ter 50 de credito",50, accountVector.retrieve("123A").getBalance(),0.0);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testDoDebit(){
		Facade financialController = new Facade(accountVector);
		
		try {
			financialController.addAccount(ordinaryAccount1);
			financialController.doCredit("123A", 50);
			financialController.doDebit("123A", 30);
		} catch (BankTransactionException e) {
	
			e.printStackTrace();
		}
		try {
			assertEquals("Deve ter 20 de credito",20, accountVector.retrieve("123A").getBalance(),0.0);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetBalance(){
		
		Facade financialController = new Facade(accountVector);
		
		try {
			financialController.addAccount(ordinaryAccount1);
			financialController.doCredit("123A", 50);
			financialController.doDebit("123A", 30);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
	
		try {
			assertEquals("Deve ter 20 de credito",20, financialController.getBalance("123A"),0.0);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test 
	public void testDoTransfer(){
		Facade financialController = new Facade(accountVector);
		
		try {
			financialController.addAccount(ordinaryAccount1);
			financialController.doCredit("123A", 50);
			financialController.addAccount(ordinaryAccount2);
			financialController.doCredit("123B", 50);
			
			financialController.doTransfer("123A", "123B", 30);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals("Deve ter 80 na conta 123B",80, financialController.getBalance("123B"),0.0 );
			assertEquals("Deve ter 20 na conta 123A",20, financialController.getBalance("123A"),0.0);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDoEarnBonus(){
		Facade financialController = new Facade(accountVector);
		
		SpecialAccount specialAccount = new SpecialAccount("123C");
		
		try {
			financialController.addAccount(specialAccount);
		
			financialController.doCredit("123C", 50);
			financialController.doEarnBonus("123C");
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		
		
		try {
			assertEquals("Deve ganhae seu bonus",50.5, financialController.getBalance("123C"),0.0);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDoEarnInterest(){
		
		Facade financialController = new Facade(accountVector);
		
		SavingsAccount savingsAccount = new SavingsAccount("123C");
		
		try {
			financialController.addAccount(savingsAccount);
		
			financialController.doCredit("123C", 50);
			financialController.doEarnInterest("123C");
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		
		
		try {
			assertEquals("Deve ter o valor 50.05 na conta", 50.05, financialController.getBalance("123C"),0.0);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCredit(){
		Facade financialController = new Facade(accountVector);
		
		try {
			financialController.addAccount(ordinaryAccount1);
			financialController.doCredit("123A", 50);
		} catch (BankTransactionException e) {
			e.printStackTrace();
		}
		try {
			assertEquals("Deve ter 50 de credito",50, financialController.getBalance("123A"),0.0);
		} catch (BankTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testDebit(){
		Facade financialController = new Facade(accountVector);
		
		try {
			financialController.addAccount(ordinaryAccount1);
			financialController.doCredit("123A", 50);
			financialController.doDebit("123A", 30);
		} catch (BankTransactionException e) {
	
			e.printStackTrace();
		}
		
		try {
			assertEquals("Deve ter 20 de credito",20, financialController.getBalance("123A"),0.0);
		} catch (BankTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
