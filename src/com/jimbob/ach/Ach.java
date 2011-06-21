package com.jimbob.ach;

public interface Ach {
	public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data);
	public AchResponse markTransactionAsNSF(AchCredentials credentials, AchTransactionData data, String traceCode);
	public AchResponse refundTransaction(AchCredentials credentials, AchTransactionData data, String traceCode);
	public AchResponse issueCredit(AchCredentials credentials, AchTransactionData data);
	public AchResponse voidSameDayTransaction(AchCredentials credentials, AchTransactionData data, String traceCode);
	public AchResponse queryTransaction(AchCredentials credentials, AchTransactionData data, String traceCode);	
}