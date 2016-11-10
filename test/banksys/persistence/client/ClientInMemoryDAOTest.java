package banksys.persistence.client;

import banksys.model.Client;
import banksys.persistence.client.exception.ClientCreationException;

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
}
