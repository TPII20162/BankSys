package banksys.view;

import java.io.IOException;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	@FXML public void Confirm(ActionEvent event) throws IOException{
		String password = Password.getText();
		String login = Login.getText();
		if(Context.getInstance().isClient()){
			try{
				Context.getInstance().setClientobj(Context.getClientServices().doLogin(login, password));
				openClientWindow(event);
		        
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
				openOperatorWindow(event);
				}
			catch(OperationServiceException e){
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Login");
				dialogoInfo.setContentText("Login ou Senha Incorretos.\n Tente Novamente!");
				dialogoInfo.showAndWait();
			}
		}	
	}
	@FXML private void openClientWindow(ActionEvent event) throws IOException{
		Parent ex_parent = FXMLLoader.load(getClass().getResource("ClientWindow.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		Context.getInstance().setClient(true);
		Context.getInstance().setOperator(false);
		app_stage.show();
	}
	
	@FXML private void openOperatorWindow(ActionEvent event) throws IOException{
		Parent ex_parent = FXMLLoader.load(getClass().getResource("OperatorWindown.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		Context.getInstance().setClient(true);
		Context.getInstance().setOperator(false);
		app_stage.show();
	}
		
}
