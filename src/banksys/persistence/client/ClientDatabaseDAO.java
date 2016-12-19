package banksys.persistence.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import banksys.model.Client;
import banksys.model.Operator;
import banksys.model.User;
import banksys.persistence.Connector;
import banksys.persistence.client.exception.ClientCreationException;
import banksys.persistence.client.exception.ClientDeletionException;
import banksys.persistence.client.exception.ClientNotFoundException;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.user.UserDatabaseDAO;

public class ClientDatabaseDAO implements ClientDAO {
	
	UserDatabaseDAO userDatabase = new UserDatabaseDAO();
	
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
	public Client retrieveByUsernameAndPassword(String username, String password) throws ClientNotFoundException {
		Connection connection = Connector.connect();
		Client client = null;
		
		try {
			User user = userDatabase.retrieveByUsernameAndPassword(username, password);
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT FROM client WHERE user_id = ?;");

			preparedStatement.setDouble(1, user.getId());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			double user_id = rs.getDouble(1);
			
			client = new Client(user_id, user.getFullName(), user.getUsername(), user.getPassword());					
			preparedStatement.close();
		}		catch(SQLException e)
		{
			throw new ClientNotFoundException(e.getMessage());
		}
		return client;
	}

	@Override
	public List<Client> list() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfClients() throws ClientNotFoundException {
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
			throw new ClientNotFoundException(e.getMessage());
		}
		
		return numberOfClients;
	}

}
