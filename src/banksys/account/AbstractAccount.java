package banksys.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import banksys.util.Transaction;

public abstract class AbstractAccount {

	protected String number;
	protected double balance;
	protected List<Transaction> transactions;

	public AbstractAccount(String number) {
		this.number = number;
		this.balance = 0;
		this.transactions = new ArrayList<Transaction>();
	}

	public void credit(double amount) throws NegativeAmountException {
		if (amount >= 0) {
			this.balance += amount;
			
			newTransaction(null, "credit", amount, new Date(System.currentTimeMillis()));
		} else {
			throw new NegativeAmountException(amount);
		}
		
		
		
	}
	
	public void newTransaction(String numDestinationAccount, String type, double value, Date date){
		int num = transactions.size();
		String id = String.valueOf(num+1);
		
		Transaction transaction;
		
		if(type.equals("transfer")){
			 transaction = new Transaction(id,number, numDestinationAccount, type, value, date);
			
		}else{
			 transaction = new Transaction(id, number, type, value, date);
		}
		
		transactions.add(transaction);
		
	}

	public abstract void debit(double amount) throws NegativeAmountException, InsufficientFundsException;

	public String getNumber() {
		return number;
	}

	public double getBalance() {
		return balance;
	}
}