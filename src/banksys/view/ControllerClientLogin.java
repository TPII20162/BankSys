package banksys.view;

import banksys.model.Client;
import banksys.model.Operator;
import banksys.persistence.account.AccountDAO;
import banksys.persistence.account.AccountInMemoryDAO;
import banksys.persistence.client.ClientDAO;
import banksys.persistence.client.ClientInMemoryDAO;
import banksys.persistence.client.exception.ClientNotFoundException;
import banksys.persistence.operator.OperatorDAO;
import banksys.persistence.operator.OperatorInMemoryDAO;
import banksys.service.ClientServices;
import banksys.service.ClientServicesImpl;
import banksys.service.OperatorServices;
import banksys.service.OperatorServicesImpl;
import banksys.service.exception.ClientServiceException;
import banksys.service.exception.OperationServiceException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerClientLogin {
	@FXML PasswordField Password = new PasswordField();
	@FXML TextField Login = new TextField();
	OperatorServices operatorServices;
	AccountDAO accountDAO;
	ClientDAO clientDAO;
	OperatorDAO operatorDAO;
	ClientServices clientServices;
	Client client;
	Operator operator;
	@FXML private void initialize(){
		accountDAO = new AccountInMemoryDAO();
		clientDAO = new ClientInMemoryDAO();
		operatorDAO = new OperatorInMemoryDAO();
		operatorServices = new OperatorServicesImpl(clientDAO, accountDAO, operatorDAO);
		clientServices = new ClientServicesImpl(accountDAO);
	}
	@FXML public void Confirm(){
		String password = Password.getText();
		String login = Login.getText();
		try{
			client = clientServices.doLogin(login, password);
			operator = operatorServices.doLogin(login, password);
			Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
	        dialogoInfo.setTitle("Login");
	        dialogoInfo.setContentText("Login Efetuado com sucesso!");
	        dialogoInfo.showAndWait();
			}
		catch(ClientServiceException | OperationServiceException e){
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
	        dialogoInfo.setTitle("Login");
	        dialogoInfo.setContentText("Login ou Senha Incorretos.\n Tente Novamente!");
	        dialogoInfo.showAndWait();
		}
	}
		
}
