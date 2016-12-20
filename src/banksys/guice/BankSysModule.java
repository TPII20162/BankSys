package banksys.guice;

import com.google.inject.AbstractModule;

import banksys.persistence.account.AccountDAO;
import banksys.persistence.account.AccountInMemoryDAO;
import banksys.persistence.client.ClientDAO;
import banksys.persistence.client.ClientInMemoryDAO;
import banksys.persistence.operator.OperatorDAO;
import banksys.persistence.operator.OperatorInMemoryDAO;
import banksys.service.ClientServices;
import banksys.service.ClientServicesImpl;

public class BankSysModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ClientServices.class).to(ClientServicesImpl.class);
		
		bind(ClientDAO.class).to(ClientInMemoryDAO.class);
		bind(OperatorDAO.class).to(OperatorInMemoryDAO.class);
		bind(AccountDAO.class).to(AccountInMemoryDAO.class);
	}

}
