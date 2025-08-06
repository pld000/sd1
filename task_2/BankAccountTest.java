
package task_2;

import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    @Test()
    void bankAccountTest() {
        double balance = 50000;
        double depositValue = 10000;
        double withdrawValue = 70000;
        BankAccount bankAccount = new BankAccount(balance);
        Assertions.assertEquals(balance, bankAccount.getBalance(), "Wrong getBalance method");

        bankAccount.deposit(depositValue);
        Assertions.assertEquals(balance + depositValue, bankAccount.getBalance(), "Wrong deposit method");

        bankAccount.withdraw(withdrawValue);
        Assertions.assertEquals(balance + depositValue - withdrawValue, bankAccount.getBalance(), "Wrong withdraw method");
    }
}
