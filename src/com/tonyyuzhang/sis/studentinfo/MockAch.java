package com.tonyyuzhang.sis.studentinfo;

import com.jimbob.ach.Ach;
import com.jimbob.ach.AchCredentials;
import com.jimbob.ach.AchResponse;
import com.jimbob.ach.AchStatus;
import com.jimbob.ach.AchTransactionData;
import java.util.*;
import org.junit.Assert;

class MockAch implements Ach {

	@Override
	public AchResponse issueDebit(AchCredentials credentials,
			AchTransactionData data) {
		Assert.assertTrue(data.account.equals(AccountTest.ACCOUNT_NUMBER));
		Assert.assertTrue(data.aba.equals(AccountTest.ABA));
		
		AchResponse response = new AchResponse();
		response.timestamp = new Date();
		response.traceCode = "1";
		response.status = AchStatus.SUCCESS;
		return response;
	}

	@Override
	public AchResponse markTransactionAsNSF(AchCredentials credentials,
			AchTransactionData data, String traceCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AchResponse refundTransaction(AchCredentials credentials,
			AchTransactionData data, String traceCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AchResponse issueCredit(AchCredentials credentials,
			AchTransactionData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AchResponse voidSameDayTransaction(AchCredentials credentials,
			AchTransactionData data, String traceCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AchResponse queryTransaction(AchCredentials credentials,
			AchTransactionData data, String traceCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
