package banksys.persistence.client;

import java.util.List;

import banksys.model.Client;
import banksys.persistence.client.exception.ClientCreationException;
import banksys.persistence.client.exception.ClientDeletionException;
import banksys.persistence.client.exception.ClientNotFoundException;
import banksys.persistence.exception.PersistenceException;

public class ClientDatabaseDAO implements ClientDAO {

	@Override
	public Client create(Client client) throws ClientCreationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Double id) throws ClientDeletionException {
		// TODO Auto-generated method stub

	}

	@Override
	public Client retrieve(Double id) throws ClientNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client retrieveByUsernameAndPassword(String username, String password)
			throws ClientNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> list() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfClients() throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}

}
