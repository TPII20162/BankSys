package banksys.persistence.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import banksys.model.User;
import banksys.persistence.Connector;

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
					.prepareStatement("DELETE FROM user WHERE user_id = ?;");
					
			preparedStatement.setDouble(1, id);
			
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}

		
	}

	@Override
	public User retrieve(Double id) throws SQLException {
		Connection connection = Connector.connect();
		User user = null;

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT FROM user WHERE user_id = ?;");

			preparedStatement.setDouble(1, id);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			double user_id = rs.getDouble(1);
			String fullName = rs.getString(2);
			String userName = rs.getString(3);
			String password = rs.getString(4);
			
			user = new User(user_id, fullName, userName, password);
			
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		return user;
	}

	@Override
	public User retrieveByUsernameAndPassword(String username, String password) throws SQLException {
		Connection connection = Connector.connect();
		User user = null;

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT FROM user WHERE user_name = ? AND password = ?;");

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			double user_id = rs.getDouble(1);
			String fullName = rs.getString(2);
			
			user = new User(user_id, fullName, username, password);
			
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return user;
	}

	@Override
	public List<User> list() throws SQLException {
		Connection connection = Connector.connect();
		List<User> users = new ArrayList<User>();		

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM user");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()){
				double user_id = rs.getDouble(1);
				String fullName = rs.getString(2);
				String userName = rs.getString(3);
				String password = rs.getString(4);
				User someUser = new User(user_id, fullName, userName, password);
				
				users.add(someUser);
			}
			
			
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		return users;
	}

	@Override
	public int numberOfUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

}
