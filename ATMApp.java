package com.rahul;
import java.util.*;

class Account {
    private String userID;
    private String userPIN;
    private double balance;
    private List<String> transactionHistory;

    public Account(String userID, String userPIN, double initialBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public boolean authenticate(String userPIN) {
        return this.userPIN.equals(userPIN);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdraw: -" + amount);
            return true;
        }
        return false;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

class ATMInterface {
    private Scanner scanner;
    private Account currentAccount;

    public ATMInterface() {
        scanner = new Scanner(System.in);
        // In a real application, you would load account data from a database or file.
        currentAccount = new Account("user123", "1234", 1000.0);
    }

    public void run() {
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter your user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String userPIN = scanner.nextLine();

        if (currentAccount.getUserID().equals(userID) && currentAccount.authenticate(userPIN)) {
            System.out.println("Authentication successful!");
            showMenu();
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }

    private void showMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. View Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewTransactionHistory();
                    break;
                case 2:
                    performWithdrawal();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void viewTransactionHistory() {
        List<String> history = currentAccount.getTransactionHistory();
        System.out.println("\nTransaction History:");
        for (String transaction : history) {
            System.out.println(transaction);
        }
    }

    private void performWithdrawal() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (currentAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
            currentAccount.addTransaction("Withdraw: -" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void performDeposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        currentAccount.deposit(amount);
        currentAccount.addTransaction("Deposit: +" + amount);
        System.out.println("Deposit successful.");
    }

    private void performTransfer() {
        System.out.print("Enter the recipient's account ID: ");
        String recipientID = scanner.next();
        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        // In a real application, you would need to implement the transfer logic.
        // This example demonstrates the basic idea.
        currentAccount.withdraw(amount);
        currentAccount.addTransaction("Transfer to " + recipientID + ": -" + amount);

        System.out.println("Transfer successful.");
    }
}

public class ATMApp {
    public static void main(String[] args) {
        ATMInterface atmInterface = new ATMInterface();
        atmInterface.run();
    }
}

