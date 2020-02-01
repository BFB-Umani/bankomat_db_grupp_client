package Bankomat.Controller;

import Bankomat.Model.Database;
import Bankomat.Model.Main;
import Bankomat.Model.Client;
import Bankomat.View.LoginScene;

public class LoginSceneController {
    private String personNr;
    private String PIN;
    private LoginScene loginScene;
    private Database database;
    private Main main;
    private Client client;
    private boolean isClient = false;


    public LoginSceneController(LoginScene loginScene, Main main, Database database, Client client) {
        this.loginScene = loginScene;
        this.main = main;
        this.database = database;
        this.client = client;
    }

    public void start() {
        loginScene.setUp();

        loginScene.getLoginButton().setOnAction(actionEvent -> {
            personNr = loginScene.getNameField().getText();
            PIN = loginScene.getPasswdField().getText();

            // kod för att hantera inloggning här, t.ex matcha personNr mot pin-kod

            if(isClient) {
                main.goToBankomatScene();
                // byter scen till bankomaten för uttag, ändra isClient till true för att se nästa scen.
            }
        });

    }
}
