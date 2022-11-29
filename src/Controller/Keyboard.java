/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Q
 */
public class Keyboard implements KeyListener {

    private boolean press = false;
    private boolean release = true;
    private static Keyboard instance;

    public Keyboard() {
    }
    
    public static Keyboard getInstance(){
        
        if(instance == null){
            instance =new  Keyboard();
        }
        return instance;
    }

    public boolean isPress() {
        return press;
    }
    public void setPress(boolean press){
        this.press = press;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (release) {
                release = false;
                press = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            release = true;
        }

    }
}
