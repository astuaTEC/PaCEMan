package game;

import java.awt.image.BufferedImage;

public class Textures {

    BufferedImage[] player = new BufferedImage[8];
    BufferedImage[] enemy = new BufferedImage[8];

    private SpriteSheet ss = null;

    public Textures(Game game){
        ss = new SpriteSheet(game.getSpriteSheet());

        getTextures();

    }
    private void getTextures(){
        //Player
        player[0] = ss.grabImage(18, 2, 50, 50);
        player[1] = ss.grabImage(18, 3, 50, 50);
        player[2] = ss.grabImage(18, 5, 50, 50);
        player[3] = ss.grabImage(18, 6, 50, 50);
        player[4] = ss.grabImage(18, 8, 50, 50);
        player[5] = ss.grabImage(18, 9, 50, 50);
        player[6] = ss.grabImage(18, 11, 50, 50);
        player[7] = ss.grabImage(18, 12, 50, 50);

        //Enemy
        enemy[0] = ss.grabImage(1, 1, 50, 50);
        enemy[1] = ss.grabImage(1, 2, 50, 50);
        enemy[2] = ss.grabImage(1, 3, 50, 50);
        enemy[3] = ss.grabImage(1, 4, 50, 50);
        enemy[4] = ss.grabImage(1, 5, 50, 50);
        enemy[5] = ss.grabImage(1, 6, 50, 50);
        enemy[6] = ss.grabImage(1, 7, 50, 50);
        enemy[7] = ss.grabImage(1, 8, 50, 50);
    }
}
