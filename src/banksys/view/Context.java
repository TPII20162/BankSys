package banksys.view;

public class Context {
	private boolean operator;
	private boolean client;
	private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }
	public boolean isOperator() {
		return operator;
	}
	public void setOperator(boolean operator) {
		this.operator = operator;
	}
	public boolean isClient() {
		return client;
	}
	public void setClient(boolean client) {
		this.client = client;
	}
}
