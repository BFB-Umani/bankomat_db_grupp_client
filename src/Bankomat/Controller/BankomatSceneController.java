package Bankomat.Controller;

import Bankomat.Model.Client;
import Bankomat.Model.Database;
import Bankomat.Model.Main;
import Bankomat.View.BankomatScene;

import javax.swing.*;

public class BankomatSceneController {

    private int uttag;
    private BankomatScene bankomatScene;
    private Database database;
    private Main main;
    private Client client;


    public BankomatSceneController(BankomatScene bankomatScene, Main main, Database database, Client client) {
        this.bankomatScene = bankomatScene;
        this.main = main;
        this.database = database;
        this.client = client;
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

    }
}
