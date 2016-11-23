package banksys.persistence.operator;

import static org.junit.Assert.*;

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

}
