package game.classes;

import java.awt.*;

public interface WallEntity {

    public Rectangle getBounds();
    public boolean isGhosLicense();
    public double getX();
    public double getY();
    public void tick();
    public void render(Graphics g);
}
