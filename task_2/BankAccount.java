package task_2;

public class BankAccount {
    private int balance;

    public BankAccount(int _balance) {
        balance = _balance;
    }

    public void deposit(int value) {
        balance += value;
    }

    public void withdraw(int value) {
        balance -= value;
    }

    public int getBalance() {
        return balance;
    }
}
