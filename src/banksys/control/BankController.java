package banksys.control;


import banksys.persistence.IAccountRepository;
import banksys.account.AbstractAccount;
import banksys.control.exception.BankTransactionException;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.FlushException;
import banksys.persistence.exception.AccountNotFoundException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;

public class BankController {
	
	private IAccountRepository repository;

	public void setRepository(IAccountRepository repository) {
		this.repository = repository;
	}

	public void addAccount(AbstractAccount account)
			throws BankTransactionException {
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
}