package task_2;

public class BankOperations {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(50000);
        System.out.println("Initial balance: " + bankAccount.getBalance());

        bankAccount.deposit(10000);
        System.out.println("After deposit balance: " + bankAccount.getBalance());

        bankAccount.withdraw(100000);
        System.out.println("After withdraw balance: " + bankAccount.getBalance());
    }
}
