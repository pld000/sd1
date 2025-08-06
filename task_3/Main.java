class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount >= 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount >= 0 && balance >= amount) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        System.out.println("Начальный баланс: " + account.getBalance());

        account.deposit(500);
        System.out.println("Баланс после депозита 500: " + account.getBalance());

        account.withdraw(200);
        System.out.println("Баланс после снятия 200: " + account.getBalance());

        account.withdraw(2000);
        System.out.println("Баланс после снятия 2000: " + account.getBalance());

        account.deposit(-100); // Некорректный депозит
        System.out.println("Баланс после некорректного депозита -100: " + account.getBalance());

        account.withdraw(-50); // Некорректное снятие
        System.out.println("Баланс после некорректного снятия -50: " + account.getBalance());
    }
}