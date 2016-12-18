package banksys.view;

import java.io.IOException;

import banksys.service.ClientServices;
import banksys.service.exception.ClientServiceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerClientOperations {
	@FXML TextField accountNumberText;
	@FXML TextField creditText;
	@FXML TextField debitText;
	ClientServices cs;
	
	@FXML public void searchAccount(ActionEvent event) throws IOException{
		String accountNumber = accountNumberText.getText();
		try {
			
			Context.getInstance().getClientServices().retriveAccount(accountNumber);
			if(Context.getInstance().isCredit()){
				openCreditWindow(event);
			}
			else if(Context.getInstance().isDebit()){
				openDebitWindow(event);
			}
			else if(Context.getInstance().isRetrieve()){
				showBalance(accountNumber);
			}
			
		} catch (ClientServiceException e) {
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
	        dialogoInfo.setTitle("Conta inválida");
	        dialogoInfo.setContentText("Esta conta não existe");
	        dialogoInfo.showAndWait();
		}
		
	}
	
	@FXML public void cancelOperation(ActionEvent event) throws IOException{
		Parent ex_parent = FXMLLoader.load(getClass().getResource("ClientWindow.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		app_stage.show();
	}
	
	@FXML private void openCreditWindow(ActionEvent event) throws IOException{
		Parent ex_parent = FXMLLoader.load(getClass().getResource("Credit.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("Creditar");
		app_stage.show();
	}
	@FXML private void openDebitWindow(ActionEvent event) throws IOException{
		Parent ex_parent = FXMLLoader.load(getClass().getResource("Debit.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("Debitar");
		app_stage.show();
	}
	@FXML private void showBalance(String accountNumber) throws IOException{
		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Saldo");
        try {
			Context.getInstance();
			dialogoInfo.setContentText("Saldo: " + Context.getInstance().getClientServices().doRetrieveBalance(Context.getInstance().getClientobj(), accountNumber));
		} catch (ClientServiceException e) {}
        dialogoInfo.showAndWait();
	}
	
	@FXML public void doCredit(ActionEvent event){
		String amount = creditText.getText();
		try {
			Context.getInstance().getClientServices().doCredit(Context.getInstance().getClientServices().retriveAccount(accountNumberText.getText()), Double.parseDouble(amount));
			Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
	        dialogoInfo.setTitle("Sucesso");
	        dialogoInfo.setContentText("Valor creditado com sucesso");
	        dialogoInfo.showAndWait();
		} catch (NumberFormatException | ClientServiceException e) {
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
	        dialogoInfo.setTitle("Error");
	        dialogoInfo.setContentText("Quantidade inválida");
	        dialogoInfo.showAndWait();
		}
	}
}
