package banksys.persistence.operator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import banksys.model.Operator;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.exception.OperatorNotFoundException;

public class OperatorInMemoryDAOTest {
	
	@Test
	public void testCreateAndRetrive() throws PersistenceException{
		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		Operator op = new Operator("Operator", "operator", "operator");
		
		opMem.create(op);
		Operator ret = opMem.retrieve(op.getId());
		assertTrue("Error: Create Fail", op.getId() == ret.getId());
	}
	
	@Test(expected=OperatorNotFoundException.class)
	public void testDelete() throws PersistenceException{
		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		Operator op = new Operator("Operator", "operator", "operator");
		
		opMem.create(op);
		opMem.delete(op.getId());
		opMem.retrieve(op.getId());
	}
	
	@Test
	public void testRetriveByUsernameAndPassword() throws PersistenceException{
		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		Operator op = new Operator("Operator", "testUsername", "testPassword");
		
		opMem.create(op);
		Operator ret = opMem.retrieveByUsernameAndPassword("testUsername", "testPassword");
		assertTrue("Error: Do Not Find Operator", op.getId() == ret.getId());
	}
	
	@Test
	public void testInsertOnList() throws PersistenceException {
		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		
		Operator op1 = new Operator("Operator1", "Operator1", "Operator1");
		Operator op2 = new Operator("Operator2", "Operator2", "Operator2");
		Operator op3 = new Operator("Operator3", "Operator3", "Operator3");
		
		int sizeBeforeInsertion = opMem.list().size();
		
		op1 = opMem.create(op1);
		op2 = opMem.create(op2);
		op3 = opMem.create(op3);
		
		assertEquals("Error: Insertion Failed in List",sizeBeforeInsertion+3, opMem.list().size());
	}

}
