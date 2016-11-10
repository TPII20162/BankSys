package banksys.model;

public class Operator extends User {

	public Operator(Double id, String fullName, String username, String password) {
		super(id, fullName, username, password);
	}

	public Operator(String fullName, String username, String password) {
		super(fullName, username, password);
	}

}
