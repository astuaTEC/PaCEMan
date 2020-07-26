package game;

import game.classes.EntityB;
import game.libs.Animation;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject implements EntityB {

    private double x, y;
    Random r = new Random();

    private Textures textures;

    private int speed = r.nextInt(3) + 1;

    Animation animation;

    public Enemy(double x, double y, Textures textures){
        super(x, y);
        this.textures = textures;

        animation = new Animation(10, textures.enemy[0], textures.enemy[1],
                textures.enemy[2], textures.enemy[3], textures.enemy[4], textures.enemy[5],
                textures.enemy[6], textures.enemy[7]);
    }

    public void tick(){
        y += speed;
        if(y > (Game.HEIGHT * Game.SCALE)){
            speed = r.nextInt(3) + 1;
            y = -10;
            x = r.nextInt(640);
        }
        animation.runAnimation();

    }

    public void render(Graphics g){
        animation.drawAnimation(g, x, y, 0);
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
}
