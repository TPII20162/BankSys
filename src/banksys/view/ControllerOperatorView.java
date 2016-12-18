package banksys.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerOperatorView {


	@FXML public void OpenCreateWindow(ActionEvent event) throws IOException {
		Parent ex_parent = FXMLLoader.load(getClass().getResource("CreateClient.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		app_stage.show();
	}

	@FXML public void OpenRemoveAccount(ActionEvent event) throws IOException {
		Parent ex_parent = FXMLLoader.load(getClass().getResource("RemoveAccount.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		app_stage.show();
	}

	@FXML public void OpenCreateAccount(ActionEvent event) throws IOException {
		Parent ex_parent = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		app_stage.show();
	}

	@FXML public void OpenEarnInterest(ActionEvent event) throws IOException {
		Parent ex_parent = FXMLLoader.load(getClass().getResource("EarnInterest.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		app_stage.show();
	}

	@FXML public void OpenEarBonus(ActionEvent event) throws IOException {
		Parent ex_parent = FXMLLoader.load(getClass().getResource("EarnBonus.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		app_stage.show();
	}

	@FXML public void Sair() {
		System.exit(0);
	}




}
