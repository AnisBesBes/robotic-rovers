package com.nasa.robotic.rovers.model;

import org.apache.commons.lang3.NotImplementedException;

public class Rover {

    private int x;
    private int y;
    private String cardinal;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCardinal() {
        return cardinal;
    }

    public void setCardinal(String cardinal) {
        this.cardinal = cardinal;
    }

    public int getX() {
        return 0;
    }

    public int getY() {
        return 0;
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }
}
