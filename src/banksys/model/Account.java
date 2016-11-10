package banksys.model;

public class Account {

	private String number;

	private Double balance;

	private AccountType type;

	private Double clientId;
	
	private Double bonus;

	public Account(String number, AccountType type) {
		this(number, 0.0, type);
	}

	public Account(AccountType type) {
		this("", 0.0, type, 0.0);
	}

	public Account(AccountType type, Double clientId) {
		this("", 0.0, type, clientId);
	}

	public Account(String number, Double balance, AccountType type) {
		this(number, balance, type, 0.0);
	}

	public Account(String number, Double balance, AccountType type, Double clientId) {
		this.number = number;
		this.balance = balance;
		this.type = type;
		this.clientId = clientId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public Double getClientId() {
		return clientId;
	}

	public void setClientId(Double clientId) {
		this.clientId = clientId;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

}