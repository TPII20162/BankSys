package banksys.persistence.operator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.model.Operator;
import banksys.persistence.operator.exception.OperatorCreationException;

public class OperatorDatabaseDAOTest {

	OperatorDatabaseDAO operatorDatabase;
	
	@Before
	public void setUp() throws Exception {
		operatorDatabase = new OperatorDatabaseDAO();
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
