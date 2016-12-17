package banksys.persistence.client;

import java.util.ArrayList;
import java.util.List;

import banksys.model.Client;
import banksys.model.Operator;
import banksys.persistence.client.exception.ClientCreationException;
import banksys.persistence.client.exception.ClientDeletionException;
import banksys.persistence.client.exception.ClientNotFoundException;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.OperatorInMemoryDAO;

public class ClientInMemoryDAO implements ClientDAO {
	
	private static double CLIENT_IDS = 1;

	private static List<Client> clients = new ArrayList<Client>();

	static{
		ClientInMemoryDAO.clients.add(new Client("Default System client", "client", "12345"));

	}
	
	private static double nextId() {
		return CLIENT_IDS++;
	}
	
	@Override
	public Client create(Client client) throws ClientCreationException {
		
		client.setId(nextId());
		clients.add(client);
		
		return client;
		
	}

	@Override
	public void delete(Double id) throws ClientDeletionException {
		
		Client client = findById(id);
		
		if (client != null) {
			clients.remove(client);
		} else {
			
			throw new ClientDeletionException("Client ID number " + id +
					" not found!");
			
		}
		
	}

	@Override
	public Client retrieve(Double id) throws ClientNotFoundException {
		
		Client client = findById(id);
		
		if (client != null) {
			return client;
		} else {
			
			throw new ClientNotFoundException("Client ID numer " + id +
					" not found!");
			
		}
		
	}

	@Override
	public Client retrieveByUsernameAndPassword(String username,
			String password) throws ClientNotFoundException {
		
		Client client = findByUsernameAndPassword(username, password);
		
		if (client != null) {
			return client;
		} else {
			
			throw new ClientNotFoundException("Client username " + username +
					" not found!");
			
		}
		
	}

	@Override
	public List<Client> list() throws PersistenceException {
		
		if (clients.size() == 0) {
			throw new PersistenceException("No existing client list!");
		}
		
		List<Client> list = new ArrayList<>();
		list.addAll(clients);
		
		return list;
		
	}

	@Override
	public int numberOfClients() throws PersistenceException {
		return clients.size();
	}

	private Client findById(Double id) {
		
		for (Client client : clients) {
			
			if (client.getId().equals(id)) {
				return client;
			}
			
		}
		
		return null;
		
	}

	private Client findByUsernameAndPassword(String username, String password) {
		
		for (Client client : clients) {
			
			if (client.getUsername().equals(username) &&
					client.getPassword().equals(password)) {
				
				return client;
				
			}
			
		}
		
		return null;
		
	}
	
}
