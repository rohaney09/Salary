package service;

import dao.AccountDAO;
import util.DBConnection;

import java.sql.Connection;

public class AccountService {
    private final AccountDAO dao = new AccountDAO();

    public boolean transfer(int debitAcc, int creditAcc, double amount) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            double balance = dao.getBalance(debitAcc, conn);

            if (balance < amount) {
                System.out.println("Insufficient Balance in Account " + debitAcc);
                conn.rollback();
                return false;
            }

            int row1 = dao.updateBalance(debitAcc, amount, conn, true);
            int row2 = dao.updateBalance(creditAcc, amount, conn, false);

            if (row1 > 0 && row2 > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

