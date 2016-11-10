package banksys.persistence.client;

import java.util.List;

import banksys.model.Client;
import banksys.persistence.client.exception.ClientCreationException;
import banksys.persistence.client.exception.ClientDeletionException;
import banksys.persistence.client.exception.ClientNotFoundException;
import banksys.persistence.exception.PersistenceException;

public interface ClientDAO {

	public Client create(Client client) throws ClientCreationException;

	public void delete(Double id) throws ClientDeletionException;

	public Client retrieve(Double id) throws ClientNotFoundException;

	public Client retrieveByUsernameAndPassword(String username, String password) throws ClientNotFoundException;

	public List<Client> list() throws PersistenceException;

	public int numberOfClients() throws PersistenceException;

}