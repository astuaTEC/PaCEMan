package game.classes;

import java.awt.*;

/**
 *  Interface for PacDots, Fruits and Pills
 * @author Saymon Astúa, Oscar Araya
 */
public interface EntityC {

    //Graphic methods
    public void tick();
    public void render(Graphics g);
    public int getValue();
    public Rectangle getBounds();
}
