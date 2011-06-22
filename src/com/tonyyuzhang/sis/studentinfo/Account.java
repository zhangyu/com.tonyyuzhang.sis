package com.tonyyuzhang.sis.studentinfo;

import java.math.BigDecimal;

import com.jimbob.ach.Ach;
import com.jimbob.ach.AchCredentials;
import com.jimbob.ach.AchResponse;
import com.jimbob.ach.AchStatus;
import com.jimbob.ach.AchTransactionData;

public class Account {
	public enum BankAccountType {
		CHECKING("ck"), SAVING("sv");
		private String value;
		private BankAccountType(String value) {
			this.value = value;
		}
		@Override
		public String toString() {
			return value;
		}
	}

	private BigDecimal balance = new BigDecimal("0.00");
	private int transactionCount = 0;
	private String bankAba;
	private String bankAccountNumber;
	private BankAccountType bankAccountType;
	private Ach ach;
	
	public void credit(BigDecimal amount) {
		balance = balance.add(amount);
		transactionCount++;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}

	public BigDecimal getTransactionAverage() {
		return balance.divide(
				new BigDecimal(transactionCount), BigDecimal.ROUND_HALF_UP);
	}

	void setBankAba(String bankAba) {
		this.bankAba = bankAba;	
	}

	void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	void setAch(Ach ach) {
		this.ach = ach;
	}

	public void setBankAccountType(BankAccountType bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	void transferFromBank(BigDecimal amount) {
		AchCredentials credentials = createCredentials();
		AchTransactionData data = createData(amount);
		
		Ach ach = getAch();
		AchResponse achResponse = ach.issueDebit(credentials, data);
		if (AchStatus.SUCCESS == achResponse.status)
			credit(amount);
	}

	private Ach getAch() {
		return ach;
	}

	private AchTransactionData createData(BigDecimal amount) {
		AchTransactionData data = new AchTransactionData();
		data.description = "transfer from bank";
		data.amount = amount;
		data.aba = bankAba;
		data.account = bankAccountNumber;
		data.accountType = bankAccountType.toString();
		return data;
	}

	private AchCredentials createCredentials() {
		AchCredentials credentials = new AchCredentials();
		credentials.merchantID = "12355";
		credentials.userName = "sismerc1920";
		credentials.password = "pitseleh411";
		return credentials;
	}
}