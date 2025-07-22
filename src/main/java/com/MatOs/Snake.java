package com.MatOs;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private int length = 0;
    private long speed;

    private final int[] startPos = new int[]{Window.width / 2, Window.height / 2};

    private int[] dir;

    private List<Segment> segments;

    public Snake(long speed, int[] dir) {
        segments = new ArrayList<Segment>();
        this.speed = speed;
        this.dir = dir;
    }

    public int getLength() {
        return length;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public void addSegment(Segment segment) {
        if(!segments.isEmpty()){
            segment.setPosition(new int[]{segments.getLast().getPosition()[0] - segments.getLast().getDirection()[0],
                    segments.getLast().getPosition()[1] + segments.getLast().getDirection()[1]});
            segment.setDirection(segments.getLast().getDirection());
        }else{
            segment.setPosition(startPos);
            segment.setDirection(dir);
        }

        this.segments.add(segment);
        this.length++;
    }

    public List<Segment> getBody(){
        return segments;
    }

    public int[] getDir() {
        return dir;
    }

    public void setDir(int[] dir) {
        this.dir = dir;
    }
}
