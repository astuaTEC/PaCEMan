package game.elements;

import game.graphics.Textures;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Life class
 * @author Saymon Astúa, Oscar Araya
 */
public class Life {

    //Attributes
    double x, y;
    Textures textures;
    BufferedImage life;

    public Life(double x, double y, Textures textures){
        this.x = x;
        this.y = y;
        this.textures = textures;

        this.life = textures.life[0];

    }

    /**
     * Update Life graphics
     * @param g Graphics to draw on the screen
     */
    public void render(Graphics g){
        g.drawImage(life, (int)x, (int)y, null);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

}
