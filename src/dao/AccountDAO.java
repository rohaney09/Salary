package dao;

import model.Account;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public List<Account> getAllAccounts()throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM sal";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt("acc_number"),
                        rs.getString("name"),
                        rs.getDouble("balance")));
            }
        }
        return accounts;
    }

    public double getBalance(int accNo, Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT balance FROM sal WHERE acc_number = ?");
        ps.setInt(1, accNo);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getDouble("balance") : -1;
    }

    public int updateBalance(int accNo, double amount,Connection conn, boolean debit) throws SQLException {
        String sql = debit ?
                "UPDATE sal SET balance = balance - ? WHERE acc_number = ?" :
                "UPDATE sal SET balance = balance + ? WHERE acc_number = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, amount);
        ps.setInt(2, accNo);
        return ps.executeUpdate();
    }
}

