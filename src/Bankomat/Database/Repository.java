package Bankomat.Database;

import Bankomat.Model.Account;
import Bankomat.Model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Connection con;

    public Repository() {
        con = dbConnection.getConnection();
    }

    // Lägger in konton i en lista. Tar ut accountId skickar till "getAccountById()" som returnerar Account.
    public List<Account> getAccounts (int custId) {
        List<Account> accountList = new ArrayList<>();
        try(CallableStatement stmt = con.prepareCall("CALL bankdatabase.accountState(?)")) {
            stmt.setInt(1, custId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                accountList.add(getAccountById(rs.getInt("AccountId")));
            }


        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        for(Account i: accountList) {
            System.out.println(i);
        }
        return accountList;
    }

    // Tar accountId och skapar ett Account objekt
    public Account getAccountById(int accId) {
        Account account = null;
        String query = "SELECT AccountID, balance from bankdatabase.accounts where AccountId = ?";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, accId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                account = new Account(rs.getInt("AccountID"), rs.getInt("balance"));
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    // Hämtar klient om Personnr stämmer med Pin.
    public Client getClient(String persNr, int pinCode) {
        Client client = null;
        try(CallableStatement stmt = con.prepareCall("CALL bankdatabase.checkCred(?, ?)")) {
            stmt.setString(1, persNr);
            stmt.setInt(2, pinCode);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                client = getClientById(rs.getInt("CustomerID"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    // Hämtar all klient info med klient id
    public Client getClientById(int custId) {
        Client client = null;
        String query = "SELECT * from bankdatabase.customer where CustomerID = ?";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, custId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                client = new Client(rs.getInt("CustomerID"), rs.getString("firstname")
                ,rs.getString("lastname"), rs.getString("personalNumber"));
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }


}
