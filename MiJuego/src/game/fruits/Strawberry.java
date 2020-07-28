package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

public class Strawberry extends Fruit implements EntityC {

    public Strawberry(int col, int row, int value, Textures textures){
        super(col, row, value, textures);
        this.fruit = textures.strawberry[0];
    }

}
