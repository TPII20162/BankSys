package banksys.persistence.operator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import banksys.model.Operator;
import banksys.persistence.Connector;
import banksys.persistence.account.exception.AccountCreationException;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.exception.OperatorCreationException;
import banksys.persistence.operator.exception.OperatorDeletionException;
import banksys.persistence.operator.exception.OperatorNotFoundException;

public class OperatorDatabaseDAO implements OperatorDAO {

	@Override
	public Operator create(Operator operator) throws OperatorCreationException {
		Connection connection = Connector.connect();
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO operator " +
			"(userId) VALUES (?);");
			
			preparedStatement.setDouble(1, operator.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		catch(SQLException e){
			throw new OperatorCreationException(e.getMessage());
		}
		
		return operator;
		
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
