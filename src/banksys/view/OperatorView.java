package banksys.view;

import java.util.Scanner;

import banksys.model.AccountType;
import banksys.model.Client;
import banksys.model.Operator;
import banksys.service.OperatorServices;
import banksys.service.exception.OperationServiceException;

public class OperatorView {

	private static Scanner scanner = new Scanner(System.in);

	public static void run(OperatorServices operatorServices) {

		boolean loop = true;
		Operator operator = null;

		while (operator == null) {
			System.out.print("Enter the username: ");
			String username = scanner.next();
			System.out.print("Enter the password: ");
			String password = scanner.next();
			try {
				operator = operatorServices.doLogin(username, password);
			} catch (OperationServiceException ose) {
				System.out.println(ose.getMessage());
				System.out.println("Returning to main menu...\n");
				return;
			}
		}
		System.out.println("Welcome operator " + operator.getFullName());

		while (loop) {
			switch (mainMenu()) {
			case 1:
				System.out.print("Enter the client full name: ");
				String fullName = scanner.next();
				System.out.print("Enter the client username: ");
				String username = scanner.next();
				System.out.print("Enter the client password: ");
				String password = scanner.next();
				System.out.print("Enter the password confirmation: ");
				String password2 = scanner.next();
				
				try {
					Client client = operatorServices.doNewClient(operator, fullName, username, password, password2);
					System.out.println("Client creation successfully! Client ID number: " + client.getId());
				} catch (OperationServiceException ose) {
					System.out.println(ose.getMessage());
				}
				break;

			case 2:
				AccountType type = AccountType.ORDINARY;
				switch (newAccountMenu()) {
				case 1:
					type = AccountType.ORDINARY;
					break;
				case 2:
					type = AccountType.SPECIAL;
					break;
				case 3:
					type = AccountType.SAVINGS;
					break;
				case 4:
					type = AccountType.TAX;
					break;

				default:
					System.out.println("Invalid option!!!!");
					break;
				}

				System.out.println("Enter the client ID number: ");
				try {
					operatorServices.doNewAccount(operator, scanner.nextDouble(), type);
					System.out.println("Account creation successfully!");
				} catch (OperationServiceException ose) {
					System.out.println(ose.getMessage());
				}
				break;

			case 3:
				System.out.println("Enter the account number: ");
				try {
					operatorServices.doCloseAccount(operator, scanner.next());
					System.out.println("Account closed successfully!");
				} catch (OperationServiceException ose) {
					System.out.println(ose.getMessage());
				}
				break;

			case 4:
				System.out.println("Enter the account number: ");
				try {
					operatorServices.doEarnInterest(operator, scanner.next());
					System.out.println("Earn interest operation performed successfully!");
				} catch (OperationServiceException ose) {
					System.out.println(ose.getMessage());
				}

				break;
			case 5:
				System.out.println("Enter the account number: ");
				try {
					operatorServices.doEarnBonus(operator, scanner.next());
					System.out.println("Earn bonus operation performed successfully!");
				} catch (OperationServiceException ose) {
					System.out.println(ose.getMessage());
				}
				break;

			case 0:
				System.out.println("Goodbye and have a nice day!!!");
				loop = false;
				break;

			default:
				break;
			}
		}
	}

	private static int mainMenu() {
		System.out.println("================================");
		System.out.println("Wellcome to the Our Bank");
		System.out.println("Operator System Terminal");
		System.out.println("================================");
		System.out.println(" [1] New Client");
		System.out.println(" [2] New Account");
		System.out.println(" [3] Close Account");
		System.out.println(" [4] Earn Iterest");
		System.out.println(" [5] Earn Bonus");
		System.out.println(" [0] Exit");
		System.out.println("================================");
		System.out.println("Enter the desired option: ");
		return scanner.nextInt();

	}

	private static int newAccountMenu() {
		System.out.println("================================");
		System.out.println("Add New Account");
		System.out.println("================================");
		System.out.println(" [1] Ordinary");
		System.out.println(" [2] Special");
		System.out.println(" [3] Savings");
		System.out.println(" [4] Tax");
		System.out.println("================================");
		System.out.println("Enter the desired option: ");
		return scanner.nextInt();
	}

}