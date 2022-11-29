/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Q
 */
public class Memory {

    private List<Render> render = new ArrayList<>();;
    private int point = 0;
    private static Memory instance;

    public Memory() {
        render.add(new Render(500, 0, false));
        render.add(new Render(500, 0, false));
        render.add(new Render(750, 0, false));
        render.add(new Render(750, 0, false));
        
        // add position of frog to render
        render.add(new Render(40, 100, true));
    }
    
    public static Memory getInstance(){
        if(instance == null){
            instance = new Memory();
        }
        return instance;
    }
    
    public static Memory reset(){
         instance = new Memory();
         return instance;
    }

    public Memory(List<Render> render) {
        this.render = render;
    }

    public List<Render> getRender() {
        return render;
    }

    public void setRender(List<Render> render) {
        this.render = render;
    }
    
    public int getPoint(){
        return point;
    }
    public void setPoint(int _point){
        this.point = _point;
    }

}
