package Bankomat.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BankomatScene {
    private VBox designLayout = new VBox();
    private Label bankomat = new Label("skriv uttag i SEK");
    private HBox nameArea = new HBox();
    private ComboBox<String> cBox = new ComboBox<>();
    private Label uttag = new Label("Uttag:          ");
    private TextField uttagField = new TextField();
    private Button okButton = new Button("OK");
    private Button showButton = new Button("Konton"); // J
    private Button ok = new Button("ok");
    private Label orderLabel = new Label();
    private HBox comboBox = new HBox();


    public void setUp() {
        designLayout.getChildren().add(bankomat); // ComboBox för konton
        designLayout.getChildren().add(comboBox);
        designLayout.getChildren().add(nameArea);

        nameArea.getChildren().add(uttag);
        nameArea.getChildren().add(uttagField);

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().add(okButton);
        buttonLayout.getChildren().add(showButton); // J
        designLayout.getChildren().add(buttonLayout);

        bankomat.setPrefSize(300,275);
        bankomat.setMaxWidth(Double.MAX_VALUE);
        bankomat.setAlignment(Pos.CENTER);
        designLayout.setId("background");
        bankomat.setId("titelText");


        comboBox.getChildren().add(cBox); // Vet ej om man kan "centra" combobox utan HBox
        comboBox.setAlignment(Pos.CENTER);


        nameArea.setAlignment(Pos.CENTER);

        nameArea.setPadding(new Insets(10));

        uttag.setId("nameText");

        buttonLayout.setAlignment(Pos.CENTER);
        okButton.setPrefSize(88,45);
        buttonLayout.setId("buttonLayout");
        okButton.setMaxWidth(Double.MAX_VALUE);
        showButton.setPrefSize(88,45); // J
        showButton.setMaxWidth(Double.MAX_VALUE); // J
    }

    public void showBalance(List<Integer> balance){
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

        int count = 0;
        StringBuilder copy = new StringBuilder();
        for(int i: balance) {
            copy.append("Konto : ").append(++count).append(" Pengar: ").append(i).append("\n");
        }
        orderLabel.setText(copy.toString());


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

    // Sätter värdet på combobox (Sämst på JavaFX finns säkert 100 gånger bättre sätt att göra det på)

    public void setcBox(List<Integer> balance, int index) {
        cBox.getItems().clear();
        int count = 0;
        ObservableList<String> testList = FXCollections.observableArrayList();
        for(int i: balance) {
            testList.add("Konto: " + ++count + " Pengar: " + i);
        }
        cBox.setItems(testList);
        cBox.getSelectionModel().select(index);

    }


    public ComboBox<String> getcBox() {
        return cBox;
    }

    public VBox getDesignLayout() {
        return designLayout;
    }

    public TextField getUttagField() {
        return uttagField;
    }

    public Button getOkButton() {
        return okButton;
    }

    public Button getShowButton() {return showButton;} // J
}
