package banksys.persistence.operator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import banksys.model.Operator;
import banksys.persistence.Connector;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.exception.OperatorCreationException;
import banksys.persistence.operator.exception.OperatorDeletionException;
import banksys.persistence.operator.exception.OperatorNotFoundException;
import banksys.persistence.user.UserDatabaseDAO;

public class OperatorDatabaseDAO implements OperatorDAO {

	UserDatabaseDAO userDatabase = new UserDatabaseDAO();
	
	@Override
	public Operator create(Operator operator) throws OperatorCreationException {
		Connection connection = Connector.connect();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO operator "
							+ "(operator_id, full_name, user_name, password) VALUES (?,?,?,?);");

			preparedStatement.setDouble(1, operator.getId());
			preparedStatement.setString(2, operator.getFullName());
			preparedStatement.setString(3, operator.getUsername());
			preparedStatement.setString(4, operator.getPassword());
			
			
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
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM operator WHERE operator_id = ?;");
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
		Connection connection = Connector.connect();
		Operator operator = null;
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM operator WHERE operator_id = ?;");

			preparedStatement.setDouble(1, id);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			double user_id = rs.getDouble(1);
			String fullName = rs.getString(2);
			String userName = rs.getString(3);
			String password = rs.getString(4);
			
			operator = new Operator(user_id, fullName, userName, password);
			
			preparedStatement.close();
		} catch (SQLException e) {
			throw new OperatorNotFoundException(e.getMessage());
		}
		return operator;
	}

	@Override
	public Operator retrieveByUsernameAndPassword(String username,
			String password) throws OperatorNotFoundException {
		Connection connection = Connector.connect();
		Operator operator = null;
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM operator WHERE user_name = ? AND password = ?;");

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			double user_id = rs.getDouble(1);
			String fullName = rs.getString(2);
			
			operator = new Operator(user_id, fullName, username, password);
			
			preparedStatement.close();
		} catch (SQLException e) {
			throw new OperatorNotFoundException(e.getMessage());
		}
		return operator;
	}

	@Override
	public List<Operator> list() throws PersistenceException {
		Connection connection = Connector.connect();
		List<Operator> operators = new ArrayList<Operator>();
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM operator");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()){
				double user_id = rs.getDouble(1);
				String fullName = rs.getString(2);
				String userName = rs.getString(3);
				String password = rs.getString(4);
				Operator operator = new Operator(user_id, fullName, userName, password);
				
				operators.add(operator);
			}
			
			preparedStatement.close();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return operators;
	}

}
