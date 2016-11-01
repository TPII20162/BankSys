package banksys.banknote;

public class BankNoteController {

	NoteAlgorithm noteAlgorithm;
	
	public String BankNoteController(){
		noteAlgorithm = new HighNotesAlgorithm();
		String notesString = noteAlgorithm.GetNotes();
		
		return notesString;
	}
	
	public void SetAlgorithm(NoteAlgorithm newAlgorithm){
		noteAlgorithm = newAlgorithm;
	}
		
	
}
