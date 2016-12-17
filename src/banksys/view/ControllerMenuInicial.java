package banksys.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class ControllerMenuInicial {
	@FXML private void initialize(){
		
	}
	@FXML public void goToClient(ActionEvent event) throws IOException {
		Parent ex_parent = FXMLLoader.load(getClass().getResource("ClientLogin.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		app_stage.show();
	}
	
	@FXML public void goToAdmin() {}

	@FXML public void Sair() {
		System.exit(0);
	}

}
