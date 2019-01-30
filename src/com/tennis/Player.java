package com.tennis;

public class Player {

    public static int LEFT_SIDE = 0, RIGHT_SIDE = 1;

    private int position;
    private String name;

    public Player(String name, int position) {
        if (position != LEFT_SIDE && position != RIGHT_SIDE) {
            throw new IllegalArgumentException("Please select valid Tennis court side");
        }
        this.name = name;
        this.position = position;

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
