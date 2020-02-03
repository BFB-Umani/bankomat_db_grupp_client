package Bankomat.Controller;

import Bankomat.Database.Repository;
import Bankomat.Main;
import Bankomat.Model.Account;
import Bankomat.Model.Client;
import Bankomat.Model.Loan;
import Bankomat.View.BankomatScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BankomatSceneController {

    private int uttag;
    private BankomatScene bankomatScene;
    private Main main;
    private Client client;
    private Repository rep;
    private List<Account> accountList;
    private List<Integer> balance;


    public BankomatSceneController(BankomatScene bankomatScene, Main main, Client client) {
        this.bankomatScene = bankomatScene;
        this.main = main;
        this.client = client;
        this.rep = new Repository();
    }

    public void start() {
        getAccounts(0);
        bankomatScene.setUp();

        bankomatScene.getOkButton().setOnAction(actionEvent -> {
            try {
                uttag = Integer.parseInt(bankomatScene.getUttagField().getText());

            }catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "du måste skriva in ett positivt heltal");
            }
            catch (IndexOutOfBoundsException e) {

            }

            // kod för att ändra summa på konto utifrån kundens uttag
            int comboIndex = bankomatScene.getcBox().getSelectionModel().getSelectedIndex();
            int accId = accountList.get(comboIndex).getId();
            if(accountList.get(comboIndex).getBalance() > uttag) {
                if (rep.withdrawMoney(accId, uttag)) {
                    System.out.println("Uttaget lyckades");
//                    accountList.get(comboIndex).setBalance(accountList.get(comboIndex).getBalance() - uttag);
                    bankomatScene.getcBox().getItems().clear();
                    getAccounts(comboIndex);
                }
            }
            else {
                System.out.println("Error, uttag för stort");
            }




        });

        // Skriver ut konton och eventuella lån.
        bankomatScene.getShowButton().setOnAction(actionEvent -> {
            balance = accountList.stream().map(Account::getBalance).collect(Collectors.toList());
            List<Loan> loanList= rep.getLoans(client.getID());
            List<String> loanStringList = new ArrayList<>();
            int count = 0;
            for(Loan l: loanList) {
                double startWithInterest = (l.getStartAmount() * (l.getInterestRate()/100) + l.getStartAmount());
                loanStringList.add("Lån:" + ++count + " Start med räntan: " + l.getInterestRate() + "% = "
                + startWithInterest + "Summa kvar: " + (startWithInterest - l.getPaidAmount()) + " att betala");

            }

            bankomatScene.showBalance(balance, loanStringList);
        });

        // test output för combobox
        bankomatScene.getcBox().setOnAction(l -> {
            System.out.println("Du valde: " + bankomatScene.getcBox().getValue() + "index " +
                    bankomatScene.getcBox().getSelectionModel().getSelectedIndex());
        });



    }
    public void getAccounts(int index) {
        accountList = rep.getAccounts(client.getID());
        balance = accountList.stream().map(Account::getBalance).collect(Collectors.toList());
        bankomatScene.setcBox(balance, index);
    }

}

