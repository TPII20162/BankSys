package banksys.persistence.operator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.model.Operator;
import banksys.persistence.ResetSQLiteDataBase;
import banksys.persistence.operator.exception.OperatorCreationException;
import banksys.persistence.operator.exception.OperatorDeletionException;

public class OperatorDatabaseDAOTest {

	OperatorDatabaseDAO operatorDatabase;
	
	@Before
	public void setUp() throws Exception {
		operatorDatabase = new OperatorDatabaseDAO();
		ResetSQLiteDataBase.resetDataBase();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		
		Operator op = new Operator(1.0, "Felipe", "fscfelipe", "123");
		try {
			operatorDatabase.create(op);
		} catch (OperatorCreationException e) {
			fail(e.getMessage());
		}
		
				
	}

	@Test
	public void testDelete() {
		
		/*
		Operator op = new Operator(7.0, "John", "johnjohn", "77262");
		try {
			operatorDatabase.create(op);
			operatorDatabase.delete(op.getId());
		} catch (OperatorCreationException e) {
			fail(e.getMessage());
		} catch (OperatorDeletionException e) {
			fail(e.getMessage());
		}
		*/
	}

	@Test
	public void testRetrieve() {
	}

	@Test
	public void testRetrieveByUsernameAndPassword() {
	}

	@Test
	public void testList() {
	}

}
