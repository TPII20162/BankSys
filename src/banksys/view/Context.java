package banksys.view;

import banksys.model.Client;
import banksys.model.Operator;
import banksys.persistence.account.AccountDAO;
import banksys.persistence.client.ClientDAO;
import banksys.persistence.operator.OperatorDAO;
import banksys.service.ClientServices;
import banksys.service.OperatorServices;

public class Context {
	private boolean operator;
	private boolean client;
	private OperatorServices operatorServices;
	private AccountDAO accountDAO;
	private ClientDAO clientDAO;
	private OperatorDAO operatorDAO;
	private static ClientServices clientServices;
	private Client clientobj;
	private Operator operatorobj;

	public OperatorServices getOperatorServices() {
		return operatorServices;
	}
	public void setOperatorServices(OperatorServices operatorServices) {
		this.operatorServices = operatorServices;
	}
	public AccountDAO getAccountDAO() {
		return accountDAO;
	}
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	public ClientDAO getClientDAO() {
		return clientDAO;
	}
	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
	public OperatorDAO getOperatorDAO() {
		return operatorDAO;
	}
	public void setOperatorDAO(OperatorDAO operatorDAO) {
		this.operatorDAO = operatorDAO;
	}
	public static ClientServices getClientServices() {
		return clientServices;
	}
	public void setClientServices(ClientServices clientServices) {
		this.clientServices = clientServices;
	}
	public Client getClientobj() {
		return clientobj;
	}
	public void setClientobj(Client clientobj) {
		this.clientobj = clientobj;
	}
	public Operator getOperatorobj() {
		return operatorobj;
	}
	public void setOperatorobj(Operator operatorobj) {
		this.operatorobj = operatorobj;
	}
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
