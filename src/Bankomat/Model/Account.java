package Bankomat.Model;

public class Account {
    private int id;
    private int balance;
   // private Admin admin;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }
}
