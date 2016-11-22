package banksys.service;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.model.Client;
import banksys.persistence.account.AccountInMemoryDAO;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.service.exception.ClientServiceException;

public class ClientServicesImplTest {
	
	@Test
	public  void doCreditTest(){
		
		AccountInMemoryDAO accountInMemoryDAO = new AccountInMemoryDAO();
		try {
			accountInMemoryDAO.create(new Account("123",AccountType.ORDINARY));
		} catch (AccountCreationException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		
		ClientServicesImpl clientServicesImpl = new ClientServicesImpl(accountInMemoryDAO);
		Client client = new Client(123.,"teste", "teste", "teste");
		
		try {
			clientServicesImpl.doCredit(client, "123", 50.0);
		} catch (ClientServiceException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		
		try {
			assertEquals("Erro valor diferente", 00, 50.,clientServicesImpl.doRetrieveBalance(client, "123"));
		} catch (ClientServiceException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		
	}
	
	
	@Test
	public void doDebitTest(){
		AccountInMemoryDAO accountInMemoryDAO = new AccountInMemoryDAO();
		try {
			accountInMemoryDAO.create(new Account("123",AccountType.ORDINARY));
		} catch (AccountCreationException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		
		ClientServicesImpl clientServicesImpl = new ClientServicesImpl(accountInMemoryDAO);
		Client client = new Client(123.,"teste", "teste", "teste");
		
		try {
			clientServicesImpl.doCredit(client, "123", 50.0);
			clientServicesImpl.doDebit(client, "123", 30.0);
		} catch (ClientServiceException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		
		try {
			assertEquals("Erro valor diferente", 00, 30.,clientServicesImpl.doRetrieveBalance(client, "123"));
		} catch (ClientServiceException e) {
			System.out.println("Erro: "+e.getMessage());
		}
	}
}
