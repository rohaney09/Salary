

import model.Account;
import dao.AccountDAO;
import service.AccountService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        AccountService service = new AccountService();
        AccountDAO dao = new AccountDAO();

        System.out.print("Enter Debit Account Number: ");
        int debit = sc.nextInt();
        System.out.print("Enter Credit Account Number: ");
        int credit = sc.nextInt();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        boolean success = service.transfer(debit, credit, amount);

        if (success) {
            System.out.println("Transfer Successful ✅");
        } else {
            System.out.println("Transfer Failed ❌");
        }

        // Show all accounts
        List<Account> accounts = dao.getAllAccounts();
        accounts.forEach(System.out::println);
    }
}
