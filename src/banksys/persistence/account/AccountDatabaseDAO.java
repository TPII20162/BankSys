package banksys.persistence.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			connection.close();
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
		return null;
	}

	@Override
	public void update(Account account) throws AccountNotFoundException {
		Connection connection = Connector.connect();
		try {
				PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE account SET " +
				"number = ?, " +
				"balance = ?, " +
				"account_type = ?, " +
				"bonus = ? " +
	 			"WHERE client_id = ?;");
				preparedStatement.setString(1, account.getNumber());
				preparedStatement.setDouble(2, account.getBalance());
				preparedStatement.setString(3, account.getType().toString());
				preparedStatement.setDouble(4, account.getBonus());
				preparedStatement.setDouble(5, account.getClientId());
	
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
		return null;
	}

	@Override
	public List<Account> findByClientId(Double clientId) throws PersistenceException {
		return null;
	}

	@Override
	public int numberOfAccounts() throws PersistenceException {
		return 0;
	}

}
