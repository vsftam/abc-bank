package com.abc;

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
        if (amount <= 1000)
            return amount * 0.02;
        if (amount <= 2000)
            return 20 + (amount-1000) * 0.05;
        return 70 + (amount-2000) * 0.1;
    }
}