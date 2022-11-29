package gui;

import gui.FrogGame;
import Controller.Game;
import Controller.Keyboard;
import Controller.Memory;
import Controller.Render;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Q
 */
public class GamePanel extends JPanel implements Runnable {

    public int WIDTH = 500;
    public int HEIGHT = 400;
    private boolean _isPause = false;
    private boolean _isSave = false;
    Game game;
    Thread t;
    FrogGame main;

    JButton frog = new JButton("frog");
    JButton btn[] = new JButton[4];
    Memory mem = Memory.getInstance();
    Keyboard key = Keyboard.getInstance();

    public GamePanel(FrogGame f) {
        main = f;
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 1));
        game = new Game();
        add(frog);
        frog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sprite/frog.png"))); // NOI18N

        for (int i = 0; i < 4; i++) {
            btn[i] = new JButton();
            btn[i].addKeyListener(key);
            add(btn[i]);
        }

    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public void pause() {
        _isPause = true;
    }

    public synchronized void resume() {
        _isPause = false;
        notify();

    }

    public synchronized void _wait() throws InterruptedException {
        while (_isPause) {
            wait();
        }
    }

    public void save() {
        mem.setRender(game.getRenders());
        _isSave = true;
    }

    public void restart(String cmd) {
        game.restart(cmd, mem.getRender());
    }

    public void showPoint() {
        main.getjLabel1().setText("Point: " + mem.getPoint());
    }

    public void destroy() {
        if (game.die) {
            int point = mem.getPoint();
            String award = "No medal";
            if (point > 10 && point < 20) {
                award = "Bronze medal";
            } else if (point >= 20 && point < 30) {
                award = "Sliver medal";
            } else if (point >= 30) {
                award = "Gold medal";
            }
            if (_isSave) {
                Object mes[] = {"New Game", "Continue", "Exit"};
                int option = JOptionPane.showOptionDialog(this, "You got " + award + " Do you want to contiune?",
                        "Notice!",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, mes, mes[0]);
                if (option == 0) {
                    restart("new");
                    game.die = false;
                    _isSave = false;
                    showPoint();
                }
                if (option == 1) {
                    restart("continue");
                    game.die = false;
                }
                if (option == 2) {
                    System.exit(0);
                }
            } else {
                Object mes[] = {"New Game", "Exit"};
                int option = JOptionPane.showOptionDialog(this, "You got " + award + " Do you want to contiune?",
                        "Notice!",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, mes, mes[0]);
                if (option == 0) {
                    restart("new");
                    game.die = false;
                    _isSave = false;
                    showPoint();

                }
                if (option == 1) {
                    System.exit(0);
                }
            }

        }
    }

    public void addCom(JButton obj, Render r) {
        obj.setBounds(r.x, r.y, r.width, r.height);
    }

    public void update() throws Exception {
        int i = 0;
        for (Render r : game.getRenders()) {
            if (r.isFrog) {
                addCom(frog, r);
            } else {
                addCom(btn[i], r);
                i++;
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                showPoint();
                update();
                game.update();
                destroy();
                _wait();
                Thread.sleep(15);
            }
        } catch (Exception e) {
        }

    }

}
