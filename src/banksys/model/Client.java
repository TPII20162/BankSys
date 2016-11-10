package banksys.model;

public class Client extends User {

	public Client(Double id, String fullName, String username, String password) {
		super(id, fullName, username, password);
	}

	public Client(String fullName, String username, String password) {
		super(fullName, username, password);
	}

}
