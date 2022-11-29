/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Sprite.Frog;
import Sprite.Pipe;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Q
 */
public class Game {

    private List<Pipe> pipes = new ArrayList<>();
    private List<Render> render = new ArrayList<>();
    private Frog frog;
    public boolean die = false;
    Memory mem = Memory.getInstance();
    private int point = 0;

    public Game() {
        restart("new", mem.getRender());
    }

    public void restart(String cmd, List<Render> memory) {

        if (cmd.equals("new")) {

            render = new Memory().getRender();
            point = 0;
            mem.setPoint(point);
        }
        if (cmd.equals("continue")) {
            render = memory;
        }
        pipes = new ArrayList<>();
        pipes.add(new Pipe(render.get(0).x, true));
        pipes.add(new Pipe(render.get(1).x, false));
        pipes.add(new Pipe(render.get(2).x, true));
        pipes.add(new Pipe(render.get(3).x, false));
        frog = new Frog(render.get(4).x, render.get(4).y);

        if (cmd.equals("continue") && render.get(0).height != 0) {
            pipes.get(0).HEIGHT = render.get(0).height;
            pipes.get(2).HEIGHT = render.get(2).height;
        }
    }

    public void update() {
        if (die) {
            return;
        }
        getPoint();
        checkCollides();
        frog.update();
        for (Pipe pipe : pipes) {
            pipe.update();
        }
        pipes.get(1).y = 120 + pipes.get(0).HEIGHT;
        pipes.get(3).y = 120 + pipes.get(2).HEIGHT;
    }

    public void getPoint() {
        if (pipes.get(0).x == frog.x || pipes.get(2).x == frog.x) {
            point++;
            mem.setPoint(point);
        }
    }

    public void checkCollides() {
        for (Pipe pipe : pipes) {
            if (pipe.collides(frog.getBox())) {
                die = true;
                break;
            }
        }
        if (frog.y >= 400 - frog.height) {
            die = true;
        }
    }

    public List<Render> getRenders() {
        List<Render> renders = new ArrayList<>();
        for (Pipe pipe : pipes) {
            renders.add(pipe.getRender());
        }
        renders.add(frog.getRender());
        return renders;
    }

}
