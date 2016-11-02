package banksys.banknote;

import static org.junit.Assert.*;

import org.junit.Test;

public class HighNotesAlgorithmTest {
	
	HighNotesAlgorithm highNotesAlgorithm = new HighNotesAlgorithm();

	@Test
	public void getNotesTestMoreThan50() {
		String s = highNotesAlgorithm.GetNotes(289);
	
		assertEquals("Fail - Number of Notes 50", highNotesAlgorithm.getBankNotes50(), 5, 0);
		assertEquals("Fail - Number of Notes 20", highNotesAlgorithm.getBankNotes20(), 1, 0);
		assertEquals("Fail - Number of Notes 10", highNotesAlgorithm.getBankNotes10(), 1, 0);
		assertEquals("Fail - Number of Notes 5", highNotesAlgorithm.getBankNotes5(), 1, 0);
		assertEquals("Fail - Number of Notes 2", highNotesAlgorithm.getBankNotes2(), 2, 0);
		
	}

	@Test
	public void getNotesTestMoreThan20LessThan50() {
		String s = highNotesAlgorithm.GetNotes(39);
	
		assertEquals("Fail - Number of Notes 50", highNotesAlgorithm.getBankNotes50(), 0, 0);
		assertEquals("Fail - Number of Notes 20", highNotesAlgorithm.getBankNotes20(), 1, 0);
		assertEquals("Fail - Number of Notes 10", highNotesAlgorithm.getBankNotes10(), 1, 0);
		assertEquals("Fail - Number of Notes 5", highNotesAlgorithm.getBankNotes5(), 1, 0);
		assertEquals("Fail - Number of Notes 2", highNotesAlgorithm.getBankNotes2(), 2, 0);
		
	}

	@Test
	public void getNotesTestMoreThan10LessThen20() {
		String s = highNotesAlgorithm.GetNotes(18);
	
		assertEquals("Fail - Number of Notes 50", highNotesAlgorithm.getBankNotes50(), 0, 0);
		assertEquals("Fail - Number of Notes 20", highNotesAlgorithm.getBankNotes20(), 0, 0);
		assertEquals("Fail - Number of Notes 10", highNotesAlgorithm.getBankNotes10(), 1, 0);
		assertEquals("Fail - Number of Notes 5", highNotesAlgorithm.getBankNotes5(), 1, 0);
		assertEquals("Fail - Number of Notes 2", highNotesAlgorithm.getBankNotes2(), 1, 0);
		
	}

	@Test
	public void getNotesTestMoreThan5LessThan10() {
		String s = highNotesAlgorithm.GetNotes(9);
	
		assertEquals("Fail - Number of Notes 50", highNotesAlgorithm.getBankNotes50(), 0, 0);
		assertEquals("Fail - Number of Notes 20", highNotesAlgorithm.getBankNotes20(), 0, 0);
		assertEquals("Fail - Number of Notes 10", highNotesAlgorithm.getBankNotes10(), 0, 0);
		assertEquals("Fail - Number of Notes 5", highNotesAlgorithm.getBankNotes5(), 1, 0);
		assertEquals("Fail - Number of Notes 2", highNotesAlgorithm.getBankNotes2(), 2, 0);
		
	}

	@Test
	public void getNotesTestMoreThan2LessThan5() {
		String s = highNotesAlgorithm.GetNotes(4);
	
		assertEquals("Fail - Number of Notes 50", highNotesAlgorithm.getBankNotes50(), 0, 0);
		assertEquals("Fail - Number of Notes 20", highNotesAlgorithm.getBankNotes20(), 0, 0);
		assertEquals("Fail - Number of Notes 10", highNotesAlgorithm.getBankNotes10(), 0, 0);
		assertEquals("Fail - Number of Notes 5", highNotesAlgorithm.getBankNotes5(), 0, 0);
		assertEquals("Fail - Number of Notes 2", highNotesAlgorithm.getBankNotes2(), 2, 0);
		
	}

}
