package banksys.banknote;

public class BankNoteController {

	NoteAlgorithm noteAlgorithm;
	
	public void BankNoteController(){
		noteAlgorithm = new HighNotesAlgorithm();
	}
	
	public void SetAlgorithm(NoteAlgorithm newAlgorithm){
		noteAlgorithm = newAlgorithm;
	}
	
}
