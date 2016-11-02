package banksys.banknote;

public class HighNotesAlgorithm implements NoteAlgorithm {
   	private int bankNotes50, bankNotes20, bankNotes10, bankNotes5, bankNotes2;

	public void valueMoreThan50(double value){
		bankNotes50 = (int) (value/50);
		value = value - bankNotes50*50;

		if(value >= 20 && value < 50){
			valueMoreThan20lessThan50(value);
		}
		else if(value >= 10 && value < 20){
			valueMoreThan10lessThan20(value);			
		}
		else if(value >= 5 && value < 10){
			valueMoreThan5lessThan10(value);
		}
		else if(value >= 2 && value < 5){
			valueMoreThan2lessThan5(value);
		}
	}

	
	public void valueMoreThan20lessThan50(double value){
		bankNotes20 = (int) (value/20);
		value = value - bankNotes20*20;

		if(value >= 10 && value < 20){
			valueMoreThan10lessThan20(value);			
		}
		else if(value >= 5 && value < 10){
			valueMoreThan5lessThan10(value);
		}
		else if(value >= 2 && value < 5){
			valueMoreThan2lessThan5(value);
		}
	}

	
	public void valueMoreThan10lessThan20(double value){
		bankNotes10 = (int) (value/10);
		value = value - bankNotes10*10;

		if(value >= 5 && value < 10){
			valueMoreThan5lessThan10(value);
		}
	}


	public void valueMoreThan5lessThan10(double value){
		bankNotes5 = (int) (value/5);
		value = value - bankNotes5*5;

		if(value >= 2 && value < 5){
			valueMoreThan2lessThan5(value);
		}
	}

	
	public void valueMoreThan2lessThan5(double value){
		bankNotes2 = (int) (value/2);
		value = value - bankNotes2*2;
	}
	
	public int getBankNotes50() {
		return bankNotes50;
	}

	public int getBankNotes20() {
		return bankNotes20;
	}

	public int getBankNotes10() {
		return bankNotes10;
	}

	public int getBankNotes5() {
		return bankNotes5;
	}

	public int getBankNotes2() {
		return bankNotes2;
	}
	
	public String getBankNotesString(){
		return "Notas de 50: "+bankNotes50+" \nNotas de 20: "+bankNotes20+" \nNotas de 10: "+bankNotes10+ "\nNotas de 5: "+bankNotes5+ "\nNotas de 2: "+bankNotes2+ "\n\n";
	}
	

	@Override
	public String GetNotes(double value) {
		// TODO Get highest notes algorithm, #27
		if(value >= 50){
			valueMoreThan50(value);
		}
		else if(value >= 20 && value < 50){
			valueMoreThan20lessThan50(value);
		}
		else if(value >= 10 && value < 20){
			valueMoreThan10lessThan20(value);
		}
		else if(value >= 5 && value < 10){
			valueMoreThan5lessThan10(value);
		}
		else if(value >= 2 && value < 5){
			valueMoreThan2lessThan5(value);
		}
		return getBankNotesString();		
	}


	@Override
	public String nameOfAlgoritm() {
		return "HighNotesAlgorithm";
	}



}
