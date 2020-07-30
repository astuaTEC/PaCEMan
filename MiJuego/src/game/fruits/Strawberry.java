package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

/**
 * Strawberry element class
 * @author Saymon Astúa, Oscar Araya
 */

public class Strawberry extends Fruit implements EntityC {

    public Strawberry(int col, int row, int value, Textures textures){
        super(col, row, value, textures);
        this.fruit = textures.strawberry[0];
    }

}
