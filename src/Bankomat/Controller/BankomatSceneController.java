package Bankomat.Controller;

import Bankomat.Database.Repository;
import Bankomat.Model.Account;
import Bankomat.Model.Client;
import Bankomat.Main;
import Bankomat.View.BankomatScene;
import javafx.application.Platform;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BankomatSceneController {

    private int uttag;
    private BankomatScene bankomatScene;
    private Main main;
    private Client client;
    private Repository rep;


    public BankomatSceneController(BankomatScene bankomatScene, Main main, Client client) {
        this.bankomatScene = bankomatScene;
        this.main = main;
        this.client = client;
        this.rep = new Repository();
    }

    public void start() {
        bankomatScene.setUp();

        bankomatScene.getOkButton().setOnAction(actionEvent -> {
            try {
                uttag = Integer.parseInt(bankomatScene.getUttagField().getText());
            }catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "du måste skriva in ett positivt heltal");
            }

            // kod för att ändra summa på konto utifrån kundens uttag

        });

        // Skriver ut balance i console (vill ha ny ruta FX))
        bankomatScene.getShowButton().setOnAction(actionEvent -> {
            System.out.println("This is my id " + client.getID());
            List<Account> accountList = new ArrayList<>();
            accountList = rep.getAccounts(client.getID());
            System.out.println(accountList.size());
            int count = 0;
            for(Account a: accountList) {
                System.out.println("Konto " + ++count + " " + a.getBalance());
            }
        });

    }
}
