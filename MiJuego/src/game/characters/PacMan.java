package game.characters;

import game.*;
import game.classes.EntityA;
import game.classes.EntityB;
import game.classes.EntityC;
import game.classes.WallEntity;
import game.elements.Pill;
import game.fruits.*;
import game.graphics.Physics;
import game.graphics.Textures;
import game.libs.Animation;

import java.awt.*;
import java.util.LinkedList;

/**
 * PacMan Character class
 * @author Saymon Astúa, Oscar Araya
 */

public class PacMan implements EntityA {

    // attributes
    private double velX = 0;
    private double velY = 0;

    private double x, y;
    private int lifes;
    public int points;

    private Textures textures;
    private Game game;
    private Controller c;
    private boolean up, down, right, left, isDeath;

    private LinkedList<Point> specificPoints;

    // Animations
    Animation upAnimation, downAnimation, leftAnimation, rightAnimation, deathAnimation;


    public PacMan(double x, double y, Textures textures, Game game, Controller c){
        this.x = x;
        this.y = y;
        this.textures = textures;
        this.game = game;
        this.c = c;
        this.lifes = 3;
        this.specificPoints = c.getSpecificPoints();

        c.setPacManPos(new Point((int)(x/20), (int)(y/20)));

        up = false;
        right = true;
        down = false;
        left = false;

        isDeath = false;

        upAnimation = new Animation(10, textures.pacman[6], textures.pacman[7]);

        rightAnimation = new Animation(10, textures.pacman[0], textures.pacman[1]);

        downAnimation = new Animation(10, textures.pacman[2], textures.pacman[3]);

        leftAnimation = new Animation(10, textures.pacman[4], textures.pacman[5]);

        deathAnimation = new Animation(10,
                textures.deathPacman[0], textures.deathPacman[1],
                textures.deathPacman[2], textures.deathPacman[3],
                textures.deathPacman[4], textures.deathPacman[5],
                textures.deathPacman[6], textures.deathPacman[7]);
    }
    /**
     * Update the graphic movements of PacMan
     */
    public void tick(){

        updatePos();
        updateMovement();

        x += velX;
        y += velY;

        if(x <= 20) {
            //right warp
            if (y >= 280 && y < 300)
                x = 530;
            else
                x = 20;
        }
        if (x >= 540) {
            //left warp
            if(y >= 280 && y < 300) {
                x = 20;
            }
            else
                x = 540;
        }

        wallCollision();

        ghostCollision();

        entityCCollision();

        verifyDeath();
    }

    /**
     * Update PacMan graphics
     * @param g Graphics to draw on the screen
     */
    public void render(Graphics g){
        if(!isDeath){
            if (up)
                upAnimation.drawAnimation(g, x, y, 0);
            if (down)
                downAnimation.drawAnimation(g, x, y, 0);
            if (left)
                leftAnimation.drawAnimation(g, x, y, 0);
            if (right)
                rightAnimation.drawAnimation(g, x, y, 0);
        }
        else {
            deathAnimation.drawAnimation(g, x, y, 0);
        }
    }

    public Point getPos(){
        Point point = new Point();
        point.setLocation((int)((x+10)/20), (int)((y+10)/20));
        return point;

    }

    /**
     * Graphic limits of pacman
     * @return Ractangle: means the limit of PacMan character
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 20, 20);
    }

    public void updatePos(){
        Point sp = specificPos();
        if(sp != null){
            c.setPacManPos(sp);
        }

    }

    public Point specificPos(){
        Point pos = getPos();
        for(Point p : specificPoints) {
            if (pos.equals(p)) {

                return p;
            }
        }
        return null;
    }
    void updateMovement(){
        c.left = left;
        c.right = right;
        c.up = up;
        c.down = down;
    }

    private void wallCollision(){
        // Collisions with walls
        for(int i = 0; i < game.we.size(); i++){
            WallEntity tempEnt = game.we.get(i);
            if(Physics.Collision(this, tempEnt)){
                double tempX = tempEnt.getX();
                double tempY = tempEnt.getY();
                if (tempX > x) {
                    x = tempX - 20;
                }
                if (tempX < x) {
                    x = tempX + 20;
                }
                if (tempY > y) {
                    y = tempY - 20;
                }
                if (tempY < y) {
                    y = tempY + 20;
                }
            }
        }
    }

    private void ghostCollision(){
        //Collisions with Ghosts
        for(int i = 0; i < game.eb.size(); i++){
            EntityB tempEnt = game.eb.get(i);
            if(Physics.Collision(this, tempEnt)){
                System.out.println("Collision");
                if(!tempEnt.isFlash()) {
                    //game.death.play();
                    isDeath = true;
                    c.removeEntity(tempEnt);
                    game.isDeath = true;
                    game.deathDelay = System.currentTimeMillis();
                }
                else{
                    //game.eatGhost.play();
                    sendMessage(tempEnt);
                    c.removeEntity(tempEnt);
                }
                /*game.setEnemy_cont(game.getEnemy_cont() + 1);
                game.setEnemy_killed(game.getEnemy_killed() + 1);*/
            }
        }
    }
    private void entityCCollision(){

        // Collisions with Fruits or PacDots
        for(int i = 0; i < game.ec.size(); i++){
            EntityC tempEnt = game.ec.get(i);
            if(Physics.Collision(this, tempEnt)){
                this.points += tempEnt.getValue();
                if(tempEnt.getClass().equals(Banana.class) || tempEnt.getClass().equals(Pineapple.class) ||
                        tempEnt.getClass().equals(Cherry.class) || tempEnt.getClass().equals(Strawberry.class) ||
                        tempEnt.getClass().equals(Apple.class) || tempEnt.getClass().equals(Orange.class) ){
                    //game.eatFruit.play();
                }
                else if(tempEnt.getClass().equals(Pill.class)){
                    System.out.println("Pill");
                    //getPos();
                    game.ghostFlashOn();
                    game.flashTimer = System.currentTimeMillis();
                    game.isFlahing = true;
                }
                else {
                    game.pacDotPoints -= 10;
                    if (game.pacDotPoints == 0){
                        game.pacDotPoints = 0;
                        System.out.println("no pacdots");
                        game.levelNum++;
                        game.selectLevel();
                    }
                    //game.munch.play();
                }

                c.removeEntity(tempEnt);
            }
        }
    }

    private void verifyDeath(){
        if(!isDeath) {
            if (up)
                upAnimation.runAnimation();
            if (down)
                downAnimation.runAnimation();
            if (left)
                leftAnimation.runAnimation();
            if (right)
                rightAnimation.runAnimation();
        }
        else{
            deathAnimation.runAnimation();
        }
    }

    public void sendMessage(EntityB entityB){
        if(entityB.getClass().equals(Shadow.class)){
            game.client.enviar("n1");
        }
        else if(entityB.getClass().equals(Speedy.class)){
            game.client.enviar("n2");
        }
        else if(entityB.getClass().equals(Bashful.class)){
            game.client.enviar("n3");
        }
        else if(entityB.getClass().equals(Pokey.class)){
            game.client.enviar("n4");
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isDeath() {
        return isDeath;
    }

    public void setDeath(boolean death) {
        isDeath = death;
    }
}
