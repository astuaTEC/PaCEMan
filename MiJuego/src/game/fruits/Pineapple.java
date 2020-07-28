package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

public class Pineapple extends Fruit implements EntityC {

    public Pineapple(int col, int row, int value, Textures textures){
        super(col, row, value, textures);
        this.fruit = textures.pineapple[0];
    }

}
