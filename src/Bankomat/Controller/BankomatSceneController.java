package Bankomat.Controller;

import Bankomat.Database.Repository;
import Bankomat.Main;
import Bankomat.Model.Account;
import Bankomat.Model.Client;
import Bankomat.View.BankomatScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        getAccounts();
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
            List<Account> accountList = rep.getAccounts(client.getID());
            List<Integer> balance;
            balance = accountList.stream().map(Account::getBalance).collect(Collectors.toList());
            bankomatScene.showBalance(balance);
        });

        // test
        bankomatScene.getcBox().setOnAction(l -> {
            System.out.println("Du valde: " + bankomatScene.getcBox().getValue() + "index " +
                    bankomatScene.getcBox().getSelectionModel().getSelectedIndex());
        });



    }
    public void getAccounts() {
        List<Account> accountList = rep.getAccounts(client.getID());
        List<Integer> balance;
        balance = accountList.stream().map(Account::getBalance).collect(Collectors.toList());
        bankomatScene.setcBox(balance);
    }
}

