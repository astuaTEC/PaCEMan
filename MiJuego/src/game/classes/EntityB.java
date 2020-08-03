package game.classes;

import java.awt.*;

/**
 *  Ghosts Interface
 * @author Saymon Astúa, Oscar Araya
 */
public interface EntityB {

    //Graphic methods
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public double getX();
    public double getY();
    public boolean isFlash();
    public void setFlash(boolean flash);
    public void setRender(boolean flash);
    public void setVel(double vel);

}
