package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Movement extends ControlController {
    //This method checks if any player has won.

    public boolean winCheck(Player player, int roll){
        boolean win = false;
        if (player.getCurrentRow()==0&&player.getCurrentColumn()-roll<=0){
            win = true;
        }
        return win;
    }
    //------------------------------------------------------------------------------------------------------------------

    //This method gives the player movement ability also calculates the fuel through the use of methods in the fuel class



    public void playerMoves(Player player, Label whoRolled, ImageView diceImage) {
        DiceRoll die = new DiceRoll();
        whoRolled.setText(player.getName()+" rolled "+die.getDie()+"...");
        if (die.getDie()==1){
            Image Image = new Image("/images/imageDice1.jpg");
            diceImage.setImage(Image);
        }else if (die.getDie()==2){
            Image Image = new Image("/images/imageDice2.jpg");
            diceImage.setImage(Image);
        }else if (die.getDie()==3){
            Image Image = new Image("/images/imageDice3.jpg");
            diceImage.setImage(Image);
        }else if (die.getDie()==4){
            Image Image = new Image("/images/imageDice4.jpg");
            diceImage.setImage(Image);
        }else if (die.getDie()==5){
            Image Image = new Image("/images/imageDice5.jpg");
            diceImage.setImage(Image);
        }else{
            Image Image = new Image("/images/imageDice6.jpg");
            diceImage.setImage(Image);
        }
        if (player.getColor().equals("blue")){
            whoRolled.setStyle("-fx-text-fill: blue;");
        }
        else {
            whoRolled.setStyle("-fx-text-fill: red;");
        }
        int rest = die.getDie();
        if (winCheck(player, rest)){
            player.setCurrentColumn(0);
            player.setCurrentRow(0);
            player.setPriority(0);
        }
        else {
            System.out.println("_________________");
            while (rest > 0) {
                System.out.println(player.getPriority());
                if (startingPosition % 2 != 0) {
                    if (player.getCurrentRow() % 2 != 0) {
                        player.setFuel(fuel.goFuelRight(player, player.getFuel(), rest));
                        rest = goRight(player, rest);
                    } else {
                        player.setFuel(fuel.goFuelLeft(player, player.getFuel(), rest));
                        rest = goLeft(player, rest);
                    }
                } else {
                    if (player.getCurrentRow() % 2 == 0) {
                        player.setFuel(fuel.goFuelRight(player, player.getFuel(), rest));
                        rest = goRight(player, rest);
                    }
                    else {
                        player.setFuel(fuel.goFuelLeft(player, player.getFuel(), rest));
                        rest = goLeft(player, rest);
                    }
                }
            }
            System.out.println("_________________");
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    //This method checks if the player has stepped in a green or black tile , and rewards them , or returns them to start.
    public void checkForField(Player player, int[][] array){
        Alert youWentForIt = new Alert(Alert.AlertType.WARNING);
        if (array[player.getCurrentColumn()][player.getCurrentRow()] == 5){
            youWentForIt.setTitle(player.getName()+"! OPS!!");
            youWentForIt.setHeaderText("Not your lucky day...");
            youWentForIt.setContentText(player.getName()+" on "+player.getColor()+" car stepped on black tile! You are going to start!");
            youWentForIt.showAndWait();
            player.setCurrentRow(amountOfRows);
            player.setCurrentColumn(0);
            movement.moveCar(player);
            carGoBrrr(player);
        }
        else if (array[player.getCurrentColumn()][player.getCurrentRow()] == 6){
            youWentForIt.setTitle(player.getName()+"! NICE!");
            youWentForIt.setHeaderText("You are lucky!");
            youWentForIt.setContentText(player.getName()+" on "+player.getColor()+" car stepped on green tile! +50% fuel for ya!");
            youWentForIt.showAndWait();
            player.setFuel(player.getFuel()+(player.getFuel()*150)/100);
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    //This method gives movement towards the right
    public int goRight(Player player, int rest) {
        if (player.getCurrentColumn() == amountOfColumns){
            rest = rest - 1;
            player.setCurrentRow(player.getCurrentRow()-1);
        }
        else if (player.getCurrentColumn()+rest > amountOfColumns){
            rest = rest - (amountOfColumns - player.getCurrentColumn());
            player.setCurrentColumn(player.getCurrentColumn()+(amountOfColumns - player.getCurrentColumn()));
            rest = rest - 1;
            player.setCurrentRow(player.getCurrentRow()-1);
        }
        else if(player.getCurrentRow()==0){
            player.setCurrentColumn(player.getCurrentColumn()+rest);
            rest = 0;
        }
        else {
            player.setCurrentColumn(player.getCurrentColumn()+rest);
            rest = 0;
        }
        return rest;
    }
    //------------------------------------------------------------------------------------------------------------------

    //This method gives movent with direction towards the left
    public int goLeft(Player player, int rest) {
        if (player.getCurrentColumn() == 0 && player.getCurrentRow() != amountOfRows){
            rest = rest - 1;
            player.setCurrentRow(player.getCurrentRow()-1);
        }
        else if (player.getCurrentColumn() - rest < 0) {
            if(player.getCurrentRow()==1&&startingPosition%2==0){
                rest = rest - (player.getCurrentColumn()+1);
                player.setCurrentColumn(amountOfColumns);
                player.setCurrentRow(player.getCurrentRow()-1);
            }
            else {
                rest = rest - (player.getCurrentColumn() + 1);
                player.setCurrentColumn(0);
                player.setCurrentRow(player.getCurrentRow() - 1);
            }
        }
        else {
            player.setCurrentColumn(player.getCurrentColumn()-rest);
            rest = 0;
        }
        return rest;
    }
    //------------------------------------------------------------------------------------------------------------------

    //This method moves the car objects within the Pane
    public void moveCar(Player player) {
        if(player.getPriority()==0&&player.getColor().equals("blue")){
            GridPane.setColumnIndex(blueCar, 0);
            GridPane.setRowIndex(blueCar, 0);
        }
        else if(player.getPriority()==0&&player.getColor().equals("red")){
            GridPane.setColumnIndex(redCar, 0);
            GridPane.setRowIndex(redCar, 0);
        }
        else {
            if (player.getColor().equals("blue")) {
                GridPane.setColumnIndex(blueCar, player.getCurrentColumn());
                GridPane.setRowIndex(blueCar, player.getCurrentRow());
            } else {
                GridPane.setColumnIndex(redCar, player.getCurrentColumn());
                GridPane.setRowIndex(redCar, player.getCurrentRow());
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
