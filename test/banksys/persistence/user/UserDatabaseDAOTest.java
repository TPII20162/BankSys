package banksys.persistence.user;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.model.User;
import banksys.persistence.ResetSQLiteDataBase;

public class UserDatabaseDAOTest {

	@Before
	public void setUp() throws Exception {
		ResetSQLiteDataBase.resetDataBase();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		
		UserDatabaseDAO userDatabase = new UserDatabaseDAO();
		User user = new User(7.0, "Felipe", "fscfelipe", "123");
		
		try {
			userDatabase.create(user);
		} catch (SQLException e) {
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

	@Test
	public void testNumberOfUsers() {
	}

}
