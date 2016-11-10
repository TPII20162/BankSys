package banksys.persistence.operator;

import java.util.ArrayList;
import java.util.List;

import banksys.model.Operator;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.exception.OperatorCreationException;
import banksys.persistence.operator.exception.OperatorDeletionException;
import banksys.persistence.operator.exception.OperatorNotFoundException;

public class OperatorInMemoryDAO implements OperatorDAO {
	private static double OPERATOR_IDS = 1;

	private static List<Operator> operators = new ArrayList<Operator>();
	
	static {
		OperatorInMemoryDAO.operators.add(new Operator("Default System Oparator", "operator", "12345"));
	}

	private static double nextId() {
		return OPERATOR_IDS++;
	}

	public OperatorInMemoryDAO() {
	}

	@Override
	public Operator create(Operator operator) throws OperatorCreationException {
		operator.setId(nextId());
		OperatorInMemoryDAO.operators.add(operator);
		return operator;
	}

	@Override
	public void delete(Double id) throws OperatorDeletionException {
		Operator operator = findById(id);
		if (operator != null) {
			OperatorInMemoryDAO.operators.remove(operator);
		} else {
			throw new OperatorDeletionException("Operator ID number " + id + " not found!");
		}
	}

	@Override
	public Operator retrieve(Double id) throws OperatorNotFoundException {
		Operator operator = findById(id);
		if (operator != null) {
			return operator;
		} else {
			throw new OperatorNotFoundException("Operator ID numer " + id + " not found!");
		}
	}

	@Override
	public Operator retrieveByUsernameAndPassword(String username, String password) throws OperatorNotFoundException {
		Operator operator = findByUsernameAndPassword(username, password);
		if (operator != null) {
			return operator;
		} else {
			throw new OperatorNotFoundException("Operator username " + username + " not found!");
		}
	}

	@Override
	public List<Operator> list() throws PersistenceException {
		if (OperatorInMemoryDAO.operators.size() == 0) {
			throw new PersistenceException("No existing operator list!");
		}
		return OperatorInMemoryDAO.operators;
	}

	private Operator findById(Double id) {
		for (Operator operator : OperatorInMemoryDAO.operators) {
			if (operator.getId() == id) {
				return operator;
			}
		}
		return null;
	}

	private Operator findByUsernameAndPassword(String username, String password) {
		for (Operator operator : OperatorInMemoryDAO.operators) {
			if (operator.getUsername().equals(username) && operator.getPassword().equals(password)) {
				return operator;
			}
		}
		return null;
	}
}
