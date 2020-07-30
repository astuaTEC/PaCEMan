package game.gameControls;

import game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * MouseInput class
 * @author Saymon Astúa, Oscar Araya
 */

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // Play Button
        if(mx > Game.WIDTH/2 + 200 && mx <= Game.WIDTH/2 + 300){
            if(my >= 150 && my <= 200){
                //Pressed Play Button
                Game.State = Game.STATE.GAME;
            }
        }
        // Quit Button
        if(mx > Game.WIDTH/2 + 200 && mx <= Game.WIDTH/2 + 300){
            if(my >= 250 && my <= 300){
                System.exit(1);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
