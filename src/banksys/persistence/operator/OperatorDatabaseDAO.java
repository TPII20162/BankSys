package banksys.persistence.operator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import banksys.model.Operator;
import banksys.persistence.Connector;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.exception.OperatorCreationException;
import banksys.persistence.operator.exception.OperatorDeletionException;
import banksys.persistence.operator.exception.OperatorNotFoundException;
import banksys.persistence.user.UserDatabaseDAO;

public class OperatorDatabaseDAO implements OperatorDAO {

	UserDatabaseDAO  userDatabase = new UserDatabaseDAO();
	
	@Override
	public Operator create(Operator operator) throws OperatorCreationException {
		Connection connection = Connector.connect();

		try {
			userDatabase.create(operator);
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO operator "
							+ "(user_id) VALUES (?);");

			preparedStatement.setDouble(1, operator.getId());

			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new OperatorCreationException(e.getMessage());
		}

		return operator;

	}

	@Override
	public void delete(Double id) throws OperatorDeletionException {

		Connection connection = Connector.connect();

		try {
			userDatabase.delete(id);
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM operator WHERE user_id = ?;");
			preparedStatement.setDouble(1, id);

			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new OperatorDeletionException(e.getMessage());
		}

	}

	@Override
	public Operator retrieve(Double id) throws OperatorNotFoundException {
		return null;
	}

	@Override
	public Operator retrieveByUsernameAndPassword(String username,
			String password) throws OperatorNotFoundException {
		return null;
	}

	@Override
	public List<Operator> list() throws PersistenceException {
		return null;
	}

}
