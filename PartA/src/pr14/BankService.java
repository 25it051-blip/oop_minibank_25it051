import java.util.ArrayList;

public class BankService {
    private ArrayList<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public int getAccountCount() {
        return accounts.size();
    }

    public void displayAccounts() {
        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Owner Name: " + account.getOwnerName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("----------------------");
        }
    }

    public void performAction(int choice) {
        if (choice < 1 || choice > 3) {
            throw new IllegalArgumentException("Invalid action!");
        }
    }
}