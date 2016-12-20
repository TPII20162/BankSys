package banksys.guice;

import com.google.inject.AbstractModule;

import banksys.service.ClientServices;
import banksys.service.ClientServicesImpl;

public class BankSysModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ClientServices.class).to(ClientServicesImpl.class);
	}

}
