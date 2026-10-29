import exception.BankException;
import java.util.Scanner;
import model.*;
import service.BankService;

public class Main {
public static void main(String[] args) {
BankInfo info = new BankInfo("CHARUSAT MiniBank", "CSPIT Changa Branch");
BankService service = new BankService();

    service.loadState();

    Scanner sc = new Scanner(System.in);
    System.out.println("==================================");
    System.out.println(info.name() + " - " + info.branch());
    System.out.println("==================================");

    while (true) {
        System.out.println("\n1. Open Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Account Statement");
        System.out.println("6. List Accounts");
        System.out.println("7. Daily Report");
        System.out.println("8. Exit");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Mobile: ");
                    String mobile = sc.nextLine();
                    System.out.print("Address Line: ");
                    String line = sc.nextLine();
                    System.out.print("City: ");
                    String city = sc.nextLine();
                    System.out.print("PIN: ");
                    String pin = sc.nextLine();
                    System.out.print("Initial Balance: ");
                    long balance = sc.nextLong();

                    Account a = service.openAccount(name, email, mobile,
                            line, city, pin, balance);
                    System.out.println("Account Created: " + a.getAccountNumber());
                }
                case 2 -> {
                    System.out.print("Account Number: ");
                    String acc = sc.nextLine();
                    System.out.print("Amount: ");
                    long amount = sc.nextLong();
                    service.deposit(acc, amount);
                    System.out.println("Deposit Successful!");
                }
                case 3 -> {
                    System.out.print("Account Number: ");
                    String acc = sc.nextLine();
                    System.out.print("Amount: ");
                    long amount = sc.nextLong();
                    service.withdraw(acc, amount);
                    System.out.println("Withdrawal Successful!");
                }
                case 4 -> {
                    System.out.print("From Account: ");
                    String from = sc.nextLine();
                    System.out.print("To Account: ");
                    String to = sc.nextLine();
                    System.out.print("Amount: ");
                    long amount = sc.nextLong();
                    service.transfer(from, to, amount);
                    System.out.println("Transfer Successful!");
                }
                case 5 -> {
                    System.out.print("Account Number: ");
                    String acc = sc.nextLine();
                    System.out.println(service.statement(acc));
                }
                case 6 -> {
                    for (Account a : service.listAccountsSortedByBalance())
                        System.out.println(a);
                }
                case 7 -> {
                    service.generateDailyReport();
                    System.out.println("Daily report saved in data/daily_report.txt");
                }
                case 8 -> {
                    service.saveState();
                    System.out.println("Thank you for using MiniBank!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid Choice!");
            }
        } catch (BankException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
}
