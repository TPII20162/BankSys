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
			throws SQLException {
		Connection connection = Connector.connect();
		Client client = null;

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM client WHERE user_id = ?;");

			preparedStatement.setString(1, user_id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				Double clientId = resultSet.getDouble("user_id");
				client = new Client(clientId);
				
			}
			preparedStatement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			throw new SQLException(e.getMessage());
		}
		return client;
	}

	@Override
	public List<Client> list() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfClients() throws SQLException {
		Integer numberOfClients = 0;
		Connection connection = Connector.connect();

		try{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS NumberOfClients FROM client;");
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
			{
				numberOfClients = resultSet.getInt("NumberOfClients");
			}

			preparedStatement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			throw new SQLException(e.getMessage());
		}
		
		return numberOfClients;
	}

}
