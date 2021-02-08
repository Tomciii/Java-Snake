package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        int key_id = e.getKeyCode();

        if(key_id == KeyEvent.VK_A){
            Game.direction=0;
        }
        if(key_id == KeyEvent.VK_D){
            Game.direction=1;
        }
        if(key_id == KeyEvent.VK_W){
            Game.direction=2;
        }
        if(key_id == KeyEvent.VK_S){
            Game.direction=3;
        }
    }

}
