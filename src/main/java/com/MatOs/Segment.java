package com.MatOs;

import java.awt.*;

public class Segment {

    private int[] position;
    private Color color;

    private int[] direction;

    public Segment(Color color, int[] direction) {
        this.color = color;
        this.direction = direction;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int[] getDirection() {
        return direction;
    }

    public void setDirection(int[] direction) {
        this.direction = direction;
    }
}
