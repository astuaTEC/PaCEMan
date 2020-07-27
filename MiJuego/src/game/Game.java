package game;

import game.characters.PacMan;
import game.classes.EntityA;
import game.classes.EntityB;
import game.classes.EntityC;
import game.gameControls.KeyInput;
import game.gameControls.MouseInput;
import game.graphics.BufferedImageLoader;
import game.graphics.Textures;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12*9;
    public static final int SCALE = 2;
    public final String TITLE = "2D GAME";
    private final Font font = new Font("arial", Font.BOLD, 25);

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    private BufferedImage menubg = null;

    public final AudioClip eatFruit = Applet.newAudioClip(getClass().
            getResource("/audio/pacman_eatfruit.wav"));
    public final AudioClip death = Applet.newAudioClip(getClass().
            getResource("/audio/pacman_death.wav"));
    public final AudioClip intermission = Applet.newAudioClip(getClass().
            getResource("/audio/pacman_intermission.wav"));
    public final AudioClip munch = Applet.newAudioClip(getClass().
            getResource("/audio/pacman_chomp.wav"));
    public final AudioClip eatGhost = Applet.newAudioClip(getClass().
            getResource("/audio/pacman_eatghost.wav"));
    private final AudioClip intro = Applet.newAudioClip(getClass().
            getResource("/audio/pacman_intro.wav"));

    private int enemy_cont = 1;
    private int enemy_killed = 0;

    private PacMan p;
    private Controller c;
    private Textures textures;
    private Menu menu;

    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
    public LinkedList<EntityC> ec;

    public static enum STATE{
        MENU,
        GAME
    };

    public static STATE State = STATE.MENU;

    public void init(){
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            spriteSheet = loader.loadImage("/imgs/pmsheet.png");
            background = loader.loadImage("/imgs/bg2.png");
            menubg = loader.loadImage("/imgs/pac-man2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        textures = new Textures(this);
        c = new Controller(textures);
        p = new PacMan(200, 200, textures, this, c);
        menu = new Menu();

        ea = c.getEntityA();
        eb = c.getEntityB();
        ec = c.getEntityC();

        intro.loop();


        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());

        c.createEnemy(enemy_cont);
    }

    private synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 5000){
                timer += 5000;
                //System.out.println("5 seconds");
                //System.out.println(updates + " Ticks, FPS " + frames);
                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    private void tick(){
        if(State == STATE.GAME){
            p.tick();
            c.tick();
        }

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        ////////////////////////////////
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        if(State == STATE.GAME){
            intro.stop();
            g.drawImage(background, 0, 0, null);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("PTS: " + p.getPoints(), 500, 450);
            p.render(g);
            c.render(g);
        }
        else if(State == STATE.MENU){
            g.drawImage(menubg, 0, 0, null);
            menu.render(g);
        }

        ///////////////////////////////
        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getExtendedKeyCode();

        if(State == STATE.GAME) {
            if (key == KeyEvent.VK_RIGHT) {
                p.setVelX(5);
                p.setRight(true);
                p.setLeft(false);
                p.setUp(false);
                p.setDown(false);
            } else if (key == KeyEvent.VK_LEFT) {
                p.setVelX(-5);
                p.setRight(false);
                p.setLeft(true);
                p.setUp(false);
                p.setDown(false);
            } else if (key == KeyEvent.VK_UP) {
                p.setVelY(-5);
                p.setRight(false);
                p.setLeft(false);
                p.setUp(true);
                p.setDown(false);
            } else if (key == KeyEvent.VK_DOWN) {
                p.setVelY(5);
                p.setRight(false);
                p.setLeft(false);
                p.setUp(false);
                p.setDown(true);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getExtendedKeyCode();

        if(key == KeyEvent.VK_RIGHT){
            p.setVelX(0);
        }
        else if(key == KeyEvent.VK_LEFT){
            p.setVelX(0);
        }
        else if(key == KeyEvent.VK_UP){
            p.setVelY(0);
        }
        else if(key == KeyEvent.VK_DOWN){
            p.setVelY(0);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }

    public int getEnemy_cont() {
        return enemy_cont;
    }

    public void setEnemy_cont(int enemy_cont) {
        this.enemy_cont = enemy_cont;
    }

    public int getEnemy_killed() {
        return enemy_killed;
    }

    public void setEnemy_killed(int enemy_killed) {
        this.enemy_killed = enemy_killed;
    }
}