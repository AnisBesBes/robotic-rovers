package com.nasa.robotic.rovers.model;

public class Rover {

    private int x;
    private int y;
    private char cardinal;

    public Rover(Integer x, Integer y, char cardinal) {
        this.x = x;
        this.y = y;
        this.cardinal = cardinal;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getCardinal() {
        return cardinal;
    }

    public void setCardinal(char cardinal) {
        this.cardinal = cardinal;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.join(" ", String.valueOf(x), String.valueOf(y), String.valueOf(cardinal));
    }
}
