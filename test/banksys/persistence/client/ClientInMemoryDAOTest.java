package banksys.persistence.client;

import banksys.model.Client;
import banksys.persistence.client.exception.ClientCreationException;
import banksys.persistence.exception.PersistenceException;

import static org.junit.Assert.*;
import org.junit.Test;

public class ClientInMemoryDAOTest {

	@Test
	public void createTest() throws ClientCreationException{
		
		Client client  = new Client((double)1,"Fulano de Tal", "fulano1", "ful1ano");
		
		ClientInMemoryDAO clientDAO = new ClientInMemoryDAO();
		
		Client clientReceive = clientDAO.create(client);
		
		assertEquals(client, clientReceive);
	}
	
	@Test
	public void deleteTest() throws PersistenceException{
		
		Client client  = new Client((double)1,"Fulano de Tal", "fulano1", "ful1ano");
		int sizeBefore;
		
		ClientInMemoryDAO clientDAO = new ClientInMemoryDAO();
		
		clientDAO.create(client);
		
		sizeBefore = clientDAO.numberOfClients();
		
		clientDAO.delete(client.getId());
		
		assertEquals(sizeBefore-1, clientDAO.numberOfClients());
	}
}
