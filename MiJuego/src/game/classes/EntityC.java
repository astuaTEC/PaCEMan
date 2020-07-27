package game.classes;

import java.awt.*;

public interface EntityC {

    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();
}
