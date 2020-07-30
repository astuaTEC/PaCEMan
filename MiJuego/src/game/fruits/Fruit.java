package game.fruits;

import game.graphics.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Fruit element class
 * @author Saymon Astúa, Oscar Araya
 */

public class Fruit {

    //Attributes
    double x, y;
    int value;
    Textures textures;
    BufferedImage fruit;

    public Fruit(int col, int row, int value, Textures textures){
        this.x = col*20;
        this.y = row*20 - 20;
        this.value = value;
        this.textures = textures;
    }

    public void tick(){

    }

    /**
     * Update Fruit graphics
     * @param g Graphics to draw on the screen
     */
    public void render(Graphics g){
        g.drawImage(fruit, (int)x, (int)y, null);
    }

    /**
     * Graphic limits of Fruit
     * @return Ractangle: means the limit of Fruit element
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
