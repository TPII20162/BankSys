package banksys.control;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.TaxAccount;
import banksys.control.exception.BankTransactionException;
import banksys.persistence.IAccountRepository;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;
import banksys.persistence.exception.FlushException;

public class AccountService {
	public enum AccountType {
		ORDINARY,SAVINGS,SPECIAL,TAX
	}
	private IAccountRepository repository;
	public AccountService(IAccountRepository repository){
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
	public void addAccount(String numero,AccountType tipo){
		switch(tipo){
		case ORDINARY: 
			try {
				this.repository.create(new OrdinaryAccount(numero));
			} catch (AccountCreationException e) {}
			break;
		case SAVINGS :	
			try {
				this.repository.create(new SavingsAccount(numero));
			} catch (AccountCreationException e) {}
			break;
		case SPECIAL :	
			try {
				this.repository.create(new SpecialAccount(numero));
			} catch (AccountCreationException e) {}
			break;
		case TAX :	
			try {
				this.repository.create(new TaxAccount(numero));
			} catch (AccountCreationException e) {}
			break;	
		}
	}
	public void removeAccount(String number) throws BankTransactionException {
		try {
			this.repository.delete(number);
		} catch (AccountDeletionException ade) {
			throw new BankTransactionException(ade);
		}
		this.commit();
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
	public void commit() throws BankTransactionException {
		try {
			this.repository.flush();
		} catch (FlushException fe) {
			throw new BankTransactionException(fe);
		}
	}
}
