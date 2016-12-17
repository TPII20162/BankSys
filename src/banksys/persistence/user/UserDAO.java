package banksys.persistence.user;

import java.sql.SQLException;
import java.util.List;

import banksys.model.User;

public interface UserDAO {
	
	public User create(User user) throws SQLException ;

	public void delete(Double id) throws SQLException ;

	public User retrieve(Double id) throws SQLException ;

	public User retrieveByUsernameAndPassword(String username, String password)  throws SQLException;

	public List<User> list() throws SQLException;

	public int numberOfUsers();

}
