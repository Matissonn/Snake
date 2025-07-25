package com.MatOs;

import java.awt.*;
import java.util.Arrays;
import javax.swing.Timer;

public class Main {
    public static Snake snake;

    public static Apple apple;
    static char[][] tiles;
    static Timer timer;
    public static boolean canMove = true;

    public static void main(String[] args) throws InterruptedException {
        tiles = new char[Window.width + 1][Window.height + 1];

        snake = new Snake(100, new int[]{0, -1});
        for(int i = 0; i < 3; i++){
            snake.addSegment(new Segment(getColor(), snake.getDir()));
        }

        Window.run();

        apple = new Apple(generateApple(), 1);

        int delay = (int) (10000 / snake.getSpeed());
        timer = new Timer(delay, e -> {
            canMove = true;
            move();
            Window.frame.repaint();

        });
        timer.start();
    }

    public static int[] generateApple(){
        int[] pos = new int[2];
        pos[0] = (int) (Math.random() * (Window.width - 1) + 1);
        pos[1] = (int) (Math.random() * (Window.height - 1) + 1);

        return pos;
    }

    public static void move(){

        for(int i = 0 ; i < Window.width ; i++){
            for(int j = 0; j < Window.height ; j++){
                tiles[i][j] = ' ';
            }
        }

        for(int i = snake.getLength() - 1; i > -1; i--){
            snake.getBody().get(i).setPosition(new int[]{snake.getBody().get(i).getPosition()[0] + snake.getBody().get(i).getDirection()[0],
                    snake.getBody().get(i).getPosition()[1] - snake.getBody().get(i).getDirection()[1]});
            if(i > 0) snake.getBody().get(i).setDirection(snake.getBody().get(i - 1).getDirection());
        }

        for(Segment s : snake.getBody()){
            int xPos = s.getPosition()[0];
            int yPos = s.getPosition()[1];

            if(xPos <= Window.width && yPos <= Window.height && xPos >= 0 && yPos >= 0){
                if(s != snake.getBody().getFirst()){
                    tiles[xPos][yPos] = 't';
                }
            }
        }

        int x = snake.getBody().getFirst().getPosition()[0];
        int y = snake.getBody().getFirst().getPosition()[1];

        if(x == 0 || y == 0 || x > Window.width || y > Window.height || tiles[x][y] == 't'){
            Window.setText("GAME OVER");
            timer.stop();
        }

        if(Arrays.equals(new int[]{x, y}, apple.getPosition())){
            snake.addSegment(new Segment(getColor(), snake.getBody().getLast().getDirection()));
            apple.setPosition(generateApple());
        }

        if(snake.getLength() == Window.height * Window.width){
            Window.setText("YOU WON");
            timer.stop();
        }
    }

    public static Color getColor(){
        return new Color(255 - 17 * (Math.min(snake.getLength(), 15)),
                17 * (snake.getLength() >= 15 ? snake.getLength() - 15 : 0),
                17 * (Math.min(snake.getLength(), 15)));
    }
}