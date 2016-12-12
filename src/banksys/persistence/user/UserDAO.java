package banksys.persistence.user;

import java.util.List;

import banksys.model.User;

public interface UserDAO {
	
	public User create(User user) ;

	public void delete(Double id) ;

	public User retrieve(Double id) ;

	public User retrieveByUsernameAndPassword(String username, String password);

	public List<User> list();

	public int numberOfUsers();

}
