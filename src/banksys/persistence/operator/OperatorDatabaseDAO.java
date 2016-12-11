package banksys.persistence.operator;

import java.util.List;

import banksys.model.Operator;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.exception.OperatorCreationException;
import banksys.persistence.operator.exception.OperatorDeletionException;
import banksys.persistence.operator.exception.OperatorNotFoundException;

public class OperatorDatabaseDAO implements OperatorDAO {

	@Override
	public Operator create(Operator operator) throws OperatorCreationException {
		return null;
	}

	@Override
	public void delete(Double id) throws OperatorDeletionException {

	}

	@Override
	public Operator retrieve(Double id) throws OperatorNotFoundException {
		return null;
	}

	@Override
	public Operator retrieveByUsernameAndPassword(String username,
			String password) throws OperatorNotFoundException {
		return null;
	}

	@Override
	public List<Operator> list() throws PersistenceException {
		return null;
	}

}
