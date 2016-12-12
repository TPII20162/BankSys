package banksys.persistence.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import banksys.model.User;
import banksys.persistence.Connector;
import banksys.persistence.operator.exception.OperatorCreationException;

public class UserDatabaseDAO implements UserDAO {

	@Override
	public User create(User user) throws SQLException {
		Connection connection = Connector.connect();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO user "
							+ "(user_id, full_name, user_name, password) VALUES (?,?,?,?);");

			preparedStatement.setDouble(1, user.getId());
			preparedStatement.setString(2, user.getFullName());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());
			
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

		return user;
	}

	@Override
	public void delete(Double id) throws SQLException {
		Connection connection = Connector.connect();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM user WHERE id = ?;");

			preparedStatement.setDouble(1, id);
			
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

		
	}

	@Override
	public User retrieve(Double id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User retrieveByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

}
