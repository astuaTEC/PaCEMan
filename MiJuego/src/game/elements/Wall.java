package game.elements;

import game.classes.WallEntity;

import java.awt.*;

public class Wall implements WallEntity {


    private Rectangle rectangle;

    private double x, y;

    private boolean ghosLicense;

    public Wall(double x, double y, boolean ghosLicense) {
        this.x = x;
        this.y = y;
        this.ghosLicense = ghosLicense;

        this.rectangle = new Rectangle((int)x, (int)y, 20, 20);
    }

    public Rectangle getBounds() {
        return rectangle;
    }

    public void tick(){

    }
    public void render(Graphics g) {

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isGhosLicense() {
        return ghosLicense;
    }
}
