package game.graphics;

import java.awt.image.BufferedImage;

/**
 * SpriteSheet class
 * Map a sprite sheet
 * @author Saymon Astúa, Oscar Araya
 */
public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    /**
     * Method to find a sprite on the sprite sheet
     * @param col column where you want to access
     * @param row row where you want to access
     * @param width sprite width
     * @param height sprite height
     * @return BufferedImage: An image with the sprite
     */
    public BufferedImage grabImage(int col, int row, int width, int height){

        BufferedImage img = image.getSubimage((col * 25) - 25,(row * 25) - 25, width, height);
        return img;

    }
}
