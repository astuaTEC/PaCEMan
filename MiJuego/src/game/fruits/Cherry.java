package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

public class Cherry extends Fruit implements EntityC {

    public Cherry(int col, int row, int value, Textures textures){
        super(col, row, value, textures);
        this.fruit = textures.cherry[0];
    }

}
