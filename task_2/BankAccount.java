package task_2;

public class BankAccount {
    private double balance;

    public BankAccount(double _balance) {
        balance = _balance;
    }

    public void deposit(double value) {
        balance += value;
    }

    public void withdraw(double value) {
        balance -= value;
    }

    public double getBalance() {
        return balance;
    }
}
