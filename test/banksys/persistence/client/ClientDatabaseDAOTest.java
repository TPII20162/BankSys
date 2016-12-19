package banksys.persistence.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banksys.model.Client;
import banksys.persistence.ResetSQLiteDataBase;
import banksys.persistence.client.exception.ClientCreationException;
import banksys.persistence.client.exception.ClientNotFoundException;


public class ClientDatabaseDAOTest {

	
	@Before
	public void setUp() throws Exception {
		ResetSQLiteDataBase.resetDataBase();
	}

	@After
	public void tearDown(){
	}
	
	@Test
	public void testCreate() {
	}

	@Test
	public void testDelete(){

	}

	@Test
	public void testRetrieve() {
	}

	@Test
	public void testRetrieveByUsernameAndPassword(){
		
		double clientId = 1;
		String fullName = "Full Test";
		String name = "Test";
		String password = "123A";
		
		Client client = new Client(clientId, fullName, name, password);
		
		ClientDatabaseDAO dao = new ClientDatabaseDAO();
		
		try {
			dao.create(client);
			
			Client retrieved = dao.retrieveByUsernameAndPassword(name, password);
			
			Assert.assertEquals(name, retrieved.getUsername());
			Assert.assertEquals(password, retrieved.getPassword());
		} catch (ClientCreationException e) {
			System.err.println("Could not create client on database: " + e.getMessage());
		} catch (ClientNotFoundException e) {
			System.err.println("Could not find client on database: " + e.getMessage());
		}

		
	}

	@Test
	public void testList(){
	}

	@Test
	public void testNumberOfClients(){
		double clientId = 1.0;
	
		Client client = new Client(clientId, null, null, null);
		
		double clientId1 = 2.0;
	
		Client client2 = new Client(clientId1, null, null, null);
		
		ClientDatabaseDAO dao = new ClientDatabaseDAO();		

		try {
			dao.create(client);
			dao.create(client2);
			
			assertEquals(2, dao.numberOfClients());
			
		}catch (ClientCreationException e) {
			System.err.println("Could not create client on database: " + e.getMessage());
		}catch (ClientNotFoundException e) {
			System.err.println("Could not find client on database: " + e.getMessage());
		}
					
	}
		
}
