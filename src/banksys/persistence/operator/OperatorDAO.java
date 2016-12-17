package banksys.persistence.operator;

import java.sql.SQLException;
import java.util.List;

import banksys.model.Operator;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.exception.OperatorCreationException;
import banksys.persistence.operator.exception.OperatorDeletionException;
import banksys.persistence.operator.exception.OperatorNotFoundException;

public interface OperatorDAO {

	public Operator create(Operator operator) throws OperatorCreationException;

	public void delete(Double id) throws OperatorDeletionException;

	public Operator retrieve(Double id) throws OperatorNotFoundException, SQLException;

	public Operator retrieveByUsernameAndPassword(String username, String password) throws OperatorNotFoundException;

	public List<Operator> list() throws PersistenceException;


}