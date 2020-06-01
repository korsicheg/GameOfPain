package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class ControlController extends Controller implements Initializable {


    //FXML objects Initialization

    @FXML
    private Label player1Control;
    @FXML
    private Label player2Control;
    @FXML
    private Button rollButton;
    @FXML
    private Label fuel1;
    @FXML
    private Label fuel2;
    @FXML
    private Label whoRolled;
    @FXML
    private ProgressBar fuelBar1;
    @FXML
    private ProgressBar fuelBar2;
    @FXML
    public ImageView diceImage;


    //------------------------------------------------------------------------------------------------------------------

    // Variable initialization
    protected static Image diceOneImage = new Image("images/imageDice1.jpg");

    //------------------------------------------------------------------------------------------------------------------

    //This method initializes the values of objects
    public void initialize(URL location, ResourceBundle resources) {
        diceImage.setImage(diceOneImage);
        whoGoesFirst.whoGoes(player1, player2, whoRolled);
        rollButton.setOnMouseClicked(event -> roll());
        player1Control.setText(player1.getName());

        //initializing the color of the fuel bar object and the name label
        if (player1.getColor().equals("red")) {
            player1Control.setStyle("-fx-text-fill: red;");
            fuelBar1.setStyle("-fx-accent: red;");
            player2Control.setStyle("-fx-text-fill: blue;");
            fuelBar2.setStyle("-fx-accent: blue;");
        } else {
            player1Control.setStyle("-fx-text-fill: blue;");
            fuelBar1.setStyle("-fx-accent: blue;");
            player2Control.setStyle("-fx-text-fill: red;");
            fuelBar2.setStyle("-fx-accent: red;");
        }
        //Initializing the values of the objects

        player2Control.setText(player2.getName());
        fuel1.setText("Fuel meter: " + player1.getFuel());
        fuelBar1.setProgress(player1.getFuel() / 1.2);
        fuel2.setText("Fuel meter: " + player2.getFuel());
        fuelBar2.setProgress(player2.getFuel() / 1.2);
        diceImage.setImage(diceOneImage);


    }
    //------------------------------------------------------------------------------------------------------------------

    //Code Winner by Minos for Image changing while turning
    //This method changes the car image based on the way its going (right or left)

    public void carGoBrrr(Player player) {
        if (startingPosition % 2 != 0) {
            if (player.getCurrentRow() % 2 != 0) {
                if (player.getColor().equals("blue")) {
                    blueCar.setImage(blueCarImage);
                } else {
                    redCar.setImage(redCarImage);
                }
            } else {
                if (player.getColor().equals("blue")) {
                    blueCar.setImage(blueCarLImage);
                } else {
                    redCar.setImage(redCarLImage);
                }
            }
        } else {
            if (player.getCurrentRow() % 2 == 0) {
                if (player.getColor().equals("blue")) {
                    blueCar.setImage(blueCarImage);
                } else {
                    redCar.setImage(redCarImage);
                }
            } else {
                if (player.getColor().equals("blue")) {
                    blueCar.setImage(blueCarLImage);
                } else {
                    redCar.setImage(redCarLImage);
                }
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    //Movement of the objects is achieved through the use of the Movement class
    //This part of the code is activated when the roll button is pressed.
    public void roll() {
        diceImage.setImage(diceOneImage);
        if (player1.getPriority() == 0) {
            whoRolled.setText(player1.getName() + " WINS!");
            if (player1.getColor().equals("blue")) {
                whoRolled.setStyle("-fx-text-fill: blue;");
            } else {
                whoRolled.setStyle("-fx-text-fill: red;");
            }
        } else if (player2.getPriority() == 0) {
            whoRolled.setText(player2.getName() + " WINS!");
            if (player2.getColor().equals("blue")) {
                whoRolled.setStyle("-fx-text-fill: blue;");
            } else {
                whoRolled.setStyle("-fx-text-fill: red;");
            }
        } else {
            if (player1.getPriority() == 1) {
                if (player1.getFuel() <= 0 && player1.getBlock() == 0) {
                    fuel.outOfFuel(player1, fuel1);
                    fuel1.setText("Fuel meter: " + (int) player1.getFuel());
                    fuelBar1.setProgress(player1.getFuel() / 120);
                } else if (player1.getBlock() > 0) {
                    whoRolled.setText(player1.getName() + " needs to wait for " + player1.getBlock() + " more turn");
                    if (player1.getColor().equals("blue")) {
                        whoRolled.setStyle("-fx-text-fill: blue;");
                    } else {
                        whoRolled.setStyle("-fx-text-fill: red;");
                    }
                    player1.setPriority(2);
                    fuel.outOfFuel(player1, fuel1);
                    fuel1.setText("Fuel meter: " + (int) player1.getFuel());
                    fuelBar1.setProgress(player1.getFuel() / 120);
                } else {
                    movement.playerMoves(player1, whoRolled, diceImage);
                    movement.moveCar(player1);
                    fuel.checkForField(player1, fuel.getFuelArray());
                    //Turning the car left and right depending on the destination
                    carGoBrrr(player1);
                    fuel1.setText("Fuel meter: " + (int) player1.getFuel());
                    fuelBar1.setProgress(player1.getFuel() / 120);
                    player1.setPriority(2);
                }
            } else {
                if (player2.getFuel() <= 0 && player2.getBlock() == 0) {
                    fuel.outOfFuel(player2, fuel2);
                    fuel2.setText("Fuel meter: " + (int) player2.getFuel());
                    fuelBar2.setProgress(player2.getFuel() / 120);
                } else if (player2.getBlock() > 0) {
                    whoRolled.setText(player2.getName() + " needs to wait for " + player2.getBlock() + " more turn");
                    if (player2.getColor().equals("blue")) {
                        whoRolled.setStyle("-fx-text-fill: blue;");
                    } else {
                        whoRolled.setStyle("-fx-text-fill: red;");
                    }
                    player1.setPriority(1);
                    fuel.outOfFuel(player2, fuel2);
                    fuel2.setText("Fuel meter: " + (int) player2.getFuel());
                    fuelBar2.setProgress(player2.getFuel() / 120);
                } else {
                    movement.playerMoves(player2, whoRolled, diceImage);
                    movement.moveCar(player2);
                    fuel.checkForField(player2, fuel.getFuelArray());

                    //Turning the car left and right depending on the destination
                    carGoBrrr(player2);
                    fuel2.setText("Fuel meter: " + (int) player2.getFuel());
                    fuelBar2.setProgress(player2.getFuel() / 120);
                    player1.setPriority(1);
                }
            }
        }

    }
    //------------------------------------------------------------------------------------------------------------------
}