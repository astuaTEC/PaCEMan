package game.gameControls;

import game.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * KeyInput class
 * @author Saymon Astúa, Oscar Araya
 */
public class KeyInput extends KeyAdapter {

    Game game;

    public KeyInput(Game game){
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        game.keyReleased(e);
    }
}
