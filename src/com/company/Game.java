package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {
    private int width = 800;
    private int height = 600;

    private Image apple;
    private Image head;
    private Image tail;

    private int tail_amount = 3;
    private int snake_width = 10;
    private int snake_x[] = new int[width * height / (snake_width * snake_width)];
    private int snake_y[] = new int[width * height / (snake_width * snake_width)];

    private int apple_x;
    private int apple_y;
    boolean running;
    private Timer t;

    public static int direction;

    public Game() {
        addKeyListener(new SnakeListener());
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.LIGHT_GRAY);

        ImageIcon icon_apple = new ImageIcon("Apple.png");
        ImageIcon icon_head = new ImageIcon("Head.png");
        ImageIcon icon_tail = new ImageIcon("Dot.png");


        apple = icon_apple.getImage();
        head = icon_head.getImage();
        tail = icon_tail.getImage();


        for (int i = 0; i < tail_amount; i++) {
            snake_x[i] = 100 - i * 10;
            snake_y[i] = 50;
        }

        running = true;
        spawn_apple();
        t = new Timer(200,this);
        t.start();
    }

    private void spawn_apple() {
        int random = (int) (Math.random() * 29);
        apple_x = random * snake_width;
        random = (int) (Math.random() * 29);
        apple_y = random * snake_width;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            check_apple();
         check_death();
            move_snake();
        }
        repaint();
    }

    private void move_snake() {
        for (int i = tail_amount; i > 0; i--) {
            snake_x[i] = snake_x[i - 1];
            snake_y[i] = snake_y[i - 1];
        }
        switch (direction) {
            case 0:
                snake_x[0] -= snake_width;
                break;
            case 1:
                snake_x[0] += snake_width;
                break;
            case 2:
                snake_y[0] -= snake_width;
                break;
            case 3:
                snake_y[0] += snake_width;
                break;
            default:
                break;
        }
    }


    private void check_death() {
        for (int i = tail_amount; i > 3; i--) {
            if (snake_x[0] == snake_x[i] && snake_y[0] == snake_y[i]) {
                running = false;
            }
        }
        if (snake_y[0] >= height || snake_x[0] >= width || snake_y[0] < 0 || snake_x[0] < 0) {
            running = false;
        }
        if(!running){
            t.stop();
        }
    }


    private void check_apple() {
        if (snake_x[0] == apple_x && snake_y[0] == apple_y) {
            tail_amount++;
            spawn_apple();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(running){
            g.drawImage(apple, apple_x,apple_y, this);
            for(int i = 1; i < tail_amount; i++){
                g.drawImage(tail, snake_x[i], snake_y[i], this);
            }
            g.drawImage(head, snake_x[0], snake_y[0], this);

            Toolkit.getDefaultToolkit().sync();
        }
        else{
            Font font = new Font("Calibri", Font.BOLD, 16);
            FontMetrics metrics = getFontMetrics(font);

            g.setColor(Color.BLACK);

            g.setFont(font);
            g.drawString("Game Over - You Died", (width/2), height/2);
        }
    }
}