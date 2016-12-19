package banksys.view;

	import banksys.persistence.account.AccountInMemoryDAO;
import banksys.persistence.client.ClientInMemoryDAO;
import banksys.persistence.operator.OperatorInMemoryDAO;
import banksys.service.ClientServicesImpl;
import banksys.service.OperatorServicesImpl;
import javafx.application.Application;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.layout.Pane;
	import javafx.stage.Stage;
	public class BankSysGraphicInterface extends Application {
		public void start(Stage primaryStage) {
			Context.getInstance().setAccountDAO(new AccountInMemoryDAO());
			Context.getInstance().setClientDAO(new ClientInMemoryDAO());
			Context.getInstance().setOperatorDAO(new OperatorInMemoryDAO());
			Context.getInstance().setOperatorServices(new OperatorServicesImpl(Context.getInstance().getClientDAO(), Context.getInstance().getAccountDAO(), Context.getInstance().getOperatorDAO()));
			Context.getInstance().setClientServices(new ClientServicesImpl(Context.getInstance().getAccountDAO()));
			try{
				Parent root = FXMLLoader.load(getClass().getResource("MenuInicial.fxml"));
				Scene scene = new Scene(root,632,418);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("BankSys");
				primaryStage.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public static void main(String[] args) {
			launch(args);
			}
		}