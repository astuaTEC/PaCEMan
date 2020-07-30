package game.graphics;

import game.Game;

import java.awt.image.BufferedImage;
/**
 * Textures class
 * Load game textures
 * @author Saymon Astúa, Oscar Araya
 */
public class Textures {

    // Characters
    public BufferedImage[] pacman = new BufferedImage[8];
    public BufferedImage[] deathPacman = new BufferedImage[8];
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

    //PacDots
    public BufferedImage[] pacDot = new BufferedImage[1];

    //Pill
    public BufferedImage[] pill = new BufferedImage[1];

    //Life
    public BufferedImage[] life = new BufferedImage[1];

    private SpriteSheet ss = null;

    public Textures(Game game){
        ss = new SpriteSheet(game.getSpriteSheet());

        getTextures();

    }

    private void getTextures(){
        //PacMan
        pacman[0] = ss.grabImage(18, 2, 20, 20);
        pacman[1] = ss.grabImage(18, 3, 20, 20);
        pacman[2] = ss.grabImage(18, 5, 20, 20);
        pacman[3] = ss.grabImage(18, 6, 20, 20);
        pacman[4] = ss.grabImage(18, 8, 20, 20);
        pacman[5] = ss.grabImage(18, 9, 20, 20);
        pacman[6] = ss.grabImage(18, 11, 20, 20);
        pacman[7] = ss.grabImage(18, 12, 20, 20);

        // Death Pacman
        deathPacman[0] = ss.grabImage(8, 1, 20, 20);
        deathPacman[1] = ss.grabImage(8, 2, 20, 20);
        deathPacman[2] = ss.grabImage(8, 3, 20, 20);
        deathPacman[3] = ss.grabImage(8, 4, 20, 20);
        deathPacman[4] = ss.grabImage(8, 5, 20, 20);
        deathPacman[5] = ss.grabImage(8, 6, 20, 20);
        deathPacman[6] = ss.grabImage(8, 7, 20, 20);
        deathPacman[7] = ss.grabImage(8, 8, 20, 20);

        //Shadow (Blinky)
        shadow[0] = ss.grabImage(1, 1, 20, 20);
        shadow[1] = ss.grabImage(1, 2, 20, 20);
        shadow[2] = ss.grabImage(1, 3, 20, 20);
        shadow[3] = ss.grabImage(1, 4, 20, 20);
        shadow[4] = ss.grabImage(1, 5, 20, 20);
        shadow[5] = ss.grabImage(1, 6, 20, 20);
        shadow[6] = ss.grabImage(1, 7, 20, 20);
        shadow[7] = ss.grabImage(1, 8, 20, 20);

        //Speedy (Pinky)
        speedy[0] = ss.grabImage(2, 1, 20, 20);
        speedy[1] = ss.grabImage(2, 2, 20, 20);
        speedy[2] = ss.grabImage(2, 3, 20, 20);
        speedy[3] = ss.grabImage(2, 4, 20, 20);
        speedy[4] = ss.grabImage(2, 5, 20, 20);
        speedy[5] = ss.grabImage(2, 6, 20, 20);
        speedy[6] = ss.grabImage(2, 7, 20, 20);
        speedy[7] = ss.grabImage(2, 8, 20, 20);

        //Bashful (Inky)
        bashful[0] = ss.grabImage(3, 1, 20, 20);
        bashful[1] = ss.grabImage(3, 2, 20, 20);
        bashful[2] = ss.grabImage(3, 3, 20, 20);
        bashful[3] = ss.grabImage(3, 4, 20, 20);
        bashful[4] = ss.grabImage(3, 5, 20, 20);
        bashful[5] = ss.grabImage(3, 6, 20, 20);
        bashful[6] = ss.grabImage(3, 7, 20, 20);
        bashful[7] = ss.grabImage(3, 8, 20, 20);

        //Pokey (Clyde)
        pokey[0] = ss.grabImage(4, 1, 20, 20);
        pokey[1] = ss.grabImage(4, 2, 20, 20);
        pokey[2] = ss.grabImage(4, 3, 20, 20);
        pokey[3] = ss.grabImage(4, 4, 20, 20);
        pokey[4] = ss.grabImage(4, 5, 20, 20);
        pokey[5] = ss.grabImage(4, 6, 20, 20);
        pokey[6] = ss.grabImage(4, 7, 20, 20);
        pokey[7] = ss.grabImage(4, 8, 20, 20);

        //flashing ghost
        flashGhost[0] = ss.grabImage(1, 12, 20, 20);
        flashGhost[1] = ss.grabImage(2, 12, 20, 20);


        // Cherry
        cherry[0] = ss.grabImage(13, 1, 20, 20);

        //Strawberry
        strawberry[0] = ss.grabImage(13, 2, 20, 20);

        //orange
        orange[0] = ss.grabImage(13, 3, 20, 20);

        //apple
        apple[0] = ss.grabImage(13, 4, 20, 20);

        //pineapple
        pineapple[0] = ss.grabImage(13, 5, 20, 20);

        //banana
        banana[0] = ss.grabImage(13, 6, 20, 20);

        //PacDot
        pacDot[0] = ss.grabImage(9, 5, 10, 10);

        //Pill
        pill[0] = ss.grabImage(19, 1, 20, 20);

        //Life
        life[0] = ss.grabImage(18, 9, 20, 20);

    }
}
