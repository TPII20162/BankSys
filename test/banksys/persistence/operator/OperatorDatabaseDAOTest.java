package banksys.persistence.operator;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.model.Operator;
import banksys.persistence.ResetSQLiteDataBase;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.exception.OperatorCreationException;
import banksys.persistence.operator.exception.OperatorDeletionException;
import banksys.persistence.operator.exception.OperatorNotFoundException;
import junit.framework.Assert;

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
		
		/*Operator op = new Operator(1.0, "Felipe", "fscfelipe", "123");
		try {
			operatorDatabase.create(op);
		} catch (OperatorCreationException e) {
			fail(e.getMessage());

		}*/
					
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
		/*
		Operator op = new Operator(7.0, "John", "johnjohn", "77262");
		double retrievedId;
		try {
			operatorDatabase.create(op);
			retrievedId = operatorDatabase.retrieve(op.getId()).getId();
			assertEquals("ActualID and retrieved ID should be equals", 7.0, retrievedId, 0);
		} catch (OperatorCreationException e) {
			System.err.println("Could not create account on database: " + e.getMessage());
		} catch (OperatorNotFoundException e) {
			System.err.println("Could not found operator on database: " + e.getMessage());
		}
		*/
	}

	@Test
	public void testRetrieveByUsernameAndPassword() {
		/*
		Operator op = new Operator(8.0, "Johnny", "jj", "77777");
		double retrievedId;
		try {
			operatorDatabase.create(op);
			retrievedId = operatorDatabase.retrieveByUsernameAndPassword("jj", "77777").getId();
			assertEquals("ActualID and retrieved ID should be equals", 8.0, retrievedId, 0);
		} catch (OperatorCreationException e) {
			System.err.println("Could not create account on database: " + e.getMessage());
		} catch (OperatorNotFoundException e) {
			System.err.println("Could not found operator on database: " + e.getMessage());
		}
		*/
	}

	@Test
	public void testList() {
		Operator op = new Operator(7.0, "John", "johnjohn", "77262");
		Operator op2 = new Operator(8.0, "Johnny", "jj", "77777");

		try {
			operatorDatabase.create(op);
			operatorDatabase.create(op2);
			List<Operator> operatorList = operatorDatabase.list();
			assertEquals("Size of the list must be 2",2, operatorList.size());
			
			assertEquals(operatorList.get(0).getId(), 77262, 0);
			assertEquals(operatorList.get(1).getId(), 77777, 0);
			
		} catch (OperatorCreationException e) {
			System.err.println("Could not create account on database: " + e.getMessage());
		} catch (OperatorNotFoundException e) {
			System.err.println("Could not found operator on database: " + e.getMessage());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
