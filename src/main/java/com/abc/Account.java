package com.abc;

import java.util.ArrayList;
import java.util.List;
import static com.abc.AccountType.*;

public abstract class Account {

    public List<Transaction> transactions;

    protected Account() {
        this.transactions = new ArrayList<Transaction>();
    }

    public static Account ofType(AccountType type) {
    	switch (type) {
    	case CHECKING:
    		return new CheckingAccount();
    	case SAVINGS:
    		return new SavingAccount();
    	case MAXI_SAVINGS:
    		return new MaxiSavingAccount();
    	default:
    		throw new IllegalArgumentException("not supported account type " + type);
    	}
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else {
	        transactions.add(new Transaction(-amount));
	    }
	}

    public double interestEarned() {
        double amount = sumTransactions();
        return calculateInterest(amount);
    }

    double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    abstract public AccountType getAccountType();
    
    abstract public String toString();
    
    abstract protected double calculateInterest(double amount);
}
