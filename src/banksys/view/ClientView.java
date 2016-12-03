package banksys.view;

import java.util.List;
import java.util.Scanner;

import banksys.model.Account;
import banksys.model.Client;
import banksys.service.ClientServices;
import banksys.service.exception.ClientServiceException;

public class ClientView {

	private static Scanner scanner = new Scanner(System.in);

	private static Client doLogIn(ClientServices clientServices)
	{
		Client client = null;
		
		while (client == null) 
		{
			System.out.print("Enter the username: ");
			String username = scanner.next();
			System.out.print("Enter the password: ");
			String password = scanner.next();
			
			try
			{
				client = clientServices.doLogin(username, password);
			}
			catch (ClientServiceException cse) 
			{
				System.out.println(cse.getMessage());
				System.out.println("Returning to main menu...\n");
				return null;
			}
		}		
		System.out.println("Wellcome client " + client.getFullName());
		
		return client;
	}
	
	private static void doCreditMenu(ClientServices services, Client client)
	{
		System.out.println("Enter the account number: ");
		String number = scanner.next();
		System.out.println("Enter the amount to be credited: ");
		Double amount = scanner.nextDouble();
		
		try
		{
			services.doCredit(client, number, amount);
			System.out.println("Credit operation performed successfully!");
		}
		catch (ClientServiceException cse) 
		{
			System.out.println(cse.getMessage());
		}
	}
	
	private static void doDebitMenu(ClientServices services, Client client)
	{
		System.out.println("Enter the account number: ");
		String number = scanner.next();
		System.out.println("Enter the amount to be debited: ");
		Double amount = scanner.nextDouble();
		
		try {
			services.doDebit(client, number, amount);
			System.out.println("Debit operation performed successfully!");
		} catch (ClientServiceException cse) {
			System.out.println(cse.getMessage());
		}
	}
	
	private static void doTransferMenu(ClientServices services, Client client)
	{
		System.out.println("Enter the origin account number: ");
		String fromNumber = scanner.next();
		System.out.println("Enter the destination account number: ");
		String toNumber = scanner.next();
		System.out.println("Enter the amount to be transferred: ");
		Double amount = scanner.nextDouble();

		try 
		{
			services.doTransfer(client, fromNumber, toNumber, amount);
			System.out.println("Operation was successful!");
		} catch (ClientServiceException cse) {
			System.out.println(cse.getMessage());
		}
	}
	
	private static void retrieveBalanceMenu(ClientServices services, Client client)
	{
		System.out.println("Enter the account number: ");
		
		try {
			Double balance = services.doRetrieveBalance(client, scanner.next());
			System.out.println("Balance: " + balance);
		} catch (ClientServiceException cse) {
			System.out.println(cse.getMessage());
		}
	}
	
	private static void listAccountsMenu(ClientServices services, Client client)
	{
		try {
			List<Account> accounts = services.doRetrieveAllClientAccounts(client);
			if(accounts.size() > 0){
				for (Account account : accounts) {
					System.out.println("[" + account.getType() + "] Number: " + account.getNumber() + " Balance: "
							+ account.getBalance());
				}
			}else{
				System.out.println("O cliente "+client.getFullName()+" não possui contas.");
			}
			
		} catch (ClientServiceException cse) {
			System.out.println(cse.getMessage());
		}
	}
	
	public static void run(ClientServices clientServices) 
	{		
		Client client = doLogIn(clientServices);
		
		if (client != null)
			mainMenu(clientServices, client);
	}

	private static void mainMenu(ClientServices services, Client client) 
	{
		while (true)
		{
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
			
			int selection = scanner.nextInt();
	
			if (selection == 1)
				doCreditMenu(services, client);
			else if (selection == 2)
				doDebitMenu(services, client);
			else if (selection == 3)
				doTransferMenu(services, client);
			else if (selection == 4)
				retrieveBalanceMenu(services, client);
			else if (selection == 5)
				listAccountsMenu(services, client);
			else if (selection == 0)
				break;
			
		}
	}
}