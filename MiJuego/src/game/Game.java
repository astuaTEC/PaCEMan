package game;

import game.characters.PacMan;
import game.classes.EntityA;
import game.classes.EntityB;
import game.classes.EntityC;
import game.classes.WallEntity;
import game.elements.Life;
import game.gameControls.KeyInput;
import game.gameControls.MouseInput;
import game.graphics.BufferedImageLoader;
import game.graphics.Textures;
import game.levels.Level1;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Game controller class
 * @author Saymon Astúa Madrigal, Oscar Araya
 */

public class Game extends Canvas implements Runnable {

    // Game constants for the window
    public static final Integer WIDTH = 900;
    public static final Integer HEIGHT = 620;
    public static final Integer SCALE = 2;
    public final String TITLE = "2D GAME";
    private final Font font = new Font("arial", Font.BOLD, 25);

    private Boolean running = false;
    private Thread thread;

    // game imeges
    private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    private BufferedImage menubg = null;

    // Game audio
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

    // Constants to handle the game
    private Integer enemy_cont = 1;
    private Integer enemy_killed = 0;
    public Long flashTimer;
    public Long deathDelay;
    public Boolean isFlahing = false;
    public Boolean isDeath = false;
    public Integer lives = 3;

    // Some elements in the game
    public PacMan p;
    private Controller c;
    private Textures textures;
    private Menu menu;
    private Level1 level1;

    // Linked List of graphics elements
    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
    public LinkedList<EntityC> ec;
    public LinkedList<WallEntity> we;
    public LinkedList<Life> graphicLives = new LinkedList<>();

    // State of program
    public enum STATE{
        MENU,
        GAME
    };

    public static STATE State = STATE.MENU;

    /**
     * Initialize game elements
     */
    public void init(){
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            spriteSheet = loader.loadImage("/imgs/pmsheet2.0.png");
            background = loader.loadImage("/imgs/lvl1.png");
            menubg = loader.loadImage("/imgs/pac-man2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        textures = new Textures(this);
        menu = new Menu();
        initLevel1();

        intro.loop();

        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());

        //c.createEnemy(enemy_cont);
    }

    /**
     * Initialize the thread and the game
     */
    private synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stops the thread and the game
     */
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

    /**
     * This method controls the game cycle
     * @ 60 fps
     */
    @Override
    public void run() {
        init();
        Long lastTime = System.nanoTime();
        final Double amountOfTicks = 60.0;
        Double ns = 1000000000 / amountOfTicks;
        Double delta = 0.0;
        Integer updates = 0;
        Integer frames = 0;
        Long timer = System.currentTimeMillis();

        while (running){
            Long now = System.nanoTime();
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
            if(isFlahing) {
                if (System.currentTimeMillis() - flashTimer > 8000) {
                    isFlahing = false;
                    ghostFlashOff();
                }
            }
            if(isDeath){
                if (System.currentTimeMillis() - deathDelay > 1500) {
                    isDeath = false;
                    initLevel1();
                }
            }

        }
        stop();
    }

    /**
     * Update the graphic movements of the game
     */
    private void tick(){
        if(State == STATE.GAME){
            p.tick();
            c.tick();
        }

    }

    /**
     * Update game graphics
     */
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
            g.drawString(String.valueOf(p.getPoints()), 795, 253);
            level1.render(g);
            p.render(g);
            c.render(g);
            for (Life life : graphicLives) life.render(g);
        }
        else if(State == STATE.MENU){
            g.drawImage(menubg, 0, 0, null);
            menu.render(g);
        }

        ///////////////////////////////
        g.dispose();
        bs.show();
    }

    /**
     * Control the keyboard methods, specifically when a key is pressed
     * @param e KeyEvent
     */
    public void keyPressed(KeyEvent e) {
        Integer key = e.getExtendedKeyCode();

        if(State == STATE.GAME && !p.isDeath()) {
            if (key == KeyEvent.VK_RIGHT) {
                p.setVelX(3);
                p.setRight(true);
                p.setLeft(false);
                p.setUp(false);
                p.setDown(false);
            } else if (key == KeyEvent.VK_LEFT) {
                p.setVelX(-3);
                p.setRight(false);
                p.setLeft(true);
                p.setUp(false);
                p.setDown(false);
            } else if (key == KeyEvent.VK_UP) {
                p.setVelY(-3);
                p.setRight(false);
                p.setLeft(false);
                p.setUp(true);
                p.setDown(false);
            } else if (key == KeyEvent.VK_DOWN) {
                p.setVelY(3);
                p.setRight(false);
                p.setLeft(false);
                p.setUp(false);
                p.setDown(true);
            }
        }
    }

    /**
     * Control the keyboard methods, specifically when a key is released
     * @param e KeyEvent
     */
    public void keyReleased(KeyEvent e) {
        Integer key = e.getExtendedKeyCode();

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

    /**
     * The principal method of the game
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        game.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

    }

    public void ghostFlashOn(){
        //intermission.loop();
        for (EntityB tempEnt2 : eb) {
            tempEnt2.setFlash(true);
        }
    }
    public void ghostFlashOff(){
        intermission.stop();
        for (EntityB tempEnt2 : eb) {
            tempEnt2.setFlash(false);
        }
    }

    /**
     * This method initialize level 1
     */
    public void initLevel1(){
        if(lives > 0) {
            lives--;
            graphicLives.clear();
        }
        else {
            System.out.println("Without lifes");
            Game.State = STATE.MENU;
            lives = 2;
            intro.loop();
        }

        c = new Controller(textures);
        p = new PacMan(280, 460, textures, this, c);
        level1 = new Level1(c, textures, this);

        ea = c.getEntityA();
        eb = c.getEntityB();
        ec = c.getEntityC();
        we = c.getWallEntity();

        c.createEnemy(enemy_cont);
    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }

    public Integer getEnemy_cont() {
        return enemy_cont;
    }

    public void setEnemy_cont(Integer enemy_cont) {
        this.enemy_cont = enemy_cont;
    }

    public Integer getEnemy_killed() {
        return enemy_killed;
    }

    public void setEnemy_killed(Integer enemy_killed) {
        this.enemy_killed = enemy_killed;
    }

}