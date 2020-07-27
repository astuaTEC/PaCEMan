package game.graphics;

import game.Game;

import java.awt.image.BufferedImage;

public class Textures {

    // Characters
    public BufferedImage[] pacman = new BufferedImage[8];
    public BufferedImage[] shadow = new BufferedImage[8];
    public BufferedImage[] speedy = new BufferedImage[8];
    public BufferedImage[] bashful = new BufferedImage[8];
    public BufferedImage[] pokey = new BufferedImage[8];
    public BufferedImage[] flashGhost = new BufferedImage[2];

    // Fruits
    public BufferedImage[] cherry = new BufferedImage[1];
    public BufferedImage[] banana = new BufferedImage[1];
    public BufferedImage[] apple = new BufferedImage[1];
    public BufferedImage[] strawberry = new BufferedImage[1];
    public BufferedImage[] pineapple = new BufferedImage[1];
    public BufferedImage[] orange = new BufferedImage[1];

    private SpriteSheet ss = null;

    public Textures(Game game){
        ss = new SpriteSheet(game.getSpriteSheet());

        getTextures();

    }

    private void getTextures(){
        //PacMan
        pacman[0] = ss.grabImage(18, 2, 50, 50);
        pacman[1] = ss.grabImage(18, 3, 50, 50);
        pacman[2] = ss.grabImage(18, 5, 50, 50);
        pacman[3] = ss.grabImage(18, 6, 50, 50);
        pacman[4] = ss.grabImage(18, 8, 50, 50);
        pacman[5] = ss.grabImage(18, 9, 50, 50);
        pacman[6] = ss.grabImage(18, 11, 50, 50);
        pacman[7] = ss.grabImage(18, 12, 50, 50);

        //Shadow (Blinky)
        shadow[0] = ss.grabImage(1, 1, 50, 50);
        shadow[1] = ss.grabImage(1, 2, 50, 50);
        shadow[2] = ss.grabImage(1, 3, 50, 50);
        shadow[3] = ss.grabImage(1, 4, 50, 50);
        shadow[4] = ss.grabImage(1, 5, 50, 50);
        shadow[5] = ss.grabImage(1, 6, 50, 50);
        shadow[6] = ss.grabImage(1, 7, 50, 50);
        shadow[7] = ss.grabImage(1, 8, 50, 50);

        //Speedy (Pinky)
        speedy[0] = ss.grabImage(2, 1, 50, 50);
        speedy[1] = ss.grabImage(2, 2, 50, 50);
        speedy[2] = ss.grabImage(2, 3, 50, 50);
        speedy[3] = ss.grabImage(2, 4, 50, 50);
        speedy[4] = ss.grabImage(2, 5, 50, 50);
        speedy[5] = ss.grabImage(2, 6, 50, 50);
        speedy[6] = ss.grabImage(2, 7, 50, 50);
        speedy[7] = ss.grabImage(2, 8, 50, 50);

        //Bashful (Inky)
        bashful[0] = ss.grabImage(3, 1, 50, 50);
        bashful[1] = ss.grabImage(3, 2, 50, 50);
        bashful[2] = ss.grabImage(3, 3, 50, 50);
        bashful[3] = ss.grabImage(3, 4, 50, 50);
        bashful[4] = ss.grabImage(3, 5, 50, 50);
        bashful[5] = ss.grabImage(3, 6, 50, 50);
        bashful[6] = ss.grabImage(3, 7, 50, 50);
        bashful[7] = ss.grabImage(3, 8, 50, 50);

        //Pokey (Clyde)
        pokey[0] = ss.grabImage(4, 1, 50, 50);
        pokey[1] = ss.grabImage(4, 2, 50, 50);
        pokey[2] = ss.grabImage(4, 3, 50, 50);
        pokey[3] = ss.grabImage(4, 4, 50, 50);
        pokey[4] = ss.grabImage(4, 5, 50, 50);
        pokey[5] = ss.grabImage(4, 6, 50, 50);
        pokey[6] = ss.grabImage(4, 7, 50, 50);
        pokey[7] = ss.grabImage(4, 8, 50, 50);

        //flashing ghost
        flashGhost[0] = ss.grabImage(1, 12, 50, 50);
        flashGhost[1] = ss.grabImage(2, 12, 50, 50);


        // Cherry
        cherry[0] = ss.grabImage(13, 1, 50, 50);

        //Strawberry
        strawberry[0] = ss.grabImage(13, 2, 50, 50);

        //orange
        orange[0] = ss.grabImage(13, 3, 50, 50);

        //apple
        apple[0] = ss.grabImage(13, 4, 50, 50);

        //pineapple
        pineapple[0] = ss.grabImage(13, 5, 50, 50);

        //banana
        banana[0] = ss.grabImage(13, 6, 50, 50);


    }
}
