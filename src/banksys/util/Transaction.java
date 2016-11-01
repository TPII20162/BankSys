package banksys.util;

import java.util.Date;

/**
 * Class basic for implements a Statement(Extrato) function
 *
 *
 *
 */
public class Transaction {

	/** ID references for this transaction*/
	private String id;
	
	/** Number of account that suffer this transaction*/
	private String numberAccount;
	
	/** Type of transaction (debit, credit, transfer, bonus, interest).
	 * 
	 * 	Question: Devemos usar os nomes referentes a cada transação, ou melhor um valor inteiro para identificar cada uma?(Ganha em peformance)
	 */
	private String type;
	
	/** The value modified by this transaction*/
	private double value;
	
	/** Date when the transaction ocurr . (Java.util)*/
	private Date date;

	public Transaction(String id, String numberAccount, String type, double value, Date date) {
		super();
		this.id = id;
		this.numberAccount = numberAccount;
		this.type = type;
		this.value = value;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
