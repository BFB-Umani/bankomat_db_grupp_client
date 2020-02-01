package Bankomat.Model;

import Bankomat.Controller.BankomatSceneController;
import Bankomat.Controller.LoginSceneController;
import Bankomat.View.BankomatScene;
import Bankomat.View.LoginScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Scene scene;
    private LoginScene loginScene;
    private BankomatScene bankomatScene;
    private Client client = new Client();
    private Database db = new Database();
    public Stage stage = new Stage();

    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Shoe Shop");
        stage.setResizable(false);
        loginScene = new LoginScene();
        bankomatScene = new BankomatScene();

        scene = new Scene(loginScene.getDesignLayout(),480,620);
        scene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        stage.setScene(scene);

        LoginSceneController loginSceneController = new LoginSceneController(loginScene,this, db, client);
        loginSceneController.start();

        BankomatSceneController bankomatSceneController = new BankomatSceneController(bankomatScene,this, db, client);
        bankomatSceneController.start();

        stage.setOnCloseRequest(t -> {
            stage.close();
            System.exit(0);
        });

        stage.show();
    }

    public void goToLoginScene() {
        scene.setRoot(loginScene.getDesignLayout());
    }

    public void goToBankomatScene() {
        scene.setRoot(bankomatScene.getDesignLayout());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
