package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static com.abc.AccountType.*;


public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(Account.ofType(CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
        
        Customer jane = new Customer("Jane");
        jane.openAccount(Account.ofType(SAVINGS));
        jane.openAccount(Account.ofType(MAXI_SAVINGS));
        bank.addCustomer(jane);
        
        assertEquals("Customer Summary\n - John (1 account)\n - Jane (2 accounts)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = Account.ofType(CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account savingsAccount = Account.ofType(SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
        
        savingsAccount.withdraw(750.0);
        
        assertEquals(0.75, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxisavingsAccount = Account.ofType(MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(maxisavingsAccount));

        maxisavingsAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
        
        maxisavingsAccount.withdraw(1500);
        
        assertEquals(45, bank.totalInterestPaid(), DOUBLE_DELTA);
        
        maxisavingsAccount.withdraw(1000);
        
        assertEquals(10, bank.totalInterestPaid(), DOUBLE_DELTA);        
    }
    
    @Test
    public void firstCustomer() {
    	Bank bank = new Bank();
    	assertEquals("Error", bank.getFirstCustomer());
    	
    	bank.addCustomer(new Customer("Amy").openAccount(Account.ofType(CHECKING)));
    	bank.addCustomer(new Customer("Bill").openAccount(Account.ofType(SAVINGS)));
    	bank.addCustomer(new Customer("Carol").openAccount(Account.ofType(MAXI_SAVINGS)));
    	assertEquals("Amy", bank.getFirstCustomer());
    }

    @Test
    public void totalInterestPaid() {
    	Bank bank = new Bank();
        Account checkingAccount = Account.ofType(CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        checkingAccount.deposit(1000.0);
        bank.addCustomer(bill);
        
        Account savingsAccount = Account.ofType(SAVINGS);
        Customer amy = new Customer("Amy").openAccount(savingsAccount);
        savingsAccount.deposit(2000.0);
        
        Account maxisavingsAccount = Account.ofType(MAXI_SAVINGS);
        amy.openAccount(maxisavingsAccount);
        maxisavingsAccount.deposit(3000.0);
        bank.addCustomer(amy);
        
        assertEquals(174, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
}
