package banksys.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerClientWindow {
	@FXML public void toCredit(ActionEvent event) throws IOException{
		Parent ex_parent = FXMLLoader.load(getClass().getResource("AccountNumberWindow.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		Context.getInstance().setCredit(true);
		Context.getInstance().setDebit(false);
		Context.getInstance().setRetrieve(false);
		app_stage.show();
	}
	
	@FXML public void toDebit(ActionEvent event) throws IOException{
		Parent ex_parent = FXMLLoader.load(getClass().getResource("AccountNumberWindow.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		Context.getInstance().setCredit(false);
		Context.getInstance().setDebit(true);
		Context.getInstance().setRetrieve(false);
		app_stage.show();
	}
	
	@FXML public void toTransfer(ActionEvent event) throws IOException{
		Parent ex_parent = FXMLLoader.load(getClass().getResource("TransferWindow.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		app_stage.show();
	}
	
	@FXML public void toRetrieve(ActionEvent event) throws IOException{
		Parent ex_parent = FXMLLoader.load(getClass().getResource("AccountNumberWindow.fxml"));
        Scene home_page_scene = new Scene(ex_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //app_stage.hide();
        app_stage.setScene(home_page_scene);
		app_stage.setTitle("BankSys");
		Context.getInstance().setCredit(false);
		Context.getInstance().setDebit(false);
		Context.getInstance().setRetrieve(true);
		app_stage.show();
	}
}
