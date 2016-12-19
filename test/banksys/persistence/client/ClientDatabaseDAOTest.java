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
import banksys.persistence.exception.PersistenceException;


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
		Client c1 = new Client(1.0, "Maria", "mary", "123");
		Client c2 = new Client(2.0, "Antonio", "anthony", "456");
		Client c3 = new Client(3.0, "Jose", "josh", "789");
		Client c4 = new Client(4.0, "Chico", "chuck", "101");
		
		ClientDatabaseDAO clientDb = new ClientDatabaseDAO();
		try {
			clientDb.create(c1);
			clientDb.create(c2);
			clientDb.create(c3);
			clientDb.create(c4);
		} catch (ClientCreationException e) {
			fail("Falha ao adicionar cliente no banco!");
		}
		
		try {
			assertEquals(4, clientDb.list().size());
		} catch (PersistenceException e) {
			fail("Falha ao listar clientes!");
		}
		
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
