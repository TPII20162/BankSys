package banksys.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import banksys.model.AccountType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import banksys.model.AccountType;
import banksys.service.exception.OperationServiceException;
import javafx.event.ActionEvent;

public class ControllerOperatorOperations {

	@FXML TextField fullName;
	@FXML PasswordField password;
	@FXML PasswordField conf_pass;
	@FXML TextField username;
	@FXML TextField AccountToRemove;
	@FXML TextField ClientToAdd;
	@FXML TextField AccountTipo;
	@FXML TextField AccountToEarnInterest;
	@FXML TextField AccountToEarnBonus;

	@FXML public void OpenCreateWindow() {}

	@FXML public void doCreateAccount() {
		String fullname = fullName.getText();
		String userName = username.getText();
		String PassWord = password.getText();
		String Conf_pass = conf_pass.getText();
		try {
			Context.getInstance().getOperatorServices().doNewClient(Context.getInstance().getOperatorobj(), fullname, userName, PassWord, Conf_pass);
			Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
	        dialogoInfo.setTitle("Criar Cliente");
	        dialogoInfo.setContentText("Cliente "+userName+" criado com sucesso!");
	        dialogoInfo.showAndWait();
		} catch (OperationServiceException e) {
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
	        dialogoInfo.setTitle("Criar Cliente");
	        dialogoInfo.setContentText("Erro ao criar o Cliente.\n Tente Novamente!");
	        dialogoInfo.showAndWait();

		}
	}

	@FXML public void RemoveAccount() {
		String accountNumber = AccountToRemove.getText();
		try {
			Context.getInstance().getOperatorServices().doCloseAccount(Context.getInstance().getOperatorobj(), accountNumber);
			Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
	        dialogoInfo.setTitle("Remover Conta");
	        dialogoInfo.setContentText("Conta "+accountNumber+" excluida com sucesso!");
	        dialogoInfo.showAndWait();
		} catch (OperationServiceException e) {
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
	        dialogoInfo.setTitle("Criar Cliente");
	        dialogoInfo.setContentText("Erro ao excluir Conta.\n Tente Novamente!");
	        dialogoInfo.showAndWait();
		}
	}

	@FXML public void CreateAccount() {
		String accountType = AccountTipo.getText();
		String clientToAdd = ClientToAdd.getText();
		Double clientId = Double.parseDouble(clientToAdd);
		AccountType type = AccountType.ORDINARY;
		if(accountType.equals("Savings")){
			type = AccountType.SAVINGS;
		}
		if(accountType.equals("Tax")){
			type = AccountType.TAX;
		}
		if(accountType.equals("Special")){
			type = AccountType.SPECIAL;
		}
		try {
			Context.getInstance().getOperatorServices().doNewAccount(Context.getInstance().getOperatorobj(), clientId, type);
			Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
	        dialogoInfo.setTitle("Criar Conta");
	        dialogoInfo.setContentText("Conta criada com sucesso!");
	        dialogoInfo.showAndWait();
		} catch (OperationServiceException e) {
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
	        dialogoInfo.setTitle("Criar Conta");
	        dialogoInfo.setContentText("Erro ao criar Conta.\n Tente Novamente!");
	        dialogoInfo.showAndWait();
		}
		
		
	}

	@FXML public void EarnInterest() {
		String accountNumber = AccountToEarnInterest.getText();
		try {
			Context.getInstance().getOperatorServices().doEarnInterest(Context.getInstance().getOperatorobj(), accountNumber);
			Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
	        dialogoInfo.setTitle("Gerar Juros");
	        dialogoInfo.setContentText("Juros gerados com sucesso com sucesso!");
	        dialogoInfo.showAndWait();
		} catch (OperationServiceException e) {
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
	        dialogoInfo.setTitle("Gerar Juros");
	        dialogoInfo.setContentText("Erro ao gerar Juros.\n Tente Novamente!");
	        dialogoInfo.showAndWait();
		}
	}

	@FXML public void EarnBonus() {
		String accountNumber = AccountToEarnBonus.getText();
		try {
			Context.getInstance().getOperatorServices().doEarnBonus(Context.getInstance().getOperatorobj(), accountNumber);
			Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
	        dialogoInfo.setTitle("Gerar Bonus");
	        dialogoInfo.setContentText("Bonus gerados com sucesso com sucesso!");
	        dialogoInfo.showAndWait();
		} catch (OperationServiceException e) {
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
	        dialogoInfo.setTitle("Gerar Bonus");
	        dialogoInfo.setContentText("Erro ao gerar Bonus.\n Tente Novamente!");
	        dialogoInfo.showAndWait();
		}
	}

	@FXML public void Voltar(ActionEvent event) throws IOException {
		Parent ex_parent = FXMLLoader.load(getClass().getResource("OperatorWindown.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		app_stage.show();
	}

}
