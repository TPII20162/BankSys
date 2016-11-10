package banksys.model;

public class User {

	private Double id;

	private String fullName;

	private String username;

	private String password;

	public User(Double id, String fullName, String username, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.password = password;
	}

	public User(String fullName, String username, String password) {
		this(0.0, fullName, username, password);
	}

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
