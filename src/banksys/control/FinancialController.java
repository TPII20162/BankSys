package banksys.control;

import banksys.account.AbstractAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import banksys.control.exception.BankTransactionException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.persistence.IAccountRepository;

public class FinancialController {

	private BankController bankControllerProduct = new BankController();
	
	public FinancialController(IAccountRepository repository) {
		bankControllerProduct.setRepository(repository);
	}

	public void addAccount(AbstractAccount account) throws BankTransactionException {
		bankControllerProduct.addAccount(account);
	}

	public void removeAccount(String number) throws BankTransactionException {
		bankControllerProduct.removeAccount(number);
	}

	public void doCredit(String number, double amount) throws BankTransactionException {
		credit(number, amount);
		bankControllerProduct.commit();

	}

	public void doDebit(String number, double amount) throws BankTransactionException {
		debit(number, amount);
		bankControllerProduct.commit();
	}

	public double getBalance(String number) throws BankTransactionException {
		AbstractAccount conta = bankControllerProduct.retrieve(number);
		return conta.getBalance();

	}

	public void doTransfer(String fromNumber, String toNumber, double amount) throws BankTransactionException{

		debit(fromNumber, amount);

		credit(toNumber, amount);

		bankControllerProduct.commit();
	}

	public void doEarnInterest(String number) throws BankTransactionException, IncompatibleAccountException {
		bankControllerProduct.doEarnInterest(number);
	}

	public void doEarnBonus(String number) throws BankTransactionException, IncompatibleAccountException {
		bankControllerProduct.doEarnBonus(number);
	}

	public AbstractAccount retrieve(String number) throws BankTransactionException{
		return bankControllerProduct.retrieve(number);
	
	}

	public void credit(String Number, double amount) throws BankTransactionException {
		AbstractAccount aux = bankControllerProduct.retrieve(Number);
		try {
			aux.credit(amount);
		} catch (NegativeAmountException e) {
			throw new BankTransactionException(e);
		}

	}

	public void debit(String Number, double amount) throws  BankTransactionException{
		AbstractAccount aux = bankControllerProduct.retrieve(Number);
		try {
			aux.debit(amount);
		} catch (NegativeAmountException | InsufficientFundsException e) {
			throw new BankTransactionException(e);
		}
	}
}