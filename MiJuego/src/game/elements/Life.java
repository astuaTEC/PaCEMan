package game.elements;

import game.graphics.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Life {

    double x, y;
    Textures textures;
    BufferedImage life;

    public Life(double x, double y, Textures textures){
        this.x = x;
        this.y = y;
        this.textures = textures;

        this.life = textures.life[0];

    }

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
