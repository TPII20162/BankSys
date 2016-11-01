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

}
