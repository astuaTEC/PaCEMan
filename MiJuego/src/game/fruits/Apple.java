package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

/**
 * Apple element class
 * @author Saymon Astúa, Oscar Araya
 */

public class Apple extends Fruit implements EntityC {

    public Apple(int col, int row, int value, Textures textures){
        super(col, row, value, textures);
        this.fruit = textures.apple[0];
    }

}
