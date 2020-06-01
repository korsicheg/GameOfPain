package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

//Movement class (each time a player moves class is called

public class Fuel extends Movement {

    //Initialization of Variables, Tables,Objects

    private String difficulty;
    private int[][] fuelArray;
    private int toStartClicked;
    private Stage stageAlert = new Stage();
    //------------------------------------------------------------------------------------------------------------------


    //Methods that Set and Get previously initialized Variables

    public int[][] getFuelArray() {
        return fuelArray;
    }

    public void setFuelArray(int[][] fuelArray) {
        this.fuelArray = fuelArray;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Fuel() {
    }
    //------------------------------------------------------------------------------------------------------------------

    //Initializing the fuel cost (1..3)of each grey tile using a random number

    public void initializeFuelCost() {
        for (int columns = 0; columns < fuel.getFuelArray().length; columns++) {
            for (int rows = 0; rows < fuel.getFuelArray()[columns].length; rows++) {
                Random ran = new Random();
                fuelArray[columns][rows] = (ran.nextInt(3) + 1);
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    //Initializing the amount of Green and Black tiles on the Game Board

    public void initializeGreenAndBlack(GridPane gridPane) {
        int amountOfBlack;
        int amountOfGreen;
        int column = 0;
        int row = 0;
        int obstaclesAmount = ObstacleAmount();
        if (obstaclesAmount < 2) {
            amountOfBlack = 0;
            amountOfGreen = 1;
        } else {
            amountOfBlack = obstaclesAmount / 2;
            amountOfGreen = obstaclesAmount / 2;
        }
        switch (fuel.getDifficulty()) {
            case "Easy":
                for (int i = 0; i <= amountOfBlack / 3; i++) {
                    i = blackPositioning(column, row, gridPane, i);
                }
                for (int i = 0; i <= amountOfGreen; i++) {
                    i = greenPositioning(column, row, gridPane, i);
                }
                break;
            case "Normal":
                for (int i = 0; i <= amountOfBlack / 2; i++) {
                    i = blackPositioning(column, row, gridPane, i);
                }
                for (int i = 0; i <= amountOfGreen / 2; i++) {
                    i = greenPositioning(column, row, gridPane, i);
                }
                break;
            case "Hard":
                for (int i = 0; i <= amountOfBlack; i++) {
                    i = blackPositioning(column, row, gridPane, i);
                }
                for (int i = 0; i <= amountOfGreen / 3; i++) {
                    i = greenPositioning(column, row, gridPane, i);
                }
                break;
            case "Impossible":
                for (int columns = 0; columns < fuel.getFuelArray().length; columns++) {
                    for (int rows = 0; rows < fuel.getFuelArray()[columns].length; rows++) {
                        if (!(rows == 0 && columns == 0)&&!(rows == 9 && columns == 0)) {
                            fuel.getFuelArray()[columns][rows] = 5;
                            Pane black = new Pane();
                            black.setStyle("-fx-background-color: black;");
                            gridPane.add(black, columns, rows);
                        }
                    }
                }
                break;
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    //

    public double goFuelRight(Player player, double fuelValue, int rest) {
        if(fuel.getFuelArray()[player.getCurrentColumn()][player.getCurrentRow()] != 5) {
            if (player.getCurrentColumn() == amountOfColumns) {
                fuelValue = fuelValue - fuel.getFuelArray()[player.getCurrentRow()][player.getCurrentColumn()];
            } else if (player.getCurrentColumn() + rest > amountOfColumns) {
                rest = rest - (amountOfColumns - player.getCurrentColumn());
                for (int y = player.getCurrentColumn(); y < getFuelArray()[player.getCurrentRow()].length && y <= rest; y++) {
                    rest = rest - 1;
                    fuelValue = fuelValue + fuel.getFuelArray()[player.getCurrentRow()][y];
                    System.out.println("Currently in: [" + fuel.getFuelArray()[player.getCurrentRow()][y] + "]");
                }
            } else {
                for (int y = player.getCurrentColumn(); y < getFuelArray()[player.getCurrentRow()].length && rest != 0; y++) {
                    fuelValue = fuelValue - fuel.getFuelArray()[player.getCurrentRow()][y];
                    System.out.println("Currently in: [" + fuel.getFuelArray()[player.getCurrentRow()][y] + "]");
                    rest = rest - 1;
                    System.out.println("rest is " + rest);
                }
            }
        }
        return fuelValue;
    }

    public double goFuelLeft(Player player, double fuelValue, int rest) {
        if (player.getCurrentColumn() == 0 && player.getCurrentRow() != amountOfRows){
                fuelValue = fuelValue - fuel.getFuelArray()[player.getCurrentRow()][player.getCurrentColumn()];
            }
        else if (player.getCurrentColumn() - rest < 0||(player.getCurrentRow()==1&&startingPosition%2==0)) {
                for (int y = player.getCurrentColumn(); y >= 0 && rest!=0; y--) {
                    rest = rest - 1;
                    fuelValue = fuelValue - fuel.getFuelArray()[player.getCurrentRow()][y];
                    System.out.println("Currently in: [" + fuel.getFuelArray()[player.getCurrentRow()][y] + "]");
                }
            } else {
                for (int y = player.getCurrentColumn(); y >= 0 && rest!=0; y--) {
                    fuelValue = fuelValue - fuel.getFuelArray()[player.getCurrentRow()][y];
                    System.out.println("Currently in: [" + fuel.getFuelArray()[player.getCurrentRow()][y] + "]");
                    rest = rest - 1;
                }
            }
        return fuelValue;
    }

    public void alertFuelWarning(Player player, Stage stage) {
        List<Integer> choices = new ArrayList<>();
        choices.add(1);
        choices.add(2);
        choices.add(3);
        choices.add(4);
        choices.add(5);
        choices.add(6);
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1, choices);
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your letter:");
        Optional<Integer> result2 = dialog.showAndWait();
        result2.ifPresent(integer -> player.setBlock(player.getBlock() + integer));
        if(result2.isPresent()){
            stage.close();
        }
    }


    public void outOfFuel (Player player, Label fuelPlayer) {
        toStartClicked=0;
        if (stageAlert.isShowing()) {
            stageAlert.toFront();
        }
        if (player.getFuel() <= 0 && player.getBlock() == 0) {
            VBox alert = new VBox();
            alert.setPadding(new Insets(10));
            alert.setSpacing(10);

            Button wait = new Button("Wait");
            Button toStart = new Button("Go to start!");
            Text text = new Text("Wow! You are out of fuel, " + player.getName() + ". What would you do? You can choose 'Wait' and have +20 fuel per turn (maximum 6 turns) or choose 'toStart' and travel to start with random fuel (1-120)");
            alert.getChildren().addAll(text, wait, toStart);
            alert.setAlignment(Pos.CENTER);
            Scene sceneAlert = new Scene(alert, 200, 200);
            text.wrappingWidthProperty().bind(sceneAlert.widthProperty().subtract(15));
            stageAlert.setTitle("Out of fuel!");
            stageAlert.setScene(sceneAlert);
            wait.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    alertFuelWarning(player,stageAlert);
                }
            });

            toStart.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Random fuel = new Random();
                    player.setFuel(fuel.nextInt(120)+1);
                    player.setCurrentColumn(0);
                    player.setCurrentRow(amountOfRows);
                    movement.moveCar(player);
                    carGoBrrr(player);
                    toStartClicked=1;
                    fuelPlayer.setText("Fuel meter: " + (int) player.getFuel());
                    player.setPriority(2);
                    stageAlert.close();
                }
            });

            stageAlert.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    if (player.getBlock()==0&&toStartClicked==0){
                        Alert alertClose = new Alert(Alert.AlertType.WARNING);
                        alertClose.setTitle("NOT SO FAST");
                        alertClose.setContentText("You will not leave unless you would choose something");
                        alertClose.setHeaderText("You really thought that would work?");
                        alertClose.showAndWait();
                        e.consume();
                    }
                }
            });
            stageAlert.show();
        }
        else {
            player.setFuel(player.getFuel()+20);
            player.setBlock(player.getBlock()-1);
        }
    }

    public int blackPositioning (int column, int row, GridPane gridPane, int i){
        do{
            column = columnRandomizer();
            row = rowRandomizer();
        }while ((column==0&&row==amountOfRows)||(row==0&&column==amountOfColumns)||(column==0&&row==0)||(column==amountOfColumns&&row==amountOfRows));
        if(notTooClose(column,row)){i = i - 1;}
        else{
            getFuelArray()[column][row] = 5;
            System.out.println("Black set at "+column+" column and "+row+" row");
            Pane black = new Pane();
            black.setStyle("-fx-background-color: black;");
            gridPane.add(black,column,row);
        }
        return i;
    }

        public int greenPositioning (int column, int row, GridPane gridPane, int i){
        do{
            column = columnRandomizer();
            row = rowRandomizer();
        }while ((column==0&&row==amountOfRows)||(row==0&&column==amountOfColumns)||(column==0&&row==0)||(column==amountOfColumns&&row==amountOfRows));
        if ((fuel.getFuelArray()[column][row])==5||notTooClose(column,row)) {
            i = i - 1;
        }
        else{
            getFuelArray()[column][row] = 6;
            System.out.println("Green set at "+column+" column and "+row+" row");
            Pane green = new Pane();
            green.setStyle("-fx-background-color: darkgreen;");
            gridPane.add(green,column,row);
        }
        return i;
    }

    public boolean notTooClose(int columns, int rows){
        boolean close=false;
        for (int x = columns; x < columns-(amountOfColumns/5); x++) {
            for (int y = rows; y < rows-(amountOfRows/5); y++) {
                try {
                    if (fuel.getFuelArray()[x][y]==5||fuel.getFuelArray()[x][y]==6){
                        close = true;
                    }
                }catch (Exception ignored){}
            }
        }
        for (int x = columns; x < columns+(amountOfColumns/5); x++) {
            for (int y = rows; y < rows+(amountOfRows/5); y++) {
                try {
                    if (fuel.getFuelArray()[x][y]==5||fuel.getFuelArray()[x][y]==6){
                        close = true;
                    }
                }catch (Exception ignored){}
            }
        }
        return close;
    }

    public int columnRandomizer(){
        Random columnR = new Random();
        return columnR.nextInt(amountOfColumns + 1);
    }

    public int rowRandomizer() {
        Random rowR = new Random();
        return rowR.nextInt(amountOfRows + 1);
    }


    public int ObstacleAmount() {
    int amount;
        int value1 = (int) (Math.log10(amountOfColumns) + 1);
        int value2 = (int) (Math.log10(amountOfRows) + 1);
        if (value1 > value2){
            amount = factorial(value2)*amountOfRows;
        }
        else {
            amount = factorial(value1)*amountOfColumns;
        }
        return amount;
    }

    public int factorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
}