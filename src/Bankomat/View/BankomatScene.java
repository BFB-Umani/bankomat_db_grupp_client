package Bankomat.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BankomatScene {
    private VBox designLayout = new VBox();
    private Label bankomat = new Label("skriv uttag i SEK");
    private HBox nameArea = new HBox();
    private Label uttag = new Label("Uttag:          ");
    private TextField uttagField = new TextField();
    private Button okButton = new Button("OK");
    private Button showButton = new Button("Konton"); // J



    public void setUp() {
        designLayout.getChildren().add(bankomat);
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
