package game.characters;

import game.graphics.Textures;
import game.libs.Animation;

import java.awt.*;
import java.util.Random;

public class Ghost {

    double x, y;

    Random r = new Random();

    Textures textures;

    int speed = r.nextInt(3) + 1;

    boolean up, down, right, left;

    Animation upAnimation, downAnimation, leftAnimation, rightAnimation;

    public Ghost(double x, double y, Textures textures){
        this.x = x;
        this.y = y;
        this.textures = textures;

        up = false;
        right = false;
        down = true;
        left = false;
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
}
