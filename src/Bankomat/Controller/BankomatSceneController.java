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
import java.util.List;

public class BankomatSceneController {

    private int uttag;
    private BankomatScene bankomatScene;
    private Main main;
    private Client client;
    private Repository rep;
    private Button ok = new Button("ok");
    private Label orderLabel = new Label();


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
            List<Account> accountList = rep.getAccounts(client.getID());
            System.out.println(accountList.size());
            int count = 0;
            for(Account a: accountList) {
                System.out.println("Konto " + ++count + " " + a.getBalance());
            }

            showBalance();
        });

    }

    public void showBalance(){
        Stage dialogStage = new Stage();
        VBox layout = new VBox();
        HBox hBox = new HBox(orderLabel);
        HBox buttons = new HBox(ok);
        layout.getChildren().add(hBox);
        layout.getChildren().add(buttons);
        layout.setMinSize(400,50);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setMinSize(300,60);
        orderLabel.setAlignment(Pos.CENTER);
        orderLabel.setText("");
        hBox.setAlignment(Pos.CENTER);
        ok.setPrefSize(88,45);
        buttons.setPadding(new Insets(15, 0, 10, 0));
        ok.setCursor(Cursor.HAND);

        ok.setOnAction(actionEvent -> {
            dialogStage.close();
        });

        dialogStage.setResizable(false);
        dialogStage.setScene(new Scene(layout));
        dialogStage.show();
        dialogStage.setOnCloseRequest(t -> {
            dialogStage.close();
        });
    }
}
