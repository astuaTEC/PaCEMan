package game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import game.characters.PacMan;
import game.classes.EntityA;
import game.classes.EntityB;
import game.classes.EntityC;
import game.classes.WallEntity;
import game.connection.Cliente;
import game.elements.Life;
import game.gameControls.KeyInput;
import game.gameControls.MouseInput;
import game.graphics.BufferedImageLoader;
import game.graphics.Textures;
import game.levels.Map_1;

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
    public Integer pacDotPoints = 0;
    private Double velLevel_1 = 1.0;
    private Double velLevel_2 = 1.5;
    private Double velLevel_3 = 2.0;
    public Integer levelNum = 1;

    // Some elements in the game
    public PacMan p;
    private Controller c;
    private Textures textures;
    private Menu menu;
    private Map_1 map1;
    public Cliente client = new Cliente(this);

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
        client.ejecutarConexion("localhost", Integer.parseInt("5050"));
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
     * @ 50 fps
     */
    @Override
    public void run() {
        init();
        Long lastTime = System.nanoTime();
        final Double amountOfTicks = 50.0;
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
                    if(levelNum == 1) {
                        pacDotPoints = 0;
                        initLevel1();
                    }
                    else if(levelNum == 2){
                        pacDotPoints = 0;
                        initLevel2();
                    }
                    else if(levelNum == 3){
                        pacDotPoints = 0;
                        initLevel3();
                    }
                    else{
                        Game.State = STATE.MENU;
                        lives = 3;
                        levelNum = 1;
                        initLevel1();
                    }

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
            g.drawString("Level: " + levelNum, 780, 400);
            //map1.render(g);
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
        map1 = new Map_1(c, textures, this);
        c.setGhostVel(velLevel_1);

        ea = c.getEntityA();
        eb = c.getEntityB();
        ec = c.getEntityC();
        we = c.getWallEntity();

    }

    /**
     * This method initialize level 2
     */
    public void initLevel2(){
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
        map1 = new Map_1(c, textures, this);
        c.setGhostVel(velLevel_2);

        ea = c.getEntityA();
        eb = c.getEntityB();
        ec = c.getEntityC();
        we = c.getWallEntity();

    }

    /**
     * This method initialize level 3
     */
    public void initLevel3(){
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
        map1 = new Map_1(c, textures, this);
        c.setGhostVel(velLevel_3);

        ea = c.getEntityA();
        eb = c.getEntityB();
        ec = c.getEntityC();
        we = c.getWallEntity();

    }

    public void selectLevel(){
        if(levelNum == 1) {
            lives = 3;
            initLevel1();
        }
        else if(levelNum == 2){
            //initLevel2
            lives = 3;
            initLevel2();
        }
        else if(levelNum == 3){
            //initLevel3
            lives = 3;
            initLevel3();
        }
        else{
            Game.State = STATE.MENU;
            lives = 3;
            levelNum = 1;
            initLevel1();
        }
    }

    public void readMessage(String message){
        System.out.println("Interpretando:...");

        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(message);

        int id = object.get("id").getAsInt();
        System.out.println(id);
        int x = object.get("xPos").getAsInt();
        System.out.println(x);
        int y = object.get("yPos").getAsInt();
        System.out.println(y);

    }

    public void createMessage(int id){

        /*JsonObject object = new JsonObject();
        object.addProperty("id", id);
        object.addProperty("xPos", x);
        object.addProperty("yPos", y);
        String json = object.toString();*/

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