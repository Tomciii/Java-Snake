package com.company;

import javax.swing.*;
import java.awt.*;

public class Snake extends JFrame {

    public Snake(){
            add(new Game());
            setResizable(false);
            pack();
            setTitle("Snake");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame snake = new Snake();
                snake.setVisible(true);
            }
        });
    }
}
