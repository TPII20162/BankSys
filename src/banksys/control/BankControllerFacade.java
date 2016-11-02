package banksys.control;

import banksys.account.AbstractAccount;
import banksys.control.exception.BankTransactionException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.persistence.IAccountRepository;

public class BankControllerFacade {

	private AccountService bankControllerAccount;
	private TransactionService bankControllerTransaction = new TransactionService();
	
	public BankControllerFacade(IAccountRepository repository) {
		bankControllerAccount = new AccountService(repository);
		bankControllerTransaction.setRepository(repository);
	}

	public void addAccount(AbstractAccount account) throws BankTransactionException {
		bankControllerAccount.addAccount(account);
	}

	public void removeAccount(String number) throws BankTransactionException {
		bankControllerAccount.removeAccount(number);
	}

	public void doCredit(String number, double amount) throws BankTransactionException {
		bankControllerTransaction.credit(number, amount);
		bankControllerTransaction.commit();

	}

	public void doDebit(String number, double amount) throws BankTransactionException {
		bankControllerTransaction.debit(number, amount);
		bankControllerTransaction.commit();
	}
	
	public boolean validateWithdraw(double amount) {
		return bankControllerTransaction.validateWithdraw(amount);
	}

	public double getBalance(String number) throws BankTransactionException {
		AbstractAccount conta = bankControllerTransaction.retrieve(number);
		return conta.getBalance();

	}

	public void doTransfer(String fromNumber, String toNumber, double amount) throws BankTransactionException{

		bankControllerTransaction.debit(fromNumber, amount);

		bankControllerTransaction.credit(toNumber, amount);

		bankControllerTransaction.commit();
	}

	public void doEarnInterest(String number) throws BankTransactionException, IncompatibleAccountException {
		bankControllerTransaction.doEarnInterest(number);
	}

	public void doEarnBonus(String number) throws BankTransactionException, IncompatibleAccountException {
		bankControllerTransaction.doEarnBonus(number);
	}

	public AbstractAccount retrieve(String number) throws BankTransactionException{
		return bankControllerTransaction.retrieve(number);
	
	}

	
}