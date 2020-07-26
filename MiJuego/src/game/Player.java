package game;

import game.classes.EntityA;
import game.classes.EntityB;
import game.libs.Animation;

import java.awt.*;


public class Player extends GameObject implements EntityA {

    private double velX = 0;
    private double velY = 0;

    private Textures textures;
    private Game game;
    private Controller c;
    private boolean up, down, right, left;

    Animation upAnimation;
    Animation downAnimation;
    Animation leftAnimation;
    Animation rightAnimation;


    public Player(double x, double y, Textures textures, Game game, Controller c){
        super(x, y);
        this.textures = textures;
        this.game = game;
        this.c = c;
        up = false;
        right = true;
        down = false;
        left = false;

        /*animation = new Animation(10, textures.player[0], textures.player[1],
                textures.player[2], textures.player[3], textures.player[4], textures.player[5],
                textures.player[6], textures.player[7]);*/
        upAnimation = new Animation(10, textures.player[6], textures.player[7]);
        rightAnimation = new Animation(10, textures.player[0], textures.player[1]);
        downAnimation = new Animation(10, textures.player[2], textures.player[3]);
        leftAnimation = new Animation(10, textures.player[4], textures.player[5]);

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
                //c.removeEntity(tempEnt);
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
