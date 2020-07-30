package game.classes;

import java.awt.*;

/**
 *  Wall Interface, for the graphic bounds
 * @author Saymon Astúa, Oscar Araya
 */
public interface WallEntity {

    // Graphic methods
    public Rectangle getBounds();
    public boolean isGhosLicense();
    public double getX();
    public double getY();
    public void tick();
    public void render(Graphics g);
}
