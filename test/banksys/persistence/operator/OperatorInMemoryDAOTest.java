package banksys.persistence.operator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import banksys.model.Operator;
import banksys.persistence.exception.PersistenceException;
import banksys.persistence.operator.exception.OperatorDeletionException;
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
	
	@Test(expected=OperatorDeletionException.class)
	public void testDeleteWhenNotExists() throws PersistenceException{
		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		Operator op = new Operator("OpDelete", "opDelete", "opDelete");

		op = opMem.create(op);
		opMem.delete(op.getId());
		opMem.delete(op.getId());
	}

	@Test
	public void testRetriveByUsernameAndPassword() throws PersistenceException{
		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		Operator op = new Operator("Operator", "testUsername", "testPassword");
		
		opMem.create(op);
		Operator ret = opMem.retrieveByUsernameAndPassword("testUsername", "testPassword");
		assertTrue("Error: Do Not Find Operator", op.getId() == ret.getId());
	}

	@Test(expected=OperatorNotFoundException.class)
	public void testRetrieveByUsernameAndPasswordWhenNotExists() throws PersistenceException{
		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		Operator op = new Operator("Operator", "testUsername22", "testPassword22");

		op = opMem.create(op);
		opMem.delete(op.getId());
		opMem.retrieveByUsernameAndPassword("testUsername22", "testPassword22");
	}

	@Test
	public void testInsertOnList() throws PersistenceException{
		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		
		Operator op1 = new Operator("Operator1", "Operator1", "Operator1");
		Operator op2 = new Operator("Operator2", "Operator2", "Operator2");
		Operator op3 = new Operator("Operator3", "Operator3", "Operator3");
		Operator op4 = new Operator("Operator4", "Operator4", "Operator4");
		
		op1 = opMem.create(op1);
		
		int sizeBeforeInsertion = opMem.list().size();
		
		op2 = opMem.create(op2);
		op3 = opMem.create(op3);
		op4 = opMem.create(op4);
		
		assertEquals("Error: Do Not Added in List",sizeBeforeInsertion+3, opMem.list().size());
	}
	
	@Test(expected = PersistenceException.class)
	public void testEmptyList() throws PersistenceException{
		
		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		List<Operator> lista = new ArrayList<>();
		
		lista.addAll(opMem.list());
		
		for(Operator op : lista)
			opMem.delete(op.getId());
		
		opMem.list();
	}

	@Test
	public void testDeleteFromList() throws PersistenceException{

		OperatorInMemoryDAO opMem = new OperatorInMemoryDAO();
		Operator op1 = new Operator("Operator1", "Operator1", "Operator1");
		Operator op2 = new Operator("Operator2", "Operator2", "Operator2");
		int sizeBeforeDelete;

		opMem.create(op1);
		op2 = opMem.create(op2);
		sizeBeforeDelete = opMem.list().size();
		opMem.delete(op2.getId());

		assertEquals("Error: Do Not Deleted of List", sizeBeforeDelete-1, opMem.list().size());

	}

}
