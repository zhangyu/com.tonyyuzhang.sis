package com.tonyyuzhang.sis.studentinfo;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	private static final String ABA = "102000012";
	private static final String ACCOUNT_NUMBER = "1944321518811";
	
	private Account account;
	
	@Before
	public void setUp() throws Exception {
		account = new Account();
		account.setBankAba(ABA);
		account.setBankAccountNumber(ACCOUNT_NUMBER);
		account.setBankAccountType(Account.BankAccountType.CHECKING);
	}
	
	@Test
	public void testTransactions() {
		account.credit(new BigDecimal("0.10"));
		account.credit(new BigDecimal("10.00"));
		assertEquals(new BigDecimal("10.10"), account.getBalance());
	}
	@Test
	public void testTransactionAverage() {
		account.credit(new BigDecimal("0.10"));
		account.credit(new BigDecimal("11.00"));
		account.credit(new BigDecimal("2.99"));
		account.credit(new BigDecimal("4.70"));
		assertEquals(new BigDecimal("4.70"), account.getTransactionAverage());
	}
	
	@Test
	public void testTransferFromBank() {
		account.setAch(new com.jimbobach.ach.JimBobAch());
		
		final BigDecimal amount = new BigDecimal("50.00");
		account.transferFromBank(amount);
		
		assertEquals(amount, account.getBalance());
	}
}
