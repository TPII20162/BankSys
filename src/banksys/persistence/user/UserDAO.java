package banksys.persistence.user;

import java.sql.SQLException;
import java.util.List;

import banksys.model.User;

public interface UserDAO {
	
	public User create(User user) throws SQLException ;

	public void delete(Double id) ;

	public User retrieve(Double id) ;

	public User retrieveByUsernameAndPassword(String username, String password);

	public List<User> list();

	public int numberOfUsers();

}
