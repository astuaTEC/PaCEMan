package game.elements;

import game.classes.EntityC;
import game.graphics.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * PacDots class
 * @author Saymon Astúa, Oscar Araya
 */
public class PacDots implements EntityC {

    double x, y;
    int value;
    Textures textures;
    BufferedImage pacDot;

    public PacDots(double x, double y, Textures textures){
        this.x = x;
        this.y = y;
        this.value = 10;
        this.textures = textures;

        this.pacDot = textures.pacDot[0];

    }

    public void tick(){

    }
    /**
     * Update PacDots graphics
     * @param g Graphics to draw on the screen
     */
    public void render(Graphics g){
        g.drawImage(pacDot, (int)x, (int)y, null);
    }

    /**
     * Graphic limits of pacDots
     * @return Ractangle: means the limit of PacDots element
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 10, 10);
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
