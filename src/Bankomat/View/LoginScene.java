package Bankomat.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class LoginScene {
    private VBox designLayout = new VBox();
    private Label bankomat = new Label("Bankomat Login");
    private HBox nameArea = new HBox();
    private HBox passwdArea = new HBox();
    private Label name = new Label("PersonNummer:     ");
    private Label passwd = new Label("PIN-kod:                 ");
    private TextField nameField = new TextField();
    private TextField passwdField = new TextField();
    private Button loginButton = new Button("login");



    public void setUp() {
        designLayout.getChildren().add(bankomat);
        designLayout.getChildren().add(nameArea);
        designLayout.getChildren().add(passwdArea);

        nameArea.getChildren().add(name);
        nameArea.getChildren().add(nameField);
        passwdArea.getChildren().add(passwd);
        passwdArea.getChildren().add(passwdField);

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().add(loginButton);
        designLayout.getChildren().add(buttonLayout);

        bankomat.setPrefSize(300,275);
        bankomat.setMaxWidth(Double.MAX_VALUE);
        bankomat.setAlignment(Pos.CENTER);
        designLayout.setId("background");
        bankomat.setId("titelText");

        nameArea.setAlignment(Pos.CENTER);
        passwdArea.setAlignment(Pos.CENTER);

        nameArea.setPadding(new Insets(10));
        passwdArea.setPadding(new Insets(10));

        name.setId("nameText");
        passwd.setId("nameText");

        buttonLayout.setAlignment(Pos.CENTER);
        loginButton.setPrefSize(88,45);
        buttonLayout.setId("buttonLayout");
        loginButton.setMaxWidth(Double.MAX_VALUE);
    }


    public VBox getDesignLayout() {
        return designLayout;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getPasswdField() {
        return passwdField;
    }

    public Button getLoginButton() {
        return loginButton;
    }
}
