package com.aoc2023.Q2;

class Game {
    private long green;
    private long blue;
    private long red;

    public long getBlue() {
        return blue;
    }

    public void setBlue(long blue) {
        this.blue = blue;
    }

    public long getGreen() {
        return green;
    }

    public void setGreen(long green) {
        this.green = green;
    }

    public long getRed() {
        return red;
    }

    public void setRed(long red) {
        this.red = red;
    }


    @Override
    public String toString() {
        return "Game{" +
                "green=" + green +
                ", blue=" + blue +
                ", red=" + red +
                '}';
    }
}