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
			"(client_id, number, balance, account_type, bonus) " +
			"VALUES (?, ?, ?, ?, ?);");
			preparedStatement.setDouble(1, account.getClientId());
			preparedStatement.setString(2, account.getNumber());
			preparedStatement.setDouble(3, account.getBalance());
			preparedStatement.setString(4, account.getType().toString());
			preparedStatement.setDouble(5, account.getBonus());

			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			}
		catch(SQLException e)
		{
			throw new AccountCreationException(e.getMessage());
		}
		
		return account;
	}
	
	public static void main(String[] args) {
		Account account = new Account("123", 72000.0, AccountType.ORDINARY, 26.0);
		account.setBonus(0.02);
		AccountDatabaseDAO accountDatabaseDAO = new AccountDatabaseDAO();
		try {
			accountDatabaseDAO.create(account);
		} catch (AccountCreationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String number) throws AccountDeletionException {
		
	}

	@Override
	public Account retrieve(String number) throws AccountNotFoundException {
		return null;
	}

	@Override
	public void update(Account account) throws AccountNotFoundException {
		
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
