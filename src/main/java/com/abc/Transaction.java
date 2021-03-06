package com.abc;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
    public final double amount;

    protected Date transactionDate;

    public Date getTransactionDate() {
    	return transactionDate;
    }
    
    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.now();
    }
}
