package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //Creation of Objects

    protected static Player player1 = new Player();
    protected static Player player2 = new Player();
    protected static Movement movement = new Movement();
    protected static WhoGoesFirst whoGoesFirst = new WhoGoesFirst();
    protected static Fuel fuel = new Fuel();
    //------------------------------------------------------------------------------------------------------------------


    //Method that launches the Welcome Box and initializes the first scene

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeBox.fxml"));
        primaryStage.setTitle("Let's Race!");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }
    //------------------------------------------------------------------------------------------------------------------

    //Main Method

    public static void main(String[] args) {

        launch(args);
    }
    //------------------------------------------------------------------------------------------------------------------
}
