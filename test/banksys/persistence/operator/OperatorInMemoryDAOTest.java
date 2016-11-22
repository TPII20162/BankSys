package banksys.persistence.operator;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.model.Operator;
import banksys.persistence.operator.exception.OperatorCreationException;
import banksys.persistence.operator.exception.OperatorNotFoundException;

public class OperatorInMemoryDAOTest {
	
	@Test
	public void testCreateAndRetrive(){
		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		Operator op = new Operator("Operator", "operator", "operator");
		try{
			opMem.create(op);
			Operator ret = opMem.retrieve(op.getId());
			assertTrue("Error: Create Fail", op.getId() == ret.getId());
		} catch(OperatorCreationException | OperatorNotFoundException e){
			fail("Error: Create Fail");
		}
	}

}
