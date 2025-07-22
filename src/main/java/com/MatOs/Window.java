package com.MatOs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JPanel implements KeyListener {

    public static JFrame frame;
    public static int unit = 24;

    public static int width = 10;
    public static int height = 10;
    public static int snakeWidth = 24;
    public static int appleWidth = 24;

    String dir = "";

    public Window() {
        setPreferredSize(new Dimension(width * unit, height * unit));
        setFocusable(true);
        addKeyListener(this);
    }

    public static void run(){
        frame = new JFrame();
        frame.setSize(width * unit + 2 * unit, height * unit + 2 * unit);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setContentPane(new Window());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintTiles(g);
        paintSnake(g);
        paintApple(g);
    }

    public static void setText(String text){
        int size = 52;
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Monospaced", Font.BOLD, size));
        textArea.setForeground(Color.red);
        textArea.setSize(width * unit, size);
        frame.add(textArea);
        frame.repaint();
    }

    public void paintTiles(Graphics g){
        boolean color = true;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(color){
                    g.setColor(new Color(47, 174, 10));
                }else{
                    g.setColor(new Color(33, 129, 5));
                }
                color = !color;


                g.fillRect(unit / 2 + j * unit, unit / 2 + i * unit, unit, unit);
            }

            if(width % 2 == 0){
                color = !color;
            }
        }
        g.setColor(new Color(47, 174, 10));
        g.setColor(new Color(33, 129, 5));
    }

    public static void paintApple(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(Main.apple.getPosition()[0] * unit - appleWidth / 2, Main.apple.getPosition()[1] * unit - appleWidth / 2, appleWidth, appleWidth);
    }

    public static void paintSnake(Graphics g){
        Snake snake = Main.snake;

        for(int i = 0; i < snake.getLength(); i++){
            g.setColor(snake.getBody().get(i).getColor());
            g.fillRect(snake.getBody().get(i).getPosition()[0] * unit - snakeWidth / 2, snake.getBody().get(i).getPosition()[1] * unit - snakeWidth / 2, snakeWidth, snakeWidth);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if((e.getKeyCode() == KeyEvent.VK_UP && !dir.equals("down")) && Main.canMove){
            Main.snake.getBody().getFirst().setDirection(new int[]{0, 1});
            Main.snake.setDir(new int[]{0, 1});
            dir = "up";
            System.out.println("UP");
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN && !dir.equals("up") && Main.canMove){
            Main.snake.getBody().getFirst().setDirection(new int[]{0, -1});
            Main.snake.setDir(new int[]{0, -1});
            dir = "down";
            System.out.println("DOWN");
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT && !dir.equals("right") && Main.canMove){
            Main.snake.getBody().getFirst().setDirection(new int[]{-1, 0});
            Main.snake.setDir(new int[]{-1, 0});
            dir = "left";
            System.out.println("LEFT");
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !dir.equals("left") && Main.canMove){
            Main.snake.getBody().getFirst().setDirection(new int[]{1, 0});
            Main.snake.setDir(new int[]{1, 0});
            dir = "right";
            System.out.println("RIGHT");
        }

        Main.canMove = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
