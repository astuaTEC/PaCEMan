package game.characters;

import game.Controller;
import game.algorithm.Algorithm;
import game.algorithm.FindPath;
import game.algorithm.Node;
import game.graphics.Textures;
import game.libs.Animation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ghost character class
 * @author Saymon Astúa, Oscar Araya
 */
public class Ghost {

    // Attributes
    double x, y;
    double velX = 0;
    double velY = 0;

    Random r = new Random();

    Textures textures;
    Controller controller;
    String indication = null;

    ArrayList<String> movements = new ArrayList<>();

    public Algorithm algorithm = new Algorithm();

    boolean up, down, right, left, isFlash;

    // Animations
    Animation upAnimation, downAnimation, leftAnimation, rightAnimation, flashAnimation;

    public Ghost(double x, double y, Textures textures, Controller controller){
        this.x = x;
        this.y = y;
        this.textures = textures;
        this.controller = controller;

        up = false;
        right = false;
        down = true;
        left = false;
        isFlash = false;

        indication = "U";

        velX = 1;
        velY = 1;

        flashAnimation = new Animation(7, textures.flashGhost[0], textures.flashGhost[1]);

        movements.add("R"); movements.add("D"); movements.add("U"); movements.add("L");
    }

    /**
     * Update Ghost graphics
     * @param g Graphics to draw on the screen
     */
    public void render(Graphics g){
        // Draw full path
        /*for(Node n : FindPath.closedList) {
            int i = n.getRow();
            int j = n.getCol();
            g.setColor(Color.GREEN);
            g.fillRect(20 * j, 20 * i, 20, 20);
            g.setColor(Color.darkGray);
            g.drawRect(20 * j, 20 * i, 20, 20);
        }*/

        if(!isFlash) {
            if (up)
                upAnimation.drawAnimation(g, x, y, 0);
            if (down)
                downAnimation.drawAnimation(g, x, y, 0);
            if (left)
                leftAnimation.drawAnimation(g, x, y, 0);
            if (right)
                rightAnimation.drawAnimation(g, x, y, 0);
        }
        else{
            flashAnimation.drawAnimation(g, x, y, 0);
        }

    }

    public Point getPos(){
        Point point = new Point();
        point.setLocation((int)(x/20), (int)(y/20));
        return point;

    }

    public void changeDirection(){
        int i = r.nextInt(3 + 1);
        indication = movements.get(i);
    }

    /**
     * Graphic limits of ghost
     * @return Ractangle: means the limit of Ghost character
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 20, 20);
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

    public boolean isFlash() {
        return isFlash;
    }

    public void setFlash(boolean flash) {
        isFlash = flash;
    }
}
