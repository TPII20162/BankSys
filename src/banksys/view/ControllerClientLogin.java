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
	@FXML PasswordField Password;
	@FXML TextField Login;
		@FXML private void initialize(){
		Context.getInstance().setAccountDAO(new AccountInMemoryDAO());
		Context.getInstance().setClientDAO(new ClientInMemoryDAO());
		Context.getInstance().setOperatorDAO(new OperatorInMemoryDAO());
		Context.getInstance().setOperatorServices(new OperatorServicesImpl(Context.getInstance().getClientDAO(), Context.getInstance().getAccountDAO(), Context.getInstance().getOperatorDAO()));
		Context.getInstance().setClientServices(new ClientServicesImpl(Context.getInstance().getAccountDAO()));
	}
	@FXML public void Confirm(){
		String password = Password.getText();
		String login = Login.getText();
		if(Context.getInstance().isClient()){
			try{
				Context.getInstance().setClientobj(Context.getClientServices().doLogin(login, password));
				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		        dialogoInfo.setTitle("Login");
		        dialogoInfo.setContentText("Login Efetuado com sucesso!");
		        dialogoInfo.showAndWait();
				}
			catch (ClientServiceException e) {
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
		        dialogoInfo.setTitle("Login");
		        dialogoInfo.setContentText("Login ou Senha Incorretos.\n Tente Novamente!");
		        dialogoInfo.showAndWait();
			}
		}
		if(Context.getInstance().isOperator()){
			try{
				Context.getInstance().setOperatorobj(Context.getInstance().getOperatorServices().doLogin(login, password));
				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
				dialogoInfo.setTitle("Login");
				dialogoInfo.setContentText("Login Efetuado com sucesso!");
				dialogoInfo.showAndWait();
				}
			catch(OperationServiceException e){
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Login");
				dialogoInfo.setContentText("Login ou Senha Incorretos.\n Tente Novamente!");
				dialogoInfo.showAndWait();
			}
		}	
	}
		
}
