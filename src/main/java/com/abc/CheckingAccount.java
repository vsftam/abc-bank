package com.abc;

public class CheckingAccount extends Account
{
	@Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
	
	@Override
	public String toString() {
		return "Checking Account";
	}
	
	@Override
	public double calculateInterest(double amount) {
        return amount * 0.001;
    }

}