package banksys.banknote;

public class BankNoteController {

	NoteAlgorithm noteAlgorithm;
	String notesString = null;
	
	BankNoteController(NoteAlgorithm newAlgorithm, double value){
		setAlgorithm(newAlgorithm);
		notesString = noteAlgorithm.GetNotes(value);
	}
	
	public String getNotesString(){
		return notesString;
	}
	
	public void setAlgorithm(NoteAlgorithm newAlgorithm){
		noteAlgorithm = newAlgorithm;
	}
	
	public String getAlgorithmName(){
		return noteAlgorithm.nameOfAlgoritm();
	}
		
	
}
