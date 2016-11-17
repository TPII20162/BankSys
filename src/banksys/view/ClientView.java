package banksys.view;

import java.util.List;
import java.util.Scanner;

import banksys.model.Account;
import banksys.model.Client;
import banksys.service.ClientServices;
import banksys.service.exception.ClientServiceException;

public class ClientView {

	private static Scanner scanner = new Scanner(System.in);

	public static void run(ClientServices clientServices) {
		boolean loop = true;
		Client client = null;

		while (client == null) {
			System.out.print("Enter the username: ");
			String username = scanner.next();
			System.out.print("Enter the password: ");
			String password = scanner.next();
			try {
				client = clientServices.doLogin(username, password);
			} catch (ClientServiceException cse) {
				System.out.println(cse.getMessage());
				System.out.println("Returning to main menu...\n");
				return;
			}
		}
		System.out.println("Welcome client " + client.getFullName());

		while (loop) {
			switch (mainMenu()) {
			case 1:
				System.out.println("Enter the account number: ");
				String number = scanner.next();
				System.out.println("Enter the amount to be credited: ");
				Double amount = scanner.nextDouble();
				try {
					clientServices.doCredit(client, number, amount);
					System.out.println("Credit operation performed successfully!");
				} catch (ClientServiceException cse) {
					System.out.println(cse.getMessage());
				}

				break;
			case 2:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				System.out.println("Enter the amount to be debited: ");
				amount = scanner.nextDouble();
				try {
					clientServices.doDebit(client, number, amount);
					System.out.println("Debit operation performed successfully!");
				} catch (ClientServiceException cse) {
					System.out.println(cse.getMessage());
				}

				break;
			case 3:
				System.out.println("Enter the origin account number: ");
				String fromNumber = scanner.next();
				System.out.println("Enter the destination account number: ");
				String toNumber = scanner.next();
				System.out.println("Enter the amount to be transferred: ");
				amount = scanner.nextDouble();

				try {
					clientServices.doTransfer(client, fromNumber, toNumber, amount);
					System.out.println("Operation was successful!");
				} catch (ClientServiceException cse) {
					System.out.println(cse.getMessage());
				}
				break;

			case 4:
				System.out.println("Enter the account number: ");
				try {
					Double balance = clientServices.doRetrieveBalance(client, scanner.next());
					System.out.println("Balance: " + balance);
				} catch (ClientServiceException cse) {
					System.out.println(cse.getMessage());
				}
				break;

			case 5:
				try {
					List<Account> accounts = clientServices.doRetrieveAllClientAccounts(client);
					for (Account account : accounts) {
						System.out.println("[" + account.getType() + "] Numer: " + account.getNumber() + " Balance: "
								+ account.getBalance());
					}
				} catch (ClientServiceException cse) {
					System.out.println(cse.getMessage());
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
		System.out.println("Automated Teller Machine");
		System.out.println("================================");
		System.out.println(" [1] Do Credit");
		System.out.println(" [2] Do Debit");
		System.out.println(" [3] Do Transfer");
		System.out.println(" [4] Do Show Balance");
		System.out.println(" [5] Do List Acconts");
		System.out.println(" [0] Exit");
		System.out.println("================================");
		System.out.println("Enter the desired option: ");
		return scanner.nextInt();

	}
}