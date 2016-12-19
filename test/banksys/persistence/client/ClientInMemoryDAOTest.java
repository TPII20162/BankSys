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
	
	private ClientInMemoryDAO clientInMemory;
	
	@Before
	public void setUp() {
		clientInMemory = new ClientInMemoryDAO();
	}
	
	@After
	public void tearDown() throws PersistenceException {
		clientInMemory.clearlist();
	}
	
	@Test
	public void testCreate() throws ClientCreationException {
		
		Client client  = new Client("Fulano de Tal", "fulano",
				"ful1ano");
		
		Client clientReceive = clientInMemory.create(client);
		
		assertEquals("Os clientes não são o mesmo", client, clientReceive);
		
	}
	
	@Test
	public void testDelete() throws PersistenceException {
		
		Client client  = new Client("Fulano de Tal", "fulano",
				"ful1ano");
		
		clientInMemory.create(client);
		
		assertEquals("Deveria existir apenas um cliente criado", 1,
				clientInMemory.numberOfClients());
		
		clientInMemory.delete(client.getId());
		
		assertEquals("Não deveria existir nenhum cliente em memória",
				0, clientInMemory.numberOfClients());
		
	}
	
	@Test
	public void testRetrieve() throws PersistenceException {
		
		Client client  = new Client("Fulano de Tal", "fulano",
				"fulano");
		
		clientInMemory.create(client);
		
		Client clientReceived = clientInMemory.retrieve(client.getId());
		
		assertEquals("O cliente criado e o recuperado não são o mesmo",
				client, clientReceived);
		
	}
	
	@Test
	public void testRetriveByUsernameAndPassword() throws PersistenceException {
		
		Client client  = new Client("Fulano de Tal", "fulano",
				"fulano");
		
		clientInMemory.create(client);
		
		assertEquals("O cliente criado e o recuperado não são o mesmo", client,
				clientInMemory.retrieveByUsernameAndPassword("fulano",
						"fulano"));
		
	}
	
	@Test
	public void testList() throws PersistenceException {
		
		Client client1 = new Client("Fulano de Tal", "fulano",
				"fulano");
		
		Client client2 = new Client("Sicrano de Tal", "sicrano",
				"sicrano");
		
		Client client3 = new Client("Beltrano de Tal", "beltrano",
				"beltrano");
		
		clientInMemory.create(client1);
		clientInMemory.create(client2);
		clientInMemory.create(client3);
		
		List<Client> clientsList = clientInMemory.list();
		
		assertEquals("Os clientes não são o mesmo", client1,
				clientsList.get(0));
		
		assertEquals("Os clientes não são o mesmo", client2,
				clientsList.get(1));
		
		assertEquals("Os clientes não são o mesmo", client3,
				clientsList.get(2));
		
	}
	
	@Test
	public void testNumberOfClients() {
		
		Client client1 = new Client("Felipe", "fscfelipe", "111");
		Client client2 = new Client("Jose", "esoj", "111");
		Client client3 = new Client("Gigi", "igig", "111");
		
		try {
			clientInMemory.create(client1);
			clientInMemory.create(client2);
			clientInMemory.create(client3);
		} catch (ClientCreationException e) {
			fail("Falha ao adicionar cliente!");
		}
		
		//SÃO 4 CLIENTES, POIS A CLASSE INCLUI 1 POR PADRÃO
		try {
			assertEquals(4, clientInMemory.numberOfClients());
		} catch (PersistenceException e) {
			fail("Falha ao captar número de clientes!");
		}
		
	}
	
}
