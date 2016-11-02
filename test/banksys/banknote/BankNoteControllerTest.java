package banksys.banknote;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankNoteControllerTest {

	
	@Test
	public void testContructorAndAlgorithmType() {	
		HighNotesAlgorithm highNoteAlgorithm = new HighNotesAlgorithm();
		BankNoteController bankNoteController = new BankNoteController(highNoteAlgorithm , 289);
		String algorithmName = bankNoteController.getAlgorithmName();
		assertTrue("Fail - type of algorithm", algorithmName.equals("HighNotesAlgorithm"));
	}
	
	@Test
	public void testStringNotes(){
		HighNotesAlgorithm highNoteAlgorithm = new HighNotesAlgorithm();
		BankNoteController bankNoteController = new BankNoteController(highNoteAlgorithm , 289);
		String expectedStringNotes = "Notas de 50: 5 \nNotas de 20: 1 \nNotas de 10: 1\nNotas de 5: 1\nNotas de 2: 2\n\n";
		String bankNoteControllerStringNotes = bankNoteController.getNotesString();
		assertTrue("Fail - string notes", bankNoteControllerStringNotes.equals(expectedStringNotes));
	}
	

}
