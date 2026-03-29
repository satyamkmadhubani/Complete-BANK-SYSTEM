/*package Bank_System;*/
import java.util.*;

// Interface (Abstraction)
interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount);
}

// Base Class
class Bank {
    String bankName = "State Bank of India";
    String ifscCode = "SBIN0001234";
}

// Account Class
class Account extends Bank implements BankOperations {

    private String name;
    private int accountNumber;
    private int pin;
    private double balance;
    private ArrayList<String> history = new ArrayList<>();

    Account(String name, int accountNumber, int pin, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean checkPin(int p) {
        return this.pin == p;
    }

    public void deposit(double amount) {
        balance += amount;
        history.add("Deposited: " + amount);
        System.out.println("✅ Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            history.add("Withdrawn: " + amount);
            System.out.println("✅ Withdrawn: " + amount);
        } else {
            System.out.println("❌ Insufficient Balance");
        }
    }

    void showDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Bank: " + bankName);
        System.out.println("IFSC: " + ifscCode);
        System.out.println("Name: " + name);
        System.out.println("Account No: " + accountNumber);
        System.out.println("Balance: " + balance);
    }

    void showHistory() {
        System.out.println("\n--- Transaction History ---");
        for (String h : history) {
            System.out.println(h);
        }
    }
}

// Main Class
public class BankSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();

        while (true) {
            System.out.println("\n===== BANK SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();

                    System.out.print("Set PIN: ");
                    int pin = sc.nextInt();

                    System.out.print("Enter Initial Balance: ");
                    double bal = sc.nextDouble();

                    accounts.add(new Account(name, accNo, pin, bal));
                    System.out.println("✅ Account Created!");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int loginAcc = sc.nextInt();

                    System.out.print("Enter PIN: ");
                    int loginPin = sc.nextInt();

                    Account current = null;

                    for (Account acc : accounts) {
                        if (acc.getAccountNumber() == loginAcc && acc.checkPin(loginPin)) {
                            current = acc;
                            break;
                        }
                    }

                    if (current == null) {
                        System.out.println("❌ Invalid login!");
                        break;
                    }

                    System.out.println("✅ Login Successful!");

                    // User Menu
                    while (true) {
                        System.out.println("\n--- USER MENU ---");
                        System.out.println("1. Show Details");
                        System.out.println("2. Deposit");
                        System.out.println("3. Withdraw");
                        System.out.println("4. Transaction History");
                        System.out.println("5. Logout");

                        int ch = sc.nextInt();

                        switch (ch) {
                            case 1:
                                current.showDetails();
                                break;
                            case 2:
                                System.out.print("Enter amount: ");
                                current.deposit(sc.nextDouble());
                                break;
                            case 3:
                                System.out.print("Enter amount: ");
                                current.withdraw(sc.nextDouble());
                                break;
                            case 4:
                                current.showHistory();
                                break;
                            case 5:
                                System.out.println("🔓 Logged out");
                                break;
                            default:
                                System.out.println("❌ Invalid choice");
                        }

                        if (ch == 5) break;
                    }
                    break;

                case 3:
                    System.out.println("👋 Thank you!");
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid choice");
            }
        }
    }
}
