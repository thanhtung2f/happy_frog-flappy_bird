/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Q
 */
public class Render {
    
    
    public int x, y;
    public boolean isFrog;
    public int width, height;
    
    public Render() {
    }

    public Render(int x, int y , boolean _isFrog) {
        this.x = x;
        this.y = y;
        this.isFrog = _isFrog;
    }
    
     public Render(int x, int y , boolean _isFrog, int _width, int _height) {
        this.x = x;
        this.y = y;
        this.isFrog = _isFrog;
        this.height = _height;
        this.width = _width;
    }
}
