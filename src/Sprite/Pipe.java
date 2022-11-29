/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprite;

import Controller.Render;
import java.awt.Rectangle;

/**
 *
 * @author Q
 */
public class Pipe {

    public int x=0, y=0;
    public int WIDTH = 50;
    public int HEIGHT = 400;
    public int speed = 2;
    public boolean isTop;
    
    public Pipe() {

    }

    public Pipe(int _x, boolean _isTop) {
        this.x = _x;
        this.isTop = _isTop;
        reset();
    }

    public void reset() {
        if (isTop) {
            this.y = 0;
            this.HEIGHT = (int) (Math.random() * 200) + 50;
        }
    }

    public void update() {
        this.x -= speed;
        if (this.x < 0 - WIDTH) {
            this.x = 550;
            reset();
        }
    }

    public Render getRender() {
        return new Render(this.x, this.y, false, WIDTH, HEIGHT);
    }

    public void setRender(Render render) {
        this.x = render.x;
        this.y = render.y;
        this.HEIGHT = render.height;
        this.WIDTH = render.width;
    }

    public boolean collides(Rectangle frog) {
        Rectangle box = new Rectangle(x, y, WIDTH, HEIGHT);
        return box.intersects(frog);
    }

}
