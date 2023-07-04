package com.nasa.robotic.rovers.model;

public class Plateau {

    private int maxX;
    private final int MIN_X = 0;
    private int maxY;
    private final int MIN_Y = 0;

    public Plateau(Integer maxX, Integer maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinX() {
        return MIN_X;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMinY() {
        return MIN_Y;
    }
}
