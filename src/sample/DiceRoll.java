package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class DiceRoll {
    // Number showing on the first diImage oneImage =e..
    private int die;
    private ImageView diceImage;
    //------------------------------------------------------------------------------------------------------------------

    // Constructor.  Rolls the dice, so that they initially
    // show some random values.
    public DiceRoll() {

        roll();  // Call the roll() method to roll the dice.
    }
    //------------------------------------------------------------------------------------------------------------------

    // Roll the dice by setting each of the dice to be
    // a random number between 1 and 6.
    public void roll() {

        die = (int)(Math.random()*6) + 1;
    }
    //------------------------------------------------------------------------------------------------------------------

    // Return the number showing on the first die.
    public int getDie() {

        return die;
    }
    //------------------------------------------------------------------------------------------------------------------

}
