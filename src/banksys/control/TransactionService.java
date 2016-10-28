package banksys.control;

import banksys.account.AbstractAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import banksys.control.exception.BankTransactionException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.persistence.IAccountRepository;
import banksys.persistence.exception.AccountNotFoundException;
import banksys.persistence.exception.FlushException;

public class TransactionService {
	private IAccountRepository repository;

	public void setRepository(IAccountRepository repository) {
		this.repository = repository;
	}

	public void commit() throws BankTransactionException {
		try {
			this.repository.flush();
		} catch (FlushException fe) {
			throw new BankTransactionException(fe);
		}
	}

	public AbstractAccount retrieve(String number)
			throws BankTransactionException {
		AbstractAccount auxAccount;
		try {
			auxAccount = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}
		return auxAccount;
	}

	public void doEarnInterest(String number) throws BankTransactionException,
			IncompatibleAccountException {
		AbstractAccount auxAccount = retrieve(number);
		if (auxAccount instanceof SavingsAccount) {
			((SavingsAccount) auxAccount).earnInterest();
		} else {
			throw new IncompatibleAccountException(number);
		}
		this.commit();
	}

	public void doEarnBonus(String number) throws BankTransactionException,
			IncompatibleAccountException {
		AbstractAccount auxAccount = retrieve(number);
		if (auxAccount instanceof SpecialAccount) {
			((SpecialAccount) auxAccount).earnBonus();
		} else {
			throw new IncompatibleAccountException(number);
		}
		this.commit();
	}

	public void credit(String Number, double amount)
			throws BankTransactionException {
		AbstractAccount aux = this.retrieve(Number);
		try {
			aux.credit(amount);
		} catch (NegativeAmountException e) {
			throw new BankTransactionException(e);
		}

	}

	public void debit(String Number, double amount)
			throws BankTransactionException {
		AbstractAccount aux = this.retrieve(Number);
		try {
			aux.debit(amount);
		} catch (NegativeAmountException | InsufficientFundsException e) {
			throw new BankTransactionException(e);
		}
	}

}
