package game.characters;

import game.*;
import game.classes.EntityA;
import game.classes.EntityB;
import game.graphics.Physics;
import game.graphics.Textures;
import game.libs.Animation;

import java.awt.*;


public class PacMan implements EntityA {

    private double velX = 0;
    private double velY = 0;

    private double x, y;

    private Textures textures;
    private Game game;
    private Controller c;
    private boolean up, down, right, left;

    Animation upAnimation, downAnimation, leftAnimation, rightAnimation;


    public PacMan(double x, double y, Textures textures, Game game, Controller c){
        this.x = x;
        this.y = y;
        this.textures = textures;
        this.game = game;
        this.c = c;
        up = false;
        right = true;
        down = false;
        left = false;

        upAnimation = new Animation(10, textures.pacman[6], textures.pacman[7]);

        rightAnimation = new Animation(10, textures.pacman[0], textures.pacman[1]);

        downAnimation = new Animation(10, textures.pacman[2], textures.pacman[3]);

        leftAnimation = new Animation(10, textures.pacman[4], textures.pacman[5]);

    }

    public void tick(){
        x += velX;
        y += velY;

        if(x <= 0)
            x = 0;
        if (x >= 600)
            x = 600;
        if(y <= 0)
            y = 0;
        if(y >= 480 - 50)
            y = 480 - 50;

        for(int i = 0; i < game.eb.size(); i++){
            EntityB tempEnt = game.eb.get(i);

            if(Physics.Collision(this, tempEnt)){
                System.out.println("Collision");
                /*c.removeEntity(tempEnt);
                game.setEnemy_cont(game.getEnemy_cont() + 1);
                game.setEnemy_killed(game.getEnemy_killed() + 1);*/
            }
        }
        if(up)
            upAnimation.runAnimation();
        if(down)
            downAnimation.runAnimation();
        if(left)
            leftAnimation.runAnimation();
        if(right)
            rightAnimation.runAnimation();

    }

    public void render(Graphics g){
        if(up)
            upAnimation.drawAnimation(g, x, y, 0);
        if(down)
            downAnimation.drawAnimation(g, x, y,0);
        if(left)
            leftAnimation.drawAnimation(g, x, y, 0);
        if(right)
            rightAnimation.drawAnimation(g, x, y, 0);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 50, 50);
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
}
