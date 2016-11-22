package banksys.view;

import java.util.Scanner;

import banksys.model.AccountType;
import banksys.model.Client;
import banksys.model.Operator;
import banksys.service.OperatorServices;
import banksys.service.exception.OperationServiceException;

public class OperatorView {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void run(OperatorServices operatorServices) 
	{	
		Operator operator = doLogInMenu(operatorServices);
		
		if (operator != null)
			mainMenu(operatorServices, operator);
	}

	private static Operator doLogInMenu(OperatorServices operatorServices)
	{
		Operator operator = null;
		
		while (operator == null) 
		{
			System.out.print("Enter the username: ");
			String username = scanner.next();
			System.out.print("Enter the password: ");
			String password = scanner.next();
			
			try 
			{
				operator = operatorServices.doLogin(username, password);
			}
			catch (OperationServiceException ose) 
			{
				System.out.println(ose.getMessage());
				System.out.println("Returning to main menu...\n");
				return null;
			}
		}
		
		System.out.println("Wellcome operator " + operator.getFullName());

		return operator;
	}
	
	private static int mainMenu(OperatorServices opService, Operator op) 
	{
		while(true)
		{
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
			
			int option = scanner.nextInt();
			
			if (option == 0)
			{
				System.out.println("Goodbye and have a nice day!!!");
				return 0;
			}
			else if (option == 1)
				newClientMenu(opService, op);
			else if (option == 2)
				newAccountMenu(opService, op);
			else if (option == 3)
				closeAccountMenu(opService, op);
			else if (option == 4)
				earnInterestMenu(opService, op);
			else if (option == 5)
				earnBonusMenu(opService, op);
		}
	}

	private static int newClientMenu(OperatorServices operatorServices, Operator operator)
	{
		System.out.print("Enter the client full name: ");
		String fullName = scanner.next();
		System.out.print("Enter the client username: ");
		String username = scanner.next();
		System.out.print("Enter the client password: ");
		String password = scanner.next();
		System.out.print("Confirm client password: ");
		String conf_pass = scanner.next();

		try 
		{
			Client client = operatorServices.doNewClient(operator, fullName, username, password, conf_pass);
			System.out.println("Client creation successfully! Client ID number: " + client.getId());
		}
		catch (OperationServiceException ose) 
		{
			System.out.println(ose.getMessage());
		}
		
		return 0;
	}
	
	private static int newAccountMenu(OperatorServices operatorServices, Operator operator)
	{
		System.out.println("================================");
		System.out.println("Add New Account");
		System.out.println("================================");
		System.out.println(" [1] Ordinary");
		System.out.println(" [2] Special");
		System.out.println(" [3] Savings");
		System.out.println(" [4] Tax");
		System.out.println("================================");
		System.out.println("Enter the desired option: ");
		
		int option = scanner.nextInt();
		
		AccountType type = AccountType.ORDINARY;
		
		if (option == 1)
			type = AccountType.ORDINARY;
		else if (option == 2)
			type = AccountType.SPECIAL;
		else if (option == 3)
			type = AccountType.SAVINGS;
		else if (option == 4)
			type = AccountType.TAX;
		else
		{
			System.out.println("Invalid option! Quitting..");
			return 0;
		}
		
		System.out.println("Enter the client ID number: ");
		
		try 
		{
			operatorServices.doNewAccount(operator, scanner.nextDouble(), type);
			System.out.println("Account creation successfull!");
			
			return 1;
		} 
		catch (OperationServiceException ose) 
		{
			System.out.println(ose.getMessage());
			return 0;
		}
	}

	private static int closeAccountMenu(OperatorServices operatorServices, Operator operator)
	{
		System.out.println("Enter the account number: ");
		
		try 
		{
			operatorServices.doCloseAccount(operator, scanner.next());
			System.out.println("Account closed successfully!");
			
			return 1;
		}
		catch (OperationServiceException ose) 
		{
			System.out.println(ose.getMessage());
			return 0;
		}
	}
	
	private static int earnInterestMenu(OperatorServices operatorServices, Operator operator)
	{
		System.out.println("Enter the account number: ");
		try
		{
			operatorServices.doEarnInterest(operator, scanner.next());
			System.out.println("Earn interest operation performed successfully!");
			
			return 1;
		} 
		catch (OperationServiceException ose) 
		{
			System.out.println(ose.getMessage());
			return 0;
		}
	}
	
	private static int earnBonusMenu(OperatorServices operatorServices, Operator operator)
	{
		System.out.println("Enter the account number: ");
		try 
		{
			operatorServices.doEarnBonus(operator, scanner.next());
			System.out.println("Earn bonus operation performed successfully!");
			
			return 1;
		}
		catch (OperationServiceException ose) 
		{
			System.out.println(ose.getMessage());
			return 0;
		}
	}
}