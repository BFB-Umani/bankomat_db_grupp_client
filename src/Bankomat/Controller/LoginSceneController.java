package Bankomat.Controller;

import Bankomat.Database.Repository;
import Bankomat.Main;
import Bankomat.Model.Client;
import Bankomat.View.LoginScene;

import javax.swing.*; // J Catch för en JOPTIONPANE.

public class LoginSceneController {
    private String personNr;
    private int PIN;
    private LoginScene loginScene;
    private Main main;
    private Repository rep;


    public LoginSceneController(LoginScene loginScene, Main main) {
        this.loginScene = loginScene;
        this.main = main;
        this.rep = new Repository();
    }

    public void start() {
        loginScene.setUp();

        loginScene.getLoginButton().setOnAction(actionEvent -> {
            try {
                personNr = loginScene.getNameField().getText();
                PIN = Integer.parseInt(loginScene.getPasswdField().getText());
                System.out.println(personNr + " % " + PIN);
                Client client = rep.getClient(personNr, PIN);
                if(client != null) {
                    main.goToBankomatScene(client);
                }
                System.out.println("Loginscene " + client.getID());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PersonNr + PIN (HELTAL)"); // ändra
            }



        });

    }
}
