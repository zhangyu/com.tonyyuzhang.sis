package com.tonyyuzhang.sis.studentinfo;

import java.math.*;

public interface Accountable {
   public void credit(BigDecimal amount);
   public BigDecimal getBalance();
   public BigDecimal getTransactionAverage();
   public void setBankAba(String bankAba);
   public void setBankAccountNumber(String bankAccountNumber);
   public void setBankAccountType(
         com.tonyyuzhang.sis.studentinfo.Account.BankAccountType bankAccountType);
   public void transferFromBank(BigDecimal amount);
}
