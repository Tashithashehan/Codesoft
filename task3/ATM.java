import java.util.Scanner;

class ATM {

    private BankAccount ba;
    private Scanner sc;

    public ATM(BankAccount ba) {
        this.ba = ba;
        this.sc = new Scanner(System.in);
    }

    private void display() {
        System.out.println("----ATM MENU----");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Quit");
        System.out.print("Enter your choice: ");
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + ba.getBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();

        if (ba.deposit(amount)) {
            System.out.println("Deposit Successful \nYour new Balance is: $" + ba.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        if (ba.withdraw(amount)) {
            System.out.println("Withdraw successful.\nYour new balance is: $" + ba.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankAccount ba = new BankAccount(1000.0);
        ATM atm = new ATM(ba);

        boolean quit = false;
        while (!quit) {
            atm.display();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;

                case 2:
                    atm.deposit();
                    break;

                case 3:
                    atm.withdraw();
                    break;

                case 4:
                    quit = true;
                    System.out.println("Thank You");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}
