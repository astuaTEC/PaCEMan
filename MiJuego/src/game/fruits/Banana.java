package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

public class Banana extends Fruit implements EntityC {

    public Banana(int col, int row, int value, Textures textures){
        super(col, row, value, textures);
        this.fruit = textures.banana[0];
    }

}
