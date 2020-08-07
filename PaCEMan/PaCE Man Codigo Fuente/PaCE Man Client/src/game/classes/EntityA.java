package game.classes;

import java.awt.*;

/**
 *  Pacman Interface
 * @author Saymon Astúa, Oscar Araya
 */
public interface EntityA {

    // Graphic Methods
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public double getX();
    public double getY();

}
