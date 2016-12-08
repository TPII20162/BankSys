package banksys.persistence.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import banksys.model.Account;
import banksys.model.AccountType;
import banksys.persistence.Connector;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.account.exception.AccountDeletionException;
import banksys.persistence.account.exception.AccountNotFoundException;
import banksys.persistence.exception.PersistenceException;

public class AccountDatabaseDAO implements AccountDAO{

	@Override
	public Account create(Account account) throws AccountCreationException {
		
		Connection connection = Connector.connect();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO account " +
			"(client_id, number, balance, account_type, bonus) VALUES (?, ?, ?, ?, ?);");
			
			preparedStatement.setDouble(1, account.getClientId());
			preparedStatement.setString(2, account.getNumber());
			preparedStatement.setDouble(3, account.getBalance());
			preparedStatement.setString(4, account.getType().toString());
			preparedStatement.setDouble(5, account.getBonus());

			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		catch(SQLException e){
			throw new AccountCreationException(e.getMessage());
		}
		
		return account;
	}

	@Override
	public void delete(String number) throws AccountDeletionException {
		Connection connection = Connector.connect();
		try {
				PreparedStatement preparedStatement = connection.prepareStatement(
				"DELETE FROM account WHERE number = ?;");
				preparedStatement.setString(1, number);
	
				preparedStatement.executeUpdate();
				preparedStatement.close();
				connection.close();
		}
		catch(SQLException e)
		{
			throw new AccountDeletionException(e.getMessage());
		}
	}

	@Override
	public Account retrieve(String number) throws AccountNotFoundException {
		Account account = null;

		Connection connection = Connector.connect();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE number = ?;");
			preparedStatement.setString(1, number);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
			{
				Double clientId = resultSet.getDouble("client_id");
				Double balance = resultSet.getDouble("balance");
				AccountType type = AccountType.valueOf(resultSet.getString("account_type"));

				account = new Account(number, balance, type, clientId);
				account.setBonus(resultSet.getDouble("bonus"));
			}

			preparedStatement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			throw new AccountNotFoundException(e.getMessage());
		}
		return account;
	}

	@Override
	public void update(Account account) throws AccountNotFoundException {
		Connection connection = Connector.connect();
		try {
				PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE account SET " +
				"client_id = ?, " +
				"balance = ?, " +
				"account_type = ?, " +
				"bonus = ? " +
	 			"WHERE number = ?;");
				preparedStatement.setDouble(1, account.getClientId());
				preparedStatement.setDouble(2, account.getBalance());
				preparedStatement.setString(3, account.getType().toString());
				preparedStatement.setDouble(4, account.getBonus());
				preparedStatement.setString(5, account.getNumber());
	
				preparedStatement.executeUpdate();
				preparedStatement.close();
				connection.close();
		}
		catch(SQLException e)
		{
			throw new AccountNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Account> list() throws PersistenceException {
		Account account = null;
		List<Account> accountList = new ArrayList<Account>();
		Connection connection = Connector.connect();

		try{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account;");
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				String number = resultSet.getString("number");
				Double balance = resultSet.getDouble("balance");
				Double clientId = resultSet.getDouble("client_id");
				AccountType type = AccountType.valueOf(resultSet.getString("account_type"));

				account = new Account(number, balance, type, clientId);
				account.setBonus(resultSet.getDouble("bonus"));
				accountList.add(account);
			}

			preparedStatement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			throw new AccountNotFoundException(e.getMessage());
		}
		
		return accountList;
	}

	@Override
	public List<Account> findByClientId(Double clientId) throws PersistenceException {
		Account account = null;
		List<Account> accountList = new ArrayList<Account>();
		Connection connection = Connector.connect();

		try{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE client_id = ?;");
			preparedStatement.setDouble(1, clientId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				String number = resultSet.getString("number");
				Double balance = resultSet.getDouble("balance");
				AccountType type = AccountType.valueOf(resultSet.getString("account_type"));

				account = new Account(number, balance, type, clientId);
				account.setBonus(resultSet.getDouble("bonus"));
				accountList.add(account);
			}

			preparedStatement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			throw new AccountNotFoundException(e.getMessage());
		}
		
		return accountList;
	}

	@Override
	public int numberOfAccounts() throws PersistenceException {
		Integer numberOfAccounts = 0;
		Connection connection = Connector.connect();

		try{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS NumberOfAccounts FROM account;");
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
			{
				numberOfAccounts = resultSet.getInt("NumberOfAccounts");
			}

			preparedStatement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			throw new AccountNotFoundException(e.getMessage());
		}
		
		return numberOfAccounts;
	}

}
