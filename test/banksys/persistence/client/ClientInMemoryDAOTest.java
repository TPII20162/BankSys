package banksys.persistence.client;

import banksys.model.Client;
import banksys.persistence.client.exception.ClientCreationException;
import banksys.persistence.exception.PersistenceException;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientInMemoryDAOTest {
	
	private ClientInMemoryDAO cim;
	
	@Before
	public void setUp() {
		cim = new ClientInMemoryDAO();
	}
	
	@After
	public void tearDown() throws PersistenceException {
		
		int numberOfClients = cim.numberOfClients();
		
		if (numberOfClients > 0) {
		
			Double[] clientIds = new Double[numberOfClients];
			
			List<Client> createdClients = cim.list();
			
			for (int i = 0; i < numberOfClients; i++) {
				clientIds[i] = createdClients.get(i).getId();
			}
			
			for (Double clientId : clientIds) {
				cim.delete(clientId);
			}
		
		}
		
	}
	
	@Test
	public void testCreate() throws ClientCreationException {
		
		Client client  = new Client((double)1,"Fulano de Tal", "fulano1",
				"ful1ano");
		
		Client clientReceive = cim.create(client);
		
		assertEquals(client, clientReceive);
		
	}
	
	@Test
	public void testDelete() throws PersistenceException {
		
		Client client  = new Client((double)1,"Fulano de Tal", "fulano1",
				"ful1ano");
		
		int sizeBefore;
		
		cim.create(client);
		
		sizeBefore = cim.numberOfClients();
		
		cim.delete(client.getId());
		
		assertEquals(sizeBefore-1, cim.numberOfClients());
		
	}
	
	@Test
	public void testRetrieve() throws PersistenceException {
		
		Client client  = new Client((double)1,"Fulano de Tal", "fulano1",
				"ful1ano");
		
		cim.create(client);
		
		Client clientReceived = cim.retrieve(client.getId());
		assertEquals(client.getId(), clientReceived.getId());
		
	}
	
	@Test
	public void testRetriveByUsernameAndPassword() throws PersistenceException {
		
		Client client  = new Client((double)1,"Fulano de Tal", "fulano1",
				"ful1ano");
		
		cim.create(client);
		
		assertNotNull(cim.retrieveByUsernameAndPassword("fulano1", "ful1ano"));
		
	}
	
	@Test
	public void testNumberOfClients() throws PersistenceException {
		
		Client client  = new Client("Fulano de Tal", "fulano1",
				"ful1ano");
		
		cim.create(client);
		
		assertEquals(1,cim.numberOfClients());
		
	}
	
}
