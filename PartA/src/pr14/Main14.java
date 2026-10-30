import java.util.Scanner;

public class Main14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();

        int choice;

        do {
            System.out.println("\n===== Mini Bank Menu =====");
            System.out.println("1. Add Account");
            System.out.println("2. Display Accounts");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            try {
                service.performAction(choice);

                switch (choice) {
                    case 1:
                        System.out.print("Enter Account Number: ");
                        int number = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter Owner Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Balance: ");
                        double balance = sc.nextDouble();

                        Account account =
                            new Account(number, name, balance);

                        service.addAccount(account);

                        System.out.println("Account Added Successfully!");
                        break;

                    case 2:
                        service.displayAccounts();
                        break;

                    case 3:
                        System.out.println("Thank You!");
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 3);

        sc.close();
    }
}