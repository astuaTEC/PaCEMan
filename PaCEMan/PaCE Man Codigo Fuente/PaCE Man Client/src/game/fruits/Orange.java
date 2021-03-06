package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

/**
 * Orange element class
 * @author Saymon Astúa, Oscar Araya
 */

public class Orange extends Fruit implements EntityC {

    public Orange(int col, int row, int value, Textures textures){
        super(col, row, value, textures);
        this.fruit = textures.orange[0];
    }

}
