import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String userId;
    private String pin;
    private double balance;
    private StringBuilder transactionHistory;

    public Account(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new StringBuilder();
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.append("Deposited: +").append(amount).append("\n");
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.append("Withdrawn: -").append(amount).append("\n");
            return true;
        }
        return false;
    }

    public void transfer(Account recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            transactionHistory.append("Transferred: -").append(amount)
                    .append(" to ").append(recipient.getUserId()).append("\n");
        }
    }

    public String getTransactionHistory() {
        return transactionHistory.toString();
    }
}

public class ATMInterface {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create some sample accounts
        accounts.put("user1", new Account("user1", "1234", 1000.0));
        accounts.put("user2", new Account("user2", "5678", 500.0));

        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();

        if (accounts.containsKey(userId)) {
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            Account account = accounts.get(userId);
            if (account.validatePin(pin)) {
                displayMenu(account);
            } else {
                System.out.println("Invalid PIN. Exiting...");
                System.out.println("------------------------------\n");
            }
        } else {
            System.out.println("Invalid user ID. Exiting...");
            System.out.println("------------------------------\n");
        }
    }

    private static void displayMenu(Account account) {
        while (true) {
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Balance Inquiry");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Transaction History:");
                    System.out.println(account.getTransactionHistory());
                    System.out.println("------------------------------\n");
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawn successfully.");
                        System.out.println("------------------------------\n");
                    } else {
                        System.out.println("Insufficient balance or invalid amount.");
                        System.out.println("------------------------------\n");
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposited successfully.");
                    System.out.println("------------------------------\n");
                    break;
                case 4:
                    System.out.print("Enter recipient's user ID: ");
                    String recipientId = scanner.next();
                    if (accounts.containsKey(recipientId)) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        account.transfer(accounts.get(recipientId), transferAmount);
                        System.out.println("Transferred successfully.");
                        System.out.println("------------------------------\n");
                    } else {
                        System.out.println("Recipient user ID not found.");
                        System.out.println("------------------------------\n");
                    }
                    break;
                case 5:
                    System.out.println("Current Balance: ₹" + account.getBalance());
                    System.out.println("------------------------------\n");
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.out.println("------------------------------\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    System.out.println("------------------------------\n");
            }
        }
    }
}