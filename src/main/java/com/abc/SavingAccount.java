package com.abc;

public class SavingAccount extends Account
{	
	@Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }
	
	@Override
	public String toString() {
		return "Savings Account";
	}
	
	@Override
	public double calculateInterest(double amount) {
        if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount-1000) * 0.002;
    }
}