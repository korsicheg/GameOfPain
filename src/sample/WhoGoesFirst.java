package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class WhoGoesFirst {
    //This method determines which player will roll first
    public void whoGoes(Player player1, Player player2, Label whoRolled) {
        DiceRoll die1;
        DiceRoll die2;
        Alert playerWins = new Alert(Alert.AlertType.INFORMATION);
        //This do..while loop repeats code until a valid acceptable result is found (either player rolls more than the other)
        //------------------------------------------------------------------------------------
        do {
            die1 = new DiceRoll();
            die2 = new DiceRoll();
            if (die1.getDie() > die2.getDie()) {
                playerWins.setTitle(player1.getName()+" WINS!");
                playerWins.setHeaderText("Congrats! "+ player1.getName() + ", you get to play first!");
                playerWins.setContentText(player1.getName()+" rolled "+die1.getDie()+", while "+player2.getName()+" rolled "+die2.getDie());
                playerWins.showAndWait();
                player1.setPriority(1);
                player2.setPriority(2);
                whoRolled.setText(player1.getName() + " is going first...");
                if (player1.getColor().equals("red")) {
                    whoRolled.setStyle("-fx-text-fill: red;");
                }
                else {
                    whoRolled.setStyle("-fx-text-fill: blue;");
                }
            } else if (die1.getDie() < die2.getDie()) {
                playerWins.setTitle(player2.getName()+" WINS!");
                playerWins.setHeaderText("Congrats! "+ player2.getName() + ", you get to play first!");
                playerWins.setContentText(player2.getName()+" rolled "+die2.getDie()+", while "+player1.getName()+" rolled "+die1.getDie());
                playerWins.showAndWait();
                player1.setPriority(2);
                player2.setPriority(1);
                whoRolled.setText(player2.getName() + " is going first...");
                if (player2.getColor().equals("red")) {
                    whoRolled.setStyle("-fx-text-fill: red;");
                }
                else {
                    whoRolled.setStyle("-fx-text-fill: blue;");
                }
            }
        } while (die1.getDie() == die2.getDie());
        //------------------------------------------------------------------------------------
    }
    //------------------------------------------------------------------------------------------------------------------
}
