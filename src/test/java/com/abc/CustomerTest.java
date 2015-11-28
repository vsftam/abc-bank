package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static com.abc.AccountType.*;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = Account.ofType(CHECKING);
        Account savingsAccount = Account.ofType(SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOpenAccount(){
        Customer oscar = new Customer("Oscar").openAccount(Account.ofType(SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
        
        oscar.openAccount(Account.ofType(CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testInvalidDespositWithdraw() {
    	Account checkingAccount = Account.ofType(CHECKING);
    	Customer oscar = new Customer("Oscar").openAccount(checkingAccount);
    	try {
    		checkingAccount.deposit(-1000);
    		fail("Should have thrown IllegalArgumentException for negative deposit");
    	}
    	catch (IllegalArgumentException e){
    		// okay!
    	}
    	
    	try {
    		checkingAccount.withdraw(-1000);
    		fail("Should have thrown IllegalArgumentException for negative withdraw");
    	}
    	catch (IllegalArgumentException e){
    		// okay!
    	}
    }
}
