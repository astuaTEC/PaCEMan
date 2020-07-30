package game.elements;

import game.classes.EntityC;
import game.graphics.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Pill class
 * @author Saymon Astúa, Oscar Araya
 */
public class Pill implements EntityC {

    //Attributes
    double x, y;
    int value;
    Textures textures;
    BufferedImage pill;

    public Pill(double x, double y, Textures textures){
        this.x = x;
        this.y = y;
        this.value = 10;
        this.textures = textures;

        this.pill = textures.pill[0];

    }

    public void tick(){

    }

    /**
     * Update Pills graphics
     * @param g Graphics to draw on the screen
     */
    public void render(Graphics g){
        g.drawImage(pill, (int)x, (int)y, null);
    }

    /**
     * Graphic limits of pill
     * @return Ractangle: means the limit of pill element
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 20, 20);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getValue() {
        return value;
    }
}
