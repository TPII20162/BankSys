package banksys.view;

import java.util.Scanner;

import com.google.inject.Guice;
import com.google.inject.Injector;

import banksys.guice.BankSysModule;
import banksys.persistence.account.AccountDAO;
import banksys.persistence.account.AccountInMemoryDAO;
import banksys.persistence.client.ClientDAO;
import banksys.persistence.client.ClientInMemoryDAO;
import banksys.persistence.operator.OperatorDAO;
import banksys.persistence.operator.OperatorInMemoryDAO;
import banksys.service.ClientServices;
import banksys.service.ClientServicesImpl;
import banksys.service.OperatorServices;
import banksys.service.OperatorServicesImpl;

public class BankSys {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new BankSysModule());
		
		boolean loop = true;
		AccountDAO accountDAO = injector.getInstance(AccountDAO.class);
		ClientDAO clientDAO = injector.getInstance(ClientDAO.class);
		OperatorDAO operatorDAO = injector.getInstance(OperatorDAO.class);
		OperatorServices operatorServices = new OperatorServicesImpl(clientDAO, accountDAO, operatorDAO);
		ClientServices clientServices = new ClientServicesImpl(accountDAO);

		while (loop) {
			switch (loginMenu()) {
			case 1:
				OperatorView.run(operatorServices);
				break;

			case 2:
				ClientView.run(clientServices);
				break;

			case 0:
				loop = false;
				System.out.print("Goodbye and have a nice day!!!");
				break;

			default:
				break;
			}
		}

	}

	private static int loginMenu() {
		System.out.println("================================");
		System.out.println("Wellcome to the Our Bank");
		System.out.println("Main Bank System Terminal");
		System.out.println("================================");
		System.out.println(" [1] To Login on Operator Terminal");
		System.out.println(" [2] To Login on Client Terminal");
		System.out.println(" [0] Exit");
		System.out.println("================================");
		System.out.println("Enter the desired option: ");
		return scanner.nextInt();
	}
}
