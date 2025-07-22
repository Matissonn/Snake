package com.MatOs;

public class Apple {

    private int[] position;
    private int buff;

    public Apple(int[] position, int buff){
        this.position = position;
        this.buff = buff;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getBuff() {
        return buff;
    }

    public void setBuff(int buff) {
        this.buff = buff;
    }
}
