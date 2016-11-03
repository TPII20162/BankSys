package banksys.account;

import java.util.Date;

import banksys.account.exception.NegativeAmountException;

public class SavingsAccount extends OrdinaryAccount {

	public SavingsAccount(String number) {
		super(number);
	}

	public void earnInterest() {
		try {
			this.credit(this.getBalance() * 0.001);
			
			newTransaction(null, "interest", this.getBalance() * 0.001, new Date(System.currentTimeMillis()));
		} catch (NegativeAmountException e) {
		}
		
	}
}
