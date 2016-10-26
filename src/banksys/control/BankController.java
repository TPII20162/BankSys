package banksys.control;

import banksys.account.AbstractAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import banksys.control.exception.BankTransactionException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.persistence.IAccountRepository;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;
import banksys.persistence.exception.FlushException;

public class BankController {

	private IAccountRepository repository;

	public BankController(IAccountRepository repository) {
		this.repository = repository;
	}

	public void addAccount(AbstractAccount account) throws BankTransactionException {
		try {
			this.repository.create(account);
		} catch (AccountCreationException ace) {
			throw new BankTransactionException(ace);
		}
		this.commit();
	}

	public void removeAccount(String number) throws BankTransactionException {
		try {
			this.repository.delete(number);
		} catch (AccountDeletionException ade) {
			throw new BankTransactionException(ade);
		}
		this.commit();
	}

	public void doCredit(String number, double amount) throws BankTransactionException {
		credit(number, amount);
		this.commit();

	}

	public void doDebit(String number, double amount) throws BankTransactionException {
		debit(number, amount);
		this.commit();
	}

	public double getBalance(String number) throws BankTransactionException {
		AbstractAccount conta = retrieve(number);
		return conta.getBalance();

	}

	public void doTransfer(String fromNumber, String toNumber, double amount) throws BankTransactionException{

		debit(fromNumber, amount);

		credit(toNumber, amount);

		this.commit();
	}

	public void doEarnInterest(String number) throws BankTransactionException, IncompatibleAccountException {
		AbstractAccount auxAccount = retrieve(number);

		if (auxAccount instanceof SavingsAccount) {
			((SavingsAccount) auxAccount).earnInterest();
		} else {
			throw new IncompatibleAccountException(number);
		}

		this.commit();
	}

	public void doEarnBonus(String number) throws BankTransactionException, IncompatibleAccountException {
		AbstractAccount auxAccount = retrieve(number);

		if (auxAccount instanceof SpecialAccount) {
			((SpecialAccount) auxAccount).earnBonus();
		} else {
			throw new IncompatibleAccountException(number);
		}
		this.commit();
	}

	private void commit() throws BankTransactionException {
		try {
			this.repository.flush();
		} catch (FlushException fe) {
			throw new BankTransactionException(fe);
		}
	}


	public AbstractAccount retrieve(String number) throws BankTransactionException{
		AbstractAccount auxAccount;
		try {
			auxAccount = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}
		return auxAccount;
	
	}

	public void credit(String Number, double amount) throws BankTransactionException {
		AbstractAccount aux = retrieve(Number);
		try {
			aux.credit(amount);
		} catch (NegativeAmountException e) {
			throw new BankTransactionException(e);
		}

	}

	public void debit(String Number, double amount) throws  BankTransactionException{
		AbstractAccount aux = retrieve(Number);
		try {
			aux.debit(amount);
		} catch (NegativeAmountException | InsufficientFundsException e) {
			throw new BankTransactionException(e);
		}
	}
}