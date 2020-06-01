package sample;

public class Player {
    //Attributes initialization
    //------------------------------------------------------------------------------------
    private String name;
    private String color;
    private double fuel;
    private int currentColumn;
    private int currentRow;
    private int priority;
    private int block;
    //------------------------------------------------------------------------------------


    //Constructor of the Player object class
    //------------------------------------------------------------------------------------
    public Player(String name, String color, double fuel, int currentColumn, int currentRow, int priority, int block) {
        this.name = name;
        this.color = color;
        this.fuel = fuel;
        this.currentColumn = currentColumn;
        this.currentRow = currentRow;
        this.priority = priority;
        this.block = block;
    }
    //------------------------------------------------------------------------------------

    //Empty constructor
    //------------------------------------------------------------------------------------
    public Player() {
    }
    //------------------------------------------------------------------------------------

    //Getters and Setters of Attributes
    //------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }
    //------------------------------------------------------------------------------------

}
