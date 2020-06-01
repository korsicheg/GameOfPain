package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends Main implements Initializable {

    //FXML initialization and connection

    @FXML
    private ComboBox<String> difficulty;
    @FXML
    private ImageView difficultyImage;
    @FXML
    private ComboBox<String> carColor;
    @FXML
    private ImageView carPlayer1;
    @FXML
    private ImageView carPlayer2;
    @FXML
    private TextField columns;
    @FXML
    private TextField rows;
    @FXML
    private TextField player1Name;
    @FXML
    private TextField player2Name;
    @FXML
    private Button playButton;
    @FXML
    public ImageView diceImage;

    //------------------------------------------------------------------------------------------------------------------

    //Variable,Image Initialization
    protected static ImageView blueCar = new ImageView();
    protected static ImageView redCar = new ImageView();
    protected static Image redCarLImage = new Image("/images/RedCarL.png");
    protected static Image blueCarLImage = new Image("/images/BlueCarL.png");
    protected static Image redCarImage = new Image("/images/RedCar.png");
    protected static Image blueCarImage = new Image("/images/BlueCar.png");
    protected static Image blueCarImage1 = new Image("/images/imageDice1.jpg");
    protected static int amountOfColumns;
    protected static int amountOfRows;
    protected static int startingPosition;
    //------------------------------------------------------------------------------------------------------------------

    //This method initializes the VBox and Gridpane objects , also it initializes all the objects inside them

    public void play() throws IOException {
        VBox mainBody = new VBox();
        GridPane game = new GridPane();
        player1.setName(player1Name.getText());
        player2.setName(player2Name.getText());
        player1.setFuel(120);
        player2.setFuel(120);
        player1.setCurrentColumn(0);
        player1.setCurrentRow(Integer.parseInt(rows.getText())-1);
        player2.setCurrentColumn(0);
        player2.setCurrentRow(Integer.parseInt(rows.getText())-1);
        player1.setBlock(0);
        player2.setBlock(0);
        player1.setPriority(3);
        player2.setPriority(3);
        startingPosition = Integer.parseInt(rows.getText())-1;
        amountOfColumns = Integer.parseInt(columns.getText())-1;
        amountOfRows = Integer.parseInt(rows.getText())-1;
        fuel.setFuelArray(new int[amountOfRows + 1][amountOfColumns + 1]);
        fuel.initializeFuelCost();
        fuel.setDifficulty(difficulty.getValue());
        fuel.initializeGreenAndBlack(game);
        Parent controls = FXMLLoader.load(getClass().getResource("ControlPanel.fxml"));
        game.setGridLinesVisible(true);
        for (int i = 0; i < Integer.parseInt(columns.getText()); i++) {
            game.getColumnConstraints().add(new ColumnConstraints(60));
        }
        for (int i = 0; i < Integer.parseInt(rows.getText()); i++) {
            game.getRowConstraints().add(new RowConstraints(60));
        }
        int rowsW = Integer.parseInt(rows.getText())*60;
        int columnsH = Integer.parseInt(columns.getText())*60;
        mainBody.getChildren().add(game);
        VBox.setMargin(game,new Insets(20, 0, 0, 0));
        controls.prefWidth(rowsW);
        controls.prefHeight(columnsH);
        mainBody.getChildren().add(controls);
        blueCar.setImage(blueCarImage);
        redCar.setImage(redCarImage);
        game.getChildren().add(blueCar);
        game.getChildren().add(redCar);
        GridPane.setColumnIndex(redCar, 0);
        GridPane.setRowIndex(redCar, Integer.parseInt(rows.getText())-1);
        GridPane.setColumnIndex(blueCar, 0);
        GridPane.setRowIndex(blueCar, Integer.parseInt(rows.getText())-1);
        GridPane.setValignment(blueCar, VPos.TOP);
        GridPane.setValignment(redCar,VPos.BOTTOM);
        mainBody.setStyle("-fx-background-color: white");
        game.setPrefSize(rowsW,columnsH);
        game.setMaxHeight(columnsH);
        game.setMaxWidth(rowsW);
        game.setStyle("-fx-background-color: gray");
        game.setAlignment(Pos.TOP_CENTER);
        mainBody.setAlignment(Pos.TOP_CENTER);
        Scene sc;
        if(rowsW<400&&columnsH<600){
            sc = new Scene(mainBody, 400, 600);
        }
        else {
            sc = new Scene(mainBody, rowsW + 100, columnsH + 250);
        }
        Stage stage = new Stage();
        stage.setTitle("Game!");
        stage.setScene(sc);
        stage.show();
    }

    //------------------------------------------------------------------------------------------------------------------

    //This method initializes the all the objects in the Welcome Box where the user enters all the variables needed to run the game

        @Override
        public void initialize (URL location, ResourceBundle resources){
            playButton.setOnAction(e -> {
                try {
                    play();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            ObservableList<String> difficultyList = FXCollections.observableArrayList("Easy", "Normal", "Hard", "Impossible");
            difficulty.setItems(difficultyList);

            //Image initialization
            Image easyImage = new Image("/images/easy.jpg");
            Image normalImage = new Image("/images/normal.jpg");
            Image hardImage = new Image("/images/hard.jpg");
            Image impossibleImage = new Image("/images/impossible.jpg");
            Image blueCar = new Image("/images/BlueCar.png");
            Image redCar = new Image("/images/RedCar.png");


            //Change image when changing difficulty
            difficulty.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                        switch (newValue) {
                            case "Easy":
                                difficultyImage.setImage(easyImage);
                                Alert alertEasy = new Alert(Alert.AlertType.INFORMATION);
                                alertEasy.setTitle("Easy mode? Really?");
                                alertEasy.setHeaderText("EASY-EASY!");
                                alertEasy.setContentText("This is WAY easy!");
                                alertEasy.show();
                                break;
                            case "Normal":
                                difficultyImage.setImage(normalImage);
                                Alert alertNormal = new Alert(Alert.AlertType.INFORMATION);
                                alertNormal.setContentText("This is normal!");
                                alertNormal.show();
                                break;
                            case "Hard":
                                difficultyImage.setImage(hardImage);
                                Alert alertHard = new Alert(Alert.AlertType.INFORMATION);
                                alertHard.setContentText("This is hard! Only experienced players!");
                                alertHard.show();
                                break;
                            case "Impossible":
                                difficultyImage.setImage(impossibleImage);
                                Alert alertImpossible = new Alert(Alert.AlertType.INFORMATION);
                                // FIXME: 21/05/2020
                                alertImpossible.setContentText("This is impossible. Like, literally impossible.");
                                alertImpossible.show();
                                break;
                        }
                    }
            );

            //Setting Images of cars based on the choice of the players, also changing the image based on that choice
            ObservableList<String> carColorList = FXCollections.observableArrayList("RED", "BLUE");
            carColor.setItems(carColorList);
            carColor.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                        switch (newValue) {
                            case "RED":
                                carPlayer1.setImage(redCar);
                                carPlayer2.setImage(blueCar);
                                player1.setColor("red");
                                player2.setColor("blue");
                                break;
                            case "BLUE":
                                carPlayer1.setImage(blueCar);
                                carPlayer2.setImage(redCar);
                                player1.setColor("blue");
                                player2.setColor("red");
                                break;
                        }
                    }
            );

        }
        //--------------------------------------------------------------------------------------------------------------
    }