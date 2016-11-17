package banksys.persistence.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import banksys.model.Client;
import banksys.persistence.client.exception.ClientCreationException;
import banksys.persistence.client.exception.ClientDeletionException;
import banksys.persistence.client.exception.ClientNotFoundException;
import banksys.persistence.exception.PersistenceException;

public class ClientInMemoryDAO implements ClientDAO {
	private static double CLIENT_IDS = 1;

	private static List<Client> clients = new ArrayList<Client>();

	private static double nextId() {
		return CLIENT_IDS++;
	}

	public ClientInMemoryDAO() {
	}

	@Override
	public Client create(Client client) throws ClientCreationException {
		
		for (Client someClient : clients){
			if (someClient.getId() == client.getId()){
				throw new ClientCreationException("Error: Duplicate client of ID" + client.getId() + "!");
			}
		}
		client.setId(nextId());
		ClientInMemoryDAO.clients.add(client);
		return client;
	}

	@Override
	public void delete(Double id) throws ClientDeletionException {
		Client client = findById(id);
		if (client != null) {
			ClientInMemoryDAO.clients.remove(client);
		} else {
			throw new ClientDeletionException("Client ID number " + id + " not found!");
		}
	}

	@Override
	public Client retrieve(Double id) throws ClientNotFoundException {
		Client client = findById(id);
		if (client != null) {
			return client;
		} else {
			throw new ClientNotFoundException("Client ID numer " + id + " not found!");
		}
	}

	@Override
	public Client retrieveByUsernameAndPassword(String username, String password) throws ClientNotFoundException {
		Client client = findByUsernameAndPassword(username, password);
		if (client != null) {
			return client;
		} else {
			throw new ClientNotFoundException("Client username " + username + " not found!");
		}
	}

	@Override
	public List<Client> list() throws PersistenceException {
		if (ClientInMemoryDAO.clients.size() == 0) {
			throw new PersistenceException("No existing client list!");
		}
		List<Client> list = new ArrayList<>();
		list.addAll(ClientInMemoryDAO.clients);

		return list;
	}

	@Override
	public int numberOfClients() throws PersistenceException {
		return ClientInMemoryDAO.clients.size();
	}

	private Client findById(Double id) {
		for (Client client : ClientInMemoryDAO.clients) {
			if (client.getId() == id) {
				return client;
			}
		}
		return null;
	}

	private Client findByUsernameAndPassword(String username, String password) {
		for (Client client : ClientInMemoryDAO.clients) {
			if (client.getUsername().equals(username) && client.getPassword().equals(password)) {
				return client;
			}
		}
		return null;
	}
}
