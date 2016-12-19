package banksys.persistence.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.model.Client;
import banksys.model.Operator;
import banksys.model.User;
import banksys.persistence.Connector;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.client.exception.ClientCreationException;
import banksys.persistence.client.exception.ClientDeletionException;
import banksys.persistence.client.exception.ClientNotFoundException;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.exception.OperatorDeletionException;
import banksys.persistence.operator.exception.OperatorNotFoundException;

public class ClientDatabaseDAO implements ClientDAO {
	
	
	@Override
	public Client create(Client client) throws ClientCreationException {
		Connection connection = Connector.connect();
		try{
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO client " +
					"(client_id, full_name, user_name, password) VALUES (?, ?, ?, ?);");
			
			preparedStatement.setDouble(1, client.getId());
			preparedStatement.setString(2, client.getFullName());
			preparedStatement.setString(3, client.getUsername());
			preparedStatement.setString(4, client.getPassword());

			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		catch(SQLException e){
			throw new ClientCreationException(e.getMessage());
		}
		
		return client;
	}

	@Override
	public void delete(Double id) throws ClientDeletionException {

		Connection connection = Connector.connect();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM client WHERE client_id = ?;");
			preparedStatement.setDouble(1, id);

			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new ClientDeletionException(e.getMessage());
		}


	}

	@Override
	public Client retrieve(Double id) throws ClientNotFoundException {
		Connection connection = Connector.connect();
		Client client = null;
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM client WHERE client_id = ?;");

			preparedStatement.setDouble(1, id);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			double user_id = rs.getDouble(1);
			String fullName = rs.getString(2);
			String userName = rs.getString(3);
			String password = rs.getString(4);
			
			client = new Client(user_id, fullName, userName, password);
			
			preparedStatement.close();
		} catch (SQLException e) {
			throw new ClientNotFoundException(e.getMessage());
		}
		return client;
	}

	@Override
	public Client retrieveByUsernameAndPassword(String username, String password) throws ClientNotFoundException {
		Connection connection = Connector.connect();
		Client client = null;
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM client WHERE user_name = ? AND password = ?;");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			Double id = resultSet.getDouble("client_id");
			String fullname = resultSet.getString("full_name");
			String uname = resultSet.getString("user_name");
			String pword = resultSet.getString("password");
			
			client = new Client(id, fullname, username, password);			
			preparedStatement.close();
		}		catch(SQLException e)
		{
			throw new ClientNotFoundException(e.getMessage());
		}
		return client;
	}

	@Override
	public List<Client> list() throws ClientNotFoundException {
		Client client = null;
		List<Client> clientList = new ArrayList<Client>();
		Connection connection = Connector.connect();
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client;");
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				Double id = resultSet.getDouble("client_id");
				String fullname = resultSet.getString("full_name");
				String username = resultSet.getString("user_name");
				String password = resultSet.getString("password");
				
				client = new Client(id, fullname, username, password);
				clientList.add(client);
			}

			preparedStatement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			throw new ClientNotFoundException(e.getMessage());
		}
		
		return clientList;
		
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
