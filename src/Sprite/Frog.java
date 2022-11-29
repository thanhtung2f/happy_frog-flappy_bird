/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprite;

import Controller.Keyboard;
import Controller.Render;
import java.awt.Rectangle;
import javax.swing.JLabel;

/**
 *
 * @author Q
 */
public class Frog {

    public int x=0, y=0;
    public int width = 48;
    public int height = 48;
    public int gravity = 24;

    private Keyboard key = Keyboard.getInstance();

    public Frog() {
        this.x = 40;
        this.y = 100;
    }
    
    public Frog(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (key.isPress()) {
            y -= gravity;
            key.setPress(false);
        } else {
            y += 2;
        }
    }

    public Rectangle getBox() {
        return new Rectangle(x, y, width, height);
    }

    public Render getRender() {
        return new Render(x, y, true ,width, height);
    }

}
