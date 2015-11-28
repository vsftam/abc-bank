package com.abc;

import java.util.Calendar;
import java.util.Date;

public class MaxiSavingAccount extends Account
{
	@Override
    public AccountType getAccountType() {
        return AccountType.MAXI_SAVINGS;
    }
	
	@Override
	public String toString() {
		return "Maxi Savings Account";
	}
	
	@Override
    public double calculateInterest(double amount) {
		/**
        if (amount <= 1000)
            return amount * 0.02;
        if (amount <= 2000)
            return 20 + (amount-1000) * 0.05;
        return 70 + (amount-2000) * 0.1;
        */
		return ( hasWithdrawalInPast10Days() ? 0.001 : 0.05 ) * amount;
    }
	
	private boolean hasWithdrawalInPast10Days() {
		Date now = DateProvider.now();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, -10);
		Date tenDaysAgo = cal.getTime();
		
		for(Transaction t: transactions) {			
			if(t.getTransactionDate().after( tenDaysAgo ) && t.amount < 0.0 ) {
				return true;
			}
		}
		return false;
	}
}